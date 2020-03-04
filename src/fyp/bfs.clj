(ns fyp.bfs
  (:import (clojure.lang PersistentQueue)))

(defn find-neighbours
  [v coll]
  (get coll v))

(defn visited?
  [v coll]
  (some #(= % v) coll))

(defn bfs
      [graph start goal]
      (loop [queue (conj PersistentQueue/EMPTY start)
             visited []]
            (if (empty? queue)
              "No Solution"
              (if (= goal (peek queue))
                (conj visited (peek queue))
                (let [ node (peek queue)
                      neighbours (find-neighbours node graph)
                      not-visited (filter (complement #(visited? % visited)) neighbours)
                      new-queue (apply conj (pop queue) not-visited)]
                     (if (visited? node visited)
                       (recur new-queue visited)
                       (recur new-queue (conj visited node))))))))