;; Based on the Nature of Code
;; by Daniel Shiffman
;; http://natureofcode.com
;;
;; Specifically:
;; https://github.com/shiffman/The-Nature-of-Code-Examples/blob/master/Processing/chp1_vectors/NOC_1_8_motion101_acceleration/Mover.pde
;;
(ns natureofclojure.ch1-5-vectors-acceleration.mover.acceleration
  (:require [quil.core :as qc])
  (:use [natureofclojure.math.vector :as mv]))

(def mover (atom {:location []
                  :velocity []
                  :acceleration []
                  :top-speed 10.0}))

(defn init-mover [width height m]
  (-> (assoc-in m [:location] [(/ width 2.0) (/ height 2.0)])
      (assoc-in [:velocity] [0.0 0.0])
      (assoc-in [:acceleration] [-0.001 0.01])))

(defn initialize [m-atom width height]
  (swap! m-atom (partial init-mover width height)))

(defn update-mover [m]
  (-> (update-in m [:velocity] #(mv/add % (:acceleration m)))
      (update-in [:velocity] #(mv/limit (:top-speed m) %))
      (update-in [:location] #(mv/add % (:velocity m)))))

(defn update [m-atom]
  (swap! m-atom update-mover))

(defn display [m]
  (qc/stroke 0)
  (qc/stroke-weight 2)
  (qc/fill 127)
  (apply #(qc/ellipse %1 %2 48 48) (:location m)))

(defn check-edges [m-atom width height]
  (let [loc (:location @m-atom)
        x (first loc)
        y (second loc)]
    (cond (> x width) (swap! m-atom assoc-in [:location] [0 y])
          (< x 0) (swap! m-atom assoc-in [:location] [width y])
          (> y height) (swap! m-atom assoc-in [:location] [x 0])
          (< y 0) (swap! m-atom assoc-in [:location] [x height]))))