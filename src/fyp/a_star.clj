(ns fyp.a-star
  (:use [clojure.data.priority-map]))

(defn visited?
  [v coll]
  (some #(= % v) coll))

(defn find-neighbours
  [v coll]
  (first (get coll v)))

(defn shortest-path
  [coll]
  (loop [val (first coll) col coll best val]
    (if (empty? col)
      best
      (if (< (last val) (last best))
        (recur (first (rest col)) (rest col) val)
        (recur (first (rest col)) (rest col) best)))))

(defn h
  [key heur]
  (get heur key))

(defn shortest-path-heuristics
  [coll heuristic]
  (loop [val (first coll) col coll best val]
    (if (empty? col)
      (first best)
      (if (< (+ (last val) (h (first val) heuristic)) (+ (last best) (h (first best) heuristic)))
        (recur (first (rest col)) (rest col) val)
        (recur (first (rest col)) (rest col) best)))))

(defn not-visited
  [neighbours path]
  (let [filtered-set (remove #(visited? % path) (for [neighbour neighbours] (conj (first neighbour))))
        not-visited-neighbours (for [neighbour filtered-set]
                                 (conj [neighbour (get neighbours neighbour)]))]
    not-visited-neighbours))

(defn a*
  [start goal graph heur]
  (let [goal? (if (fn? goal) goal #(= goal %))]
    (loop [open (priority-map [start [start] 0] (h start heur))]
      (let [[[node path distance] total] (first open)]
        (cond
          (goal? node)
          [path distance]
          (empty? (not-visited (find-neighbours node graph) path))
          "Not Found"
          :else
          (recur (let [neighbours (find-neighbours node graph)
                       new-node (shortest-path-heuristics (not-visited neighbours path) heur)
                       new-path (conj path new-node)
                       new-distance (+ distance (last (shortest-path (not-visited neighbours path))))
                       new-total (+ new-distance (h new-node heur))
                       new-open (priority-map [new-node new-path new-distance] new-total)]
                   new-open))
          )))))

(def graph2
  {
   :A [{:B 20 :E 10 :D 4 :G 9 :J 15}]
   :B [{:A 20 :C 11 :D 9 :E 21}]
   :C [{:B 11 :H 13 :I 14 :D 3}]
   :D [{:B 9 :A 4 :C 3 :H 15 :J 10}]
   :E [{:A 10 :B 21 :F 16 :G 17}]
   :F [{:E 16 :G 24}]
   :G [{:E 17 :F 24 :K 5 :J 7 :A 9}]
   :H [{:C 13 :I 2 :D 15 :J 4 :K 23}]
   :I [{:H 2 :C 14}]
   :J [{:G 7 :K 10 :H 4 :D 10 :A 15}]
   :K [{:G 5 :J 10 :H 23}]
   })

(def heuristics
  {
   :A 7
   :B 11
   :C 0
   :D 3
   :E 17
   :F 33
   :G 16
   :H 13
   :I 14
   :J 13
   :K 23
   })

(a* :F :C graph2 heuristics)
(a* :F :Q graph2 heuristics)