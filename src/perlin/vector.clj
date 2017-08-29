(ns perlin.vector
  (:require [quil.core :as q]))

(defn from-angle
  ([ang]
   [(Math/cos ang)
    (Math/sin ang)])
  ([ang limit]
   (map #(* limit %)
        (from-angle ang))))

(defn random
  ([]
   (from-angle (rand Math/PI)))
  ([x]
   (from-angle (rand Math/PI) x))
  ([x y]
   [(rand x)
    (rand y)]))

(defn make-vector [x y]
  [x y])

(defn draw [vector]
  (let [[x y] vector]
    (q/point x y)))

(defn add [v1 v2]
  (let [[x1 y1] v1
        [x2 y2] v2]
    [(+ x1 x2)
     (+ y1 y2)]))

(defn perlin-vector [x y z]
  (let [angle (* 2 Math/PI
                 (q/noise x y z))]
    (from-angle angle)))

(defn angle [x y]
  (let [a (Math/atan2 y x)]
    (if (< a 0)
      (+ a (* Math/PI 2))
      a)))

(defn draw-perlin [v x y]
  (let [[x y] v
        a (angle x y)]
    (q/line 
