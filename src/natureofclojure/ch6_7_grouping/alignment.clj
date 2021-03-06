;; Based on the Nature of Code
;; by Daniel Shiffman
;; http://natureofcode.com
;;
;; Specifically:
;; https://github.com/shiffman/The-Nature-of-Code-Examples/tree/master/chp6_agents/Alignment
;;
(ns natureofclojure.ch6-7-grouping.alignment
  (:require
   [quil.core :as q]
   [quil.middleware :as m]
   [natureofclojure.math.fast-vector :as fvec]
   [natureofclojure.ch6-7-grouping.behavior :as beh]))

(def SIZE-W 800.0)
(def SIZE-H 600.0)

(def VEHICLE-R 5.0)
(def VEHICLE-D (* 2 VEHICLE-R))

(def NEIGHBOR-DIST 30.0)

(defn random-vehicle
  ([]
     (random-vehicle (rand SIZE-W) (rand SIZE-H)))
  ([x y]
     {:location (fvec/fvec x y)
      :velocity (fvec/fvec (- (rand 80.0) 40.0)
                           (- (rand 80.0) 40.0))
      :acceleration (fvec/fvec 0.0 0.0)
      :max-speed (+ 4.0 (rand 4.0))
      :max-force (+ 0.2 (rand 0.2))}))

(def VEHICLES
  (vec
   (for [_ (range 10)]
     (random-vehicle))))

(defn setup []
  {:vehicles VEHICLES})

(defn update-vehicles [vehicles]
  (doall (map #(->> %
                    (beh/align NEIGHBOR-DIST vehicles)
                    (beh/move-vehicle)
                    (beh/borders SIZE-W SIZE-H VEHICLE-R))
              vehicles)))

(defn update [{:keys [vehicles] :as state}]
  (-> state
      (update-in [:vehicles] update-vehicles)))

(defn draw-vehicle
  [{:keys [location]}]
  (let [[x y] (fvec/x-y location)]
    (q/with-translation [x y]
      (q/ellipse 0 0 VEHICLE-D VEHICLE-D))))

(defn draw [state]
  (q/background 255)
  (let [{:keys [vehicles]} state]
    (q/fill 255 0 0)
    (doall (map draw-vehicle vehicles))))

(defn mouse-pressed [state event]
  (let [{:keys [x y]} event]
    (update-in state [:vehicles] #(conj % (random-vehicle x y)))))

(q/defsketch quil-workflow
  :title "Group Steering Behaviors: Alignment"
  :size [SIZE-W SIZE-H]
  :setup setup
  :update update
  :draw draw
  :mouse-pressed mouse-pressed
  :middleware [m/fun-mode])
