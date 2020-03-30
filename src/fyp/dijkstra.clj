(ns fyp.dijkstra
  (:use [clojure.data.priority-map])
  (:gen-class
    :methods [#^{:static true} [dijkstraMethod [String String String] String]]))

(defn visited?
  [v coll]
  (some #(= % v) coll))

(defn find-neighbours
  [v coll]
  (first (get coll v)))

(defn Dijkstra-method
  [start goal graph]
  (let [goal? (if (fn? goal) goal #(= goal %))]
    (loop [closed [] open (priority-map [start [start]] 0)]
      (let [[[node path] distance] (first open)]
        (cond
          (goal? node)
          (str [path distance])
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

(defn -dijkstraMethod [sta go gra]
  (let
    [graph (clojure.edn/read-string (str "{" gra "}"))
     start (keyword sta)
     goal (keyword go)]
    (Dijkstra-method start goal graph)))