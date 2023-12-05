(ns aoc.day01test
    (:require [clojure.test :refer :all]
              [aoc.day01 :refer :all]))

(comment
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

(deftest find-number-in-first-row-of-second-case
  (testing "Can find numbers in first row"
    (is
     (=
      (findNumberIn "two1nine")
      29))))
(deftest find-number-in-second-row-of-second-case
  (testing "Can find numbers in second row"
    (is
     (=
      (findNumberIn "eightwothree")
      83))))
(deftest find-numbers-in-multiple-row-in-second-case
  (testing "Can extract a list of numbers from a multiline string in second case"
    (is
     (=
      (findNumberListIn "two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen")
      '(29 83 13 24 42 14 76)))))
(deftest sum-numbers-in-multiple-row-in-second-case
  (testing "Can extract a list of numbers from a multiline string in second case"
    (is
     (=
      (sumNumberListIn "two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen")
      281))))
)