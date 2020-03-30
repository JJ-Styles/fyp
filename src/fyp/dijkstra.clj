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

(time (-dijkstraMethod "A"
                    "C"
                    ":A [{:B 20 :E 10 :D 4 :G 9 :J 15}]\n :B [{:A 20 :C 11 :D 9 :E 21}]\n :C [{:B 11 :H 13 :I 14 :D 3}]\n :D [{:B 9 :A 4 :C 3 :H 15 :J 10}]\n :E [{:A 10 :B 21 :F 16 :G 17}]\n :F [{:E 16 :G 24}]\n :G [{:E 17 :F 24 :K 5 :J 7 :A 9}]\n :H [{:C 13 :I 2 :D 15 :J 4 :K 23}]\n :I [{:H 2 :C 14}]\n :J [{:G 7 :K 10 :H 4 :D 10 :A 15}]\n :K [{:G 5 :J 10 :H 23}]"))