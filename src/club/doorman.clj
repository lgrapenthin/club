(ns club.doorman)

(defn wrap-doorman
  "The following keys must be provided in the configuration parameter:

  :op A (fn [request]) that returns an operation identifier or nil if
                       the operation is not guarded

  :subject A (fn [request]) that returns a subject identifier

  :not-authorized? A (fn [subject op]) that returns a reason why a
                   subject is not authorized for an operation or, if
                   that is not the case, nil
                   
  :handle-not-authorized A (fn [req reason]) that continues handling
                         the request when the subject has not been
                         authorized

  :log-authorized A (fn [subject op]) that logs authorized requests"
  [handler {:keys [op subject not-authorized?
                   handle-not-authorized log-authorized]}]
  (fn [req]
    (if-let [op (op req)]
      (let [subject (subject req)]
        (if-let [reason (not-authorized? subject op)]
          (handle-not-authorized req reason)
          (do (log-authorized req)
              (handler req)))))))
