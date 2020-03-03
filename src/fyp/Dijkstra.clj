(ns fyp.Dijkstra)

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

(defn not-visited
  [neighbours path]
  (let [filtered-set (remove #(visited? % path) (for [neighbour neighbours] (conj (first neighbour))))
        not-visited-neighbours (for [neighbour filtered-set]
                                 (conj [neighbour (get neighbours neighbour)]))]
    not-visited-neighbours))

(defn Dijkstra
  [graph start goal]
  (let [Q [] goal? (if (fn? goal) goal #(= goal %))]
    (loop [Q Q
           node start
           path [node]
           distance 0]
      (cond
        (goal? node)
        [path distance]
        (empty? Q)
        (if (> (count path) 1)
          "Not Found"
          (recur (for [neighbour (keys graph)]
                   (conj Q neighbour))
                 node
                 path
                 distance))
        :else
        (let [neighbours (find-neighbours node graph)
              new-node (first (shortest-path (not-visited neighbours path)))
              new-path (conj path new-node)
              new-Q (rest Q)
              new-distance (+ distance (if (nil? new-node)
                                         0
                                         (last (shortest-path (not-visited neighbours path)))))]
          (recur new-Q new-node new-path new-distance))
        ))))

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

(Dijkstra graph2 :F :C)
(Dijkstra graph2 :F :Q)