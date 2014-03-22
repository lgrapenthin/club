(ns club.bouncer)

(defn forbidden
  "ex-info carrying the reason why an operation is not permitted"
  [reason]
  (ex-info "Operation forbidden"
           {:type ::forbidden
            :reason reason}))

(defmacro restrain
  "Indicate that an operation is not permitted.

  Examples:

  (restrain \"You must be at least 18 years old\" (>= (:age user) 18))"
  [reason assertion]
  `(when-not ~assertion
     (throw (forbidden ~reason))))

(defn wrap-bouncer
  "The following keys must be provided in the configuration parameter:

  :handle-forbidden A (fn [req reason]) that continues handling the
                    request when it was not permitted
  :log-permitted A (fn [req]) that logs requests that passed bouncer"
  [handler {:keys [handle-forbidden log-permitted]}]
  (fn [req]
    (try (do (log-permitted req)
             (handler req))
         (catch Exception e
           (let [{:keys [type reason]} (ex-data e)]
             (if (= ::forbidden type)
               (handle-forbidden req reason)
               (throw e)))))))
