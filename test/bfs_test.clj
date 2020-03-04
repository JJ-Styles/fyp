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
    (is (= "No Solution" (bfs tree :A :Q)))))

(deftest bfs-AtoA
  (testing "BFS Optimality A to A"
    (is (= [:A] (bfs tree :A :A)))))

(deftest bfs-AtoB
  (testing "BFS Optimality A to B"
    (is (= [:A :B] (bfs tree :A :B)))))

(deftest bfs-AtoC
  (testing "BFS Optimality A to C"
    (is (= [:A :B :E :C] (bfs tree :A :C)))))

(deftest bfs-AtoE
  (testing "BFS Optimality A to E"
    (is (= [:A :B :E] (bfs tree :A :E)))))

(deftest bfs-AtoF
  (testing "BFS Optimality A to F"
    (is (= [:A :B :E :C :D :F] (bfs tree :A :F)))))

(deftest bfs-AtoG
  (testing "BFS Optimality A to G"
    (is (= [:A :B :E :C :D :F :G] (bfs tree :A :G)))))

(deftest bfs-AtoH
  (testing "BFS Optimality A to H"
    (is (= [:A :B :E :C :D :F :G :H] (bfs tree :A :H)))))

(deftest bfs-AtoI
  (testing "BFS Optimality A to I"
    (is (= [:A :B :E :C :D :F :G :H :I] (bfs tree :A :I)))))

(deftest bfs-AtoJ
  (testing "BFS Optimality A to J"
    (is (= [:A :B :E :C :D :F :G :H :I :J] (bfs tree :A :J)))))

(deftest bfs-AtoK
  (testing "BFS Optimality A to K"
    (is (= [:A :B :E :C :D :F :G :H :I :J :K] (bfs tree :A :K)))))

(run-tests)