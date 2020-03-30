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
    (is (= "No Solution" (dfs-method tree :A :Q)))))

(deftest dfs-AtoA
  (testing "DFS Optimality A to A"
    (is (= (str [:A]) (dfs-method tree :A :A)))))

(deftest dfs-AtoB
  (testing "DFS Optimality A to B"
    (is (= (str [:A :B]) (dfs-method tree :A :B)))))

(deftest dfs-AtoC
  (testing "DFS Optimality A to C"
    (is (= (str [:A :B :C]) (dfs-method tree :A :C)))))

(deftest dfs-AtoE
  (testing "DFS Optimality A to E"
    (is (= (str [:A :B :C :H :I :D :E]) (dfs-method tree :A :E)))))

(deftest dfs-AtoF
  (testing "DFS Optimality A to F"
    (is (= (str [:A :B :C :H :I :D :E :F]) (dfs-method tree :A :F)))))

(deftest dfs-AtoG
  (testing "DFS Optimality A to G"
    (is (= (str [:A :B :C :H :I :D :E :F :G]) (dfs-method tree :A :G)))))

(deftest dfs-AtoH
  (testing "DFS Optimality A to H"
    (is (= (str [:A :B :C :H]) (dfs-method tree :A :H)))))

(deftest dfs-AtoI
  (testing "DFS Optimality A to I"
    (is (= (str [:A :B :C :H :I]) (dfs-method tree :A :I)))))

(deftest dfs-AtoJ
  (testing "DFS Optimality A to J"
    (is (= (str [:A :B :C :H :I :D :E :F :G :J]) (dfs-method tree :A :J)))))

(deftest dfs-AtoK
  (testing "DFS Optimality A to K"
    (is (= (str [:A :B :C :H :I :D :E :F :G :J :K]) (dfs-method tree :A :K)))))

(run-tests)