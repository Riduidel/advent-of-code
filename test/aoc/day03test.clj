(ns aoc.day03test
      (:require [clojure.test :refer :all]
                [clojure.string :as str]
             [aoc.day03 :refer :all]))

(deftest can-locate-parts
  (testing "We can locate all parts"
    (is
     (=
      (locate-valid-parts-in
       (str/split-lines "467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598.."))
      (list 467 35 633 617 592 755 664 598))))
  )
(deftest can-sum-all-parts-ids
  (testing "We can create the result"
    (is
     (=
      (sum-all-parts-numbers 
       "467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..")
      4361))))
