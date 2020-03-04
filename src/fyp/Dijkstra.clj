(ns fyp.Dijkstra
  (:use [clojure.data.priority-map]))

(defn visited?
  [v coll]
  (some #(= % v) coll))

(defn find-neighbours
  [v coll]
  (first (get coll v)))

(defn Dijkstra
  [start goal graph]
  (let [goal? (if (fn? goal) goal #(= goal %))]
    (loop [closed [] open (priority-map [start [start]] 0)]
      (let [[[node path] distance] (first open)]
        (cond
          (goal? node)
          [path distance]
          (every? #(visited? % closed) (keys graph))
          "Not Found"
          :else
          (recur (conj closed node)
                 (into (pop open) (for [neighbour (find-neighbours node graph)
                                        :let [new-node (first neighbour)
                                              new-path (conj path new-node)
                                              new-distance (+ distance (last neighbour))]
                                        :when (not (visited? new-node closed))]
                                    [[new-node new-path] new-distance])))
          )))))