(ns iddfs_test
  (:require [clojure.test :refer :all]
            [fyp.iddfs :refer :all]))

(def tree {:A [:B :E]
           :B [:C :D]
           :E [:F :G]
           :C [:H :I]
           :G [:J :K]})

(deftest iddfs-AtoQ
  (testing "IDDFS Completeness A to Q"
    (is (= "No Solution" (iddfs tree :A :Q)))))

(deftest iddfs-AtoA
  (testing "IDDFS Optimality A to A"
    (is (= {[:A] 0} (iddfs tree :A :A)))))

(deftest iddfs-AtoB
  (testing "IDDFS Optimality A to B"
    (is (= {[:A :B] 1} (iddfs tree :A :B)))))

(deftest iddfs-AtoC
  (testing "IDDFS Optimality A to C"
    (is (= {[:A :B :C] 2} (iddfs tree :A :C)))))

(deftest iddfs-AtoD
  (testing "IDDFS Optimality A to D"
    (is (= {[:A :B :C :D] 2} (iddfs tree :A :D)))))

(deftest iddfs-AtoE
  (testing "IDDFS Optimality A to E"
    (is (= {[:A :B :E] 1} (iddfs tree :A :E)))))

(deftest iddfs-AtoF
  (testing "IDDFS Optimality A to F"
    (is (= {[:A :B :C :D :E :F] 2} (iddfs tree :A :F)))))

(deftest iddfs-AtoG
  (testing "IDDFS Optimality A to G"
    (is (= {[:A :B :C :D :E :F :G] 2} (iddfs tree :A :G)))))

(deftest iddfs-AtoH
  (testing "IDDFS Optimality A to H"
    (is (= {[:A :B :C :H] 3} (iddfs tree :A :H)))))

(deftest iddfs-AtoI
  (testing "IDDFS Optimality A to I"
    (is (= {[:A :B :C :H :I] 3} (iddfs tree :A :I)))))

(deftest iddfs-AtoJ
  (testing "IDDFS Optimality A to J"
    (is (= {[:A :B :C :H :I :D :E :F :G :J] 3} (iddfs tree :A :J)))))

(deftest iddfs-AtoK
  (testing "IDDFS Optimality A to K"
    (is (= {[:A :B :C :H :I :D :E :F :G :J :K] 3} (iddfs tree :A :K)))))

(run-tests)
