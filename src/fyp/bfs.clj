(ns fyp.bfs
  (:import (clojure.lang PersistentQueue))
  (:gen-class
    :methods [#^{:static true} [bfsMethod [String String String] String]]))

(defn find-neighbours
  [v coll]
  (get coll v))

(defn visited?
  [v coll]
  (some #(= % v) coll))

(defn bfs-method
      [graph start goal]
      (loop [queue (conj PersistentQueue/EMPTY start)
             visited []]
            (if (empty? queue)
              "Not Found"
              (if (= goal (peek queue))
                (str (conj visited (peek queue)))
                (let [ node (peek queue)
                      neighbours (find-neighbours node graph)
                      not-visited (filter (complement #(visited? % visited)) neighbours)
                      new-queue (apply conj (pop queue) not-visited)]
                     (if (visited? node visited)
                       (recur new-queue visited)
                       (recur new-queue (conj visited node))))))))

(defn -bfsMethod [gra sta go]
  (let
    [graph (clojure.edn/read-string (str "{" gra "}"))
     start (keyword sta)
     goal (keyword go)]
    (bfs-method graph start goal)))