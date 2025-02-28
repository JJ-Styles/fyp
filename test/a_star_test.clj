(ns a_star_test
  (:require [clojure.test :refer :all]
            [fyp.a-star :refer :all]))

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

(def heuristics-to-C
  {
   :A 7
   :B 11
   :C 0
   :D 3
   :E 17
   :F 33
   :G 20
   :H 13
   :I 14
   :J 13
   :K 23
   })

(def heuristics-to-I
  {
   :A 20
   :B 25
   :C 14
   :D 16
   :E 30
   :F 37
   :G 13
   :H 2
   :I 0
   :J 6
   :K 16
   })

(def heuristics-to-J
  {
   :A 14
   :B 19
   :C 13
   :D 10
   :E 25
   :F 31
   :G 7
   :H 4
   :I 6
   :J 0
   :K 10
   })

(deftest A-Complete
  (testing "A* Completeness F to Q"
    (is (= "Not Found" (a* :F :Q graph heuristics-to-C)))))

(deftest A-KtoC
  (testing "A* Optimality K to C"
    (is (= [[:K :J :D :C]23] (a* :K :C graph heuristics-to-C)))))

(deftest A-FtoC
  (testing "A* Optimality F to C"
    (is (= [[:F :E :A :D :C]33] (a* :F :C graph heuristics-to-C)))))

(deftest A-GtoC
  (testing "A* Optimality G to C"
    (is (= [[:G :A :D :C]16] (a* :G :C graph heuristics-to-C)))))

(deftest A-HtoC
  (testing "A* Optimality H to C"
    (is (= [[:H :C]13] (a* :H :C graph heuristics-to-C)))))

(deftest A-FtoI
  (testing "A* Optimality F to I"
    (is (= [[:F :G :J :H :I] 37] (a* :F :I graph heuristics-to-I)))))

(deftest A-DtoI
  (testing "A* Optimality D to I"
    (is (= [[:D :J :H :I]16] (a* :D :I graph heuristics-to-I)))))

(deftest A-KtoI
  (testing "A* Optimality K to I"
    (is (= [[:K :J :H :I]16] (a* :K :I graph heuristics-to-I)))))

(deftest A-BtoI
  (testing "A* Optimality B to I"
    (is (= [[:B :C :I]25] (a* :B :I graph heuristics-to-I)))))

(deftest A-FtoJ
  (testing "A* Optimality F to J"
    (is (= [[:F :G :J] 31] (a* :F :J graph heuristics-to-J)))))

(deftest A-BtoJ
  (testing "A* Optimality B to J"
    (is (= [[:B :D :J]19] (a* :B :J graph heuristics-to-J)))))

(deftest A-EtoJ
  (testing "A* Optimality E to J"
    (is (= [[:E :A :D :J]24] (a* :E :J graph heuristics-to-J)))))

(deftest A-GtoJ
  (testing "A* Optimality G to J"
    (is (= [[:G :J]7] (a* :G :J graph heuristics-to-J)))))

(run-tests)