(ns Dijkstras_test
  (:require [clojure.test :refer :all]
            [fyp.Dijkstra :refer :all]))

(def graph
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

(deftest Dijkstra-FtoQ
  (testing "Dijkstra Completeness F to Q"
    (is (= "Not Found" (Dijkstra :F :Q graph)))))

(deftest Dijkstra-KtoC
  (testing "Dijkstra Optimality K to C"
    (is (= [[:K :G :A :D :C]21] (Dijkstra :K :C graph)))))

(deftest Dijkstra-AtoC
  (testing "Dijkstra Optimality A to C"
    (is (= [[:A :D :C]7] (Dijkstra :A :C graph)))))

(deftest Dijkstra-BtoG
  (testing "Dijkstra Optimality B to G"
    (is (= [[:B :D :A :G]22] (Dijkstra :B :G graph)))))

(deftest Dijkstra-ItoH
  (testing "Dijkstra Optimality I to H"
    (is (= [[:I :H]2] (Dijkstra :I :H graph)))))

(deftest Dijkstra-JtoD
  (testing "Dijkstra Optimality J to D"
    (is (= [[:J :D]10] (Dijkstra :J :D graph)))))

(deftest Dijkstra-AtoK
  (testing "Dijkstra Optimality A to K"
    (is (= [[:A :G :K]14] (Dijkstra :A :K graph)))))

(deftest Dijkstra-CtoJ
  (testing "Dijkstra Optimality C to J"
    (is (= [[:C :D :J]13] (Dijkstra :C :J graph)))))

(deftest Dijkstra-EtoF
  (testing "Dijkstra Optimality E to F"
    (is (= [[:E :F]16] (Dijkstra :E :F graph)))))

(deftest Dijkstra-BtoF
  (testing "Dijkstra Optimality B to F"
    (is (= [[:B :E :F]37] (Dijkstra :B :F graph)))))

(run-tests)
