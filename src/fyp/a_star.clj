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

(defn h
  [key heur]
  (get heur key))

(defn a-star-method
  [start goal graph heur]
  (let [goal? (if (fn? goal) goal #(= goal %))]
    (loop [closed [] open (priority-map [start [start] 0] (h start heur))]
      (let [[[node path distance] total] (first open)]
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
                                              new-distance (+ distance (last neighbour))
                                              new-heur (+ new-distance (h new-node heur))]
                                        :when (not (visited? new-node closed))]
                                    [[new-node new-path new-distance] new-heur])))
          )))))

(defn -aStarMethod [sta go gra heu]
  (let [start (keyword sta)
        goal (keyword go)
        graph (clojure.edn/read-string (str "{" gra "}"))
        heur (clojure.edn/read-string (str "{" heu "}"))]
    (a-star-method start goal graph heur)))