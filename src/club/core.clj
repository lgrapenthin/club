(ns club.core
  (:require [club.doorman :refer [wrap-doorman]]
            [club.bouncer :refer [wrap-bouncer]]))

(defn wrap-club
  "Wraps both doorman and bouncer. Please refer to the configuration
  parameters in the docstrings of club.doorman/wrap-doorman and
  club.bouncer/wrap-bouncer"
  [handler cfg]
  (-> handler
      (wrap-bouncer cfg)
      (wrap-doorman cfg)))
