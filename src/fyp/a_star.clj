(ns fyp.a-star
  (:use [clojure.data.priority-map])
  (:gen-class
    :methods [#^{:static true} [aStarMethod [String String String String] String]]))

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
  [coll heuristic start]
  (loop [val (first coll) col coll best val]
    (if (empty? col)
      best
      (if (< (+ (last val) (+ (h (first val) heuristic) (h start heuristic))) (+ (last best) (+ (h (first best) heuristic) (h start heuristic))))
        (recur (first (rest col)) (rest col) val)
        (recur (first (rest col)) (rest col) best)))))

(defn not-visited
  [neighbours path]
  (let [filtered-set (remove #(visited? % path) (for [neighbour neighbours] (conj (first neighbour))))
        not-visited-neighbours (for [neighbour filtered-set]
                                 (conj [neighbour (get neighbours neighbour)]))]
    not-visited-neighbours))

(defn a-star-method
  [start goal graph heur]
  (let [goal? (if (fn? goal) goal #(= goal %))]
    (loop [open (priority-map [start [start] 0] (h start heur))]
      (let [[[node path distance] total] (first open)]
        (cond
          (goal? node)
          (str [path distance])
          (empty? (not-visited (find-neighbours node graph) path))
          "Not Found"
          :else
          (recur (let [neighbours (find-neighbours node graph)
                       new-node (shortest-path-heuristics (not-visited neighbours path) heur node)
                       new-distance (+ distance (last new-node))
                       new-path (conj path (first new-node))
                       new-total (+ new-distance (h (first new-node) heur))
                       new-open (priority-map [(first new-node) new-path new-distance] new-total)]
                   new-open))
          )))))

(defn -aStarMethod [sta go gra heu]
  (let [start (keyword sta)
        goal (keyword go)
        graph (clojure.edn/read-string (str "{" gra "}"))
        heur (clojure.edn/read-string (str "{" heu "}"))]
    (a-star-method start goal graph heur)))