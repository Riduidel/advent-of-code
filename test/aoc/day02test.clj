(ns aoc.day02test
    (:require [clojure.test :refer :all]
              [aoc.day02 :refer :all]))

(comment
(deftest can-read-available-pieces
  (testing "The available pieces text can be read"
    (is 
     (= 
      (read-available-pieces
       "12 red cubes, 13 green cubes, and 14 blue cubes")
      {"red" 12 "green" 13 "blue" 14} ))))

(deftest get-sum-of-possible-gamesids
  (testing "The sum of ids of games is valid"
    (is 
     (= 
      (sum-ids-of-possible-games 
       "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
       "12 red cubes, 13 green cubes, and 14 blue cubes")
      8 ))))

(deftest get-powers-of-possible-gamesids
  (testing "The sum of ids of games is valid"
    (is
     (=
      (powers-of-minimum-for-all-sets
       "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")
      2286))))
)