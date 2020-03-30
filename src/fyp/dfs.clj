(ns fyp.dfs
  (:gen-class
    :methods [#^{:static true} [dfsMethod [String String String] String]]))

(defn find-neighbours
  [v coll]
  (get coll v))

(defn visited?
  [v coll]
  (some #(= % v) coll))

(defn dfs-method
  [graph start goal]
  (loop [stack (vector start)
         visited []]
    (if (empty? stack)
      "Not Found"
      (if (= goal (peek stack))
        (str (conj visited (peek stack)))
        (let [node (peek stack)
              neighbours (-> (find-neighbours node graph) reverse)
              not-visited (filter (complement #(visited? % visited)) neighbours)
              new-stack (into (pop stack) not-visited)]
          (if (visited? node visited)
            (recur new-stack visited)
            (recur new-stack (conj visited node))))))))

(defn -dfsMethod [gra sta go]
  (let
    [graph (clojure.edn/read-string (str "{" gra "}"))
     start (keyword sta)
     goal (keyword go)]
    (dfs-method graph start goal)))