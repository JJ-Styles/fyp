(ns fyp.testfile
  (:gen-class
    :methods [#^{:static true} [add [int int] int]]))

(defn hello_world []
  (println "Hello World"))

(defn add [int1 int2]
  (+ int1 int2))

(defn -hello_world []
  (hello_world))

(defn -add [int1 int2]
  (add int1 int2))

(defn -main [& args]
  (println (hello_world))
  (println (str "(add 5 3): " (add 5 3)))
  (println (str "(add 4524520 15461): " (add 4524520 15461)))
  )
