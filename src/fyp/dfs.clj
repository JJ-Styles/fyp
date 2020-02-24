(ns dfs)

(defn find-neighbours
  [v coll]
  (get coll v))

(defn visited?
  [v coll]
  (some #(= % v) coll))

(def graph {:A [:B :C]
            :B [:A :X]
            :X [:B :Y]
            :Y [:X]
            :C [:A :D]
            :D [:C :E :F]
            :E [:D :G]
            :F [:D]
            :G [:E]})

(defn dfs
  [graph start goal]
  (loop [stack (vector start)
         visited []]
    (if (empty? stack)
      "No Solution"
      (if (= goal (peek stack))
        (conj visited (peek stack))
        (let [node (peek stack)
              neighbours (-> (find-neighbours node graph) reverse)
              not-visited (filter (complement #(visited? % visited)) neighbours)
              new-stack (into (pop stack) not-visited)]
          (if (visited? node visited)
            (recur new-stack visited)
            (recur new-stack (conj visited node))))))))

(dfs graph :A :F)