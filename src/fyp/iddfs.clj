(ns fyp.iddfs)

(defn find-neighbours
  [v coll]
  (get coll v))

(defn visited?
  [v coll]
  (some #(= % v) coll))

(defn dls
  [graph start goal depth]
  (cond
    (= depth 0)
    (if (= goal start)
      [start]
      [])
    :else
      (loop [stack (vector start)
             visited []
             depth-gone 0]
        (if (empty? stack)
          []
          (if (= goal (peek stack))
            (conj visited (peek stack))
            (if (= depth depth-gone)
              (if (empty? stack)
                []
                (if (> depth-gone (count (pop stack)))
                  (recur (pop stack) (conj visited (peek stack)) (- depth-gone 1))
                  (recur (pop stack) (conj visited (peek stack)) depth-gone)))
              (let [node (peek stack)
                    neighbours (-> (find-neighbours node graph) reverse)
                    not-visited (filter (complement #(visited? % visited)) neighbours)
                    new-stack (into (if (= depth-gone 0)
                                      stack
                                      (pop stack))
                                    not-visited)]
                (if (visited? node visited)
                  (recur new-stack visited (+ depth-gone 1))
                  (recur
                      new-stack
                    (conj visited node)
                    (if (empty? not-visited)
                      (- depth-gone 1)
                      (+ depth-gone 1)))))))))))

(defn dls2
  [graph node goal depth path]
  (cond
    (= depth 0)
    (conj path node)
    :else
    (let [neighbours (-> (find-neighbours node graph) reverse)
          not-visited (filter (complement #(visited? % path)) neighbours)
          new-depth (- depth 1)
          new-path (conj path node)]
      (for [neighbour not-visited]
        (let [new-path (dls2 graph neighbour goal new-depth new-path)]
          (if (= goal (last new-path))
            path
            ))))))


(defn iddfs
  [graph start goal]
  (let [total-depth (count graph)]
    (loop [path (dls2 graph start goal 0 []) depth 0]
      (cond
        (empty? path)
        "No Solution"
        (not (= depth total-depth))
        (recur (dls2 graph start goal (+ depth 1) []) (+ depth 1))
        :else
        {path, depth}))))

(def tree {:A [:B :E]
           :B [:C :D]
           :E [:F :G]
           :C [:H :I]
           :G [:J :K]})

(dls2 tree :A :K 3 [])
(iddfs tree :A :K)
(iddfs tree :A :Q)