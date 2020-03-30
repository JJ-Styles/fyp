(ns dijkstras_test
  (:require [clojure.test :refer :all]
            [fyp.dijkstra :refer :all]))

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
    (is (= "Not Found" (Dijkstra-method :F :Q graph)))))

(deftest Dijkstra-KtoC
  (testing "Dijkstra Optimality K to C"
    (is (= (str [[:K :G :A :D :C] 21]) (Dijkstra-method :K :C graph)))))

(deftest Dijkstra-AtoC
  (testing "Dijkstra Optimality A to C"
    (is (= (str [[:A :D :C] 7]) (Dijkstra-method :A :C graph)))))

(deftest Dijkstra-BtoG
  (testing "Dijkstra Optimality B to G"
    (is (= (str [[:B :D :A :G] 22]) (Dijkstra-method :B :G graph)))))

(deftest Dijkstra-ItoH
  (testing "Dijkstra Optimality I to H"
    (is (= (str [[:I :H] 2]) (Dijkstra-method :I :H graph)))))

(deftest Dijkstra-JtoD
  (testing "Dijkstra Optimality J to D"
    (is (= (str [[:J :D] 10]) (Dijkstra-method :J :D graph)))))

(deftest Dijkstra-AtoK
  (testing "Dijkstra Optimality A to K"
    (is (= (str [[:A :G :K] 14]) (Dijkstra-method :A :K graph)))))

(deftest Dijkstra-CtoJ
  (testing "Dijkstra Optimality C to J"
    (is (= (str [[:C :D :J] 13]) (Dijkstra-method :C :J graph)))))

(deftest Dijkstra-EtoF
  (testing "Dijkstra Optimality E to F"
    (is (= (str [[:E :F] 16]) (Dijkstra-method :E :F graph)))))

(deftest Dijkstra-BtoF
  (testing "Dijkstra Optimality B to F"
    (is (= (str [[:B :E :F] 37]) (Dijkstra-method :B :F graph)))))

(run-tests)
