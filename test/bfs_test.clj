(ns bfs_test
  (:require [clojure.test :refer :all]
            [fyp.bfs :refer :all]))


(def tree {:A [:B :E]
            :B [:C :D]
            :E [:F :G]
            :C [:H :I]
            :G [:J :K]})

(deftest bfs-AtoQ
  (testing "BFS Completeness A to Q"
    (is (= "No Solution" (bfs-method tree :A :Q)))))

(deftest bfs-AtoA
  (testing "BFS Optimality A to A"
    (is (= (str [:A]) (bfs-method tree :A :A)))))

(deftest bfs-AtoB
  (testing "BFS Optimality A to B"
    (is (= (str [:A :B]) (bfs-method tree :A :B)))))

(deftest bfs-AtoC
  (testing "BFS Optimality A to C"
    (is (= (str [:A :B :E :C]) (bfs-method tree :A :C)))))

(deftest bfs-AtoE
  (testing "BFS Optimality A to E"
    (is (= (str [:A :B :E]) (bfs-method tree :A :E)))))

(deftest bfs-AtoF
  (testing "BFS Optimality A to F"
    (is (= (str [:A :B :E :C :D :F]) (bfs-method tree :A :F)))))

(deftest bfs-AtoG
  (testing "BFS Optimality A to G"
    (is (= (str [:A :B :E :C :D :F :G]) (bfs-method tree :A :G)))))

(deftest bfs-AtoH
  (testing "BFS Optimality A to H"
    (is (= (str [:A :B :E :C :D :F :G :H]) (bfs-method tree :A :H)))))

(deftest bfs-AtoI
  (testing "BFS Optimality A to I"
    (is (= (str [:A :B :E :C :D :F :G :H :I]) (bfs-method tree :A :I)))))

(deftest bfs-AtoJ
  (testing "BFS Optimality A to J"
    (is (= (str [:A :B :E :C :D :F :G :H :I :J]) (bfs-method tree :A :J)))))

(deftest bfs-AtoK
  (testing "BFS Optimality A to K"
    (is (= (str [:A :B :E :C :D :F :G :H :I :J :K]) (bfs-method tree :A :K)))))

(run-tests)