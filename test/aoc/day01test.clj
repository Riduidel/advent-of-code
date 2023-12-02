(ns aoc.day01test
    (:require [clojure.test :refer :all]
              [aoc.day01 :refer :all]))

(deftest find-number-in-first-row
  (testing "Can find numbers in first row"
    (is 
     (= 
      (findNumberIn "1abc2")
      12 ))))

(deftest find-number-in-second-row
  (testing "Can find numbers in second row"
    (is
     (=
      (findNumberIn "pqr3stu8vwx")
      38))))

(deftest find-number-in-last-row
  (testing "Can find numbers in last row"
    (is
     (=
      (findNumberIn "treb7uchet")
      77))))

(deftest find-numbers-in-multiple-row
  (testing "Can extract a list of numbers from a multiline string"
    (is
     (=
      (findNumberListIn "1abc2
                         treb7uchet")
      '(12 77)))))

(deftest sum-numbers-in-multiple-row
  (testing "Can extract a list of numbers from a multiline string"
    (is
     (=
      (sumNumberListIn "1abc2
                         treb7uchet")
      89))))

