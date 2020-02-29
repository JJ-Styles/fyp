(ns iddfs)

(defn find-neighbours
  [v coll]
  (get coll v))

(defn visited?
  [v coll]
  (some #(= % v) coll))

(def graph {:A [:B :E]
            :B [:C :D]
            :E [:F :G]
            :C [:H :I]
            :G [:K :J]})

(defn dls
  [graph start goal depth]
  (cond
    (= depth 0)
    (if (= goal start)
      start
      [])
    :else
    (if (> depth 0)
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
                (if (>= depth-gone (count stack))
                  (recur (pop stack) (conj visited (peek stack)) (- depth-gone 1))
                  (recur (pop stack) (conj visited (peek stack)) depth-gone)))
              (let [node (peek stack)
                    neighbours (-> (find-neighbours node graph) reverse)
                    not-visited (filter (complement #(visited? % visited)) neighbours)
                    new-stack (into (pop stack) not-visited)]
                (if (visited? node visited)
                  (recur new-stack visited (+ depth-gone 1))
                  (recur
                    (if (empty? not-visited)
                      (into [start] new-stack)
                      new-stack)
                    (conj visited node)
                    (if (empty? not-visited)
                      (- depth-gone 1)
                      (+ depth-gone 1))))))))))))

(defn iddfs
  [graph start goal]
  (let [total-depth (count graph)]
    (loop [path (dls graph start goal 0) depth 0]
      (cond
        (not (empty? path))
        {path, depth}
        (not (= depth total-depth))
        (recur (dls graph start goal (+ depth 1)) (+ depth 1))
        :else
        nil))))

(iddfs graph :A :G)