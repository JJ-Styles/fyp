(ns dfs_test
  (:require [clojure.test :refer :all]
            [fyp.dfs :refer :all]))

(def tree {:A [:B :E]
           :B [:C :D]
           :E [:F :G]
           :C [:H :I]
           :G [:J :K]})

(deftest dfs-AtoQ
  (testing "DFS Completeness A to Q"
    (is (= "No Solution" (dfs tree :A :Q)))))

(deftest dfs-AtoA
  (testing "DFS Optimality A to A"
    (is (= [:A] (dfs tree :A :A)))))

(deftest dfs-AtoB
  (testing "DFS Optimality A to B"
    (is (= [:A :B] (dfs tree :A :B)))))

(deftest dfs-AtoC
  (testing "DFS Optimality A to C"
    (is (= [:A :B :C] (dfs tree :A :C)))))

(deftest dfs-AtoE
  (testing "DFS Optimality A to E"
    (is (= [:A :B :C :H :I :D :E] (dfs tree :A :E)))))

(deftest dfs-AtoF
  (testing "DFS Optimality A to F"
    (is (= [:A :B :C :H :I :D :E :F] (dfs tree :A :F)))))

(deftest dfs-AtoG
  (testing "DFS Optimality A to G"
    (is (= [:A :B :C :H :I :D :E :F :G] (dfs tree :A :G)))))

(deftest dfs-AtoH
  (testing "DFS Optimality A to H"
    (is (= [:A :B :C :H] (dfs tree :A :H)))))

(deftest dfs-AtoI
  (testing "DFS Optimality A to I"
    (is (= [:A :B :C :H :I] (dfs tree :A :I)))))

(deftest dfs-AtoJ
  (testing "DFS Optimality A to J"
    (is (= [:A :B :C :H :I :D :E :F :G :J] (dfs tree :A :J)))))

(deftest dfs-AtoK
  (testing "DFS Optimality A to K"
    (is (= [:A :B :C :H :I :D :E :F :G :J :K] (dfs tree :A :K)))))

(run-tests)