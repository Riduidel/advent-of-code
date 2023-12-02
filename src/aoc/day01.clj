(ns aoc.day01
    (:require [clojure.string :as str]
               [clojure.java.io :as io]))


(comment
  (+ 1 2)
  (findNumberIn "Treb7uchet")
  (findNumberListIn "1abc2
                     Treb7uchet")
  )
(defn findNumberIn
  "Find the numbers in the given string"
  [text]
  (def replaced (str/replace text #"\D" ""))
  (def numberString (str (first replaced)(last replaced)))
  (Integer/parseInt numberString))

(defn findNumberListIn
  "Convert each row to number by applying the findNumberIn to each row"
  [text]
  (def lines (str/split-lines text))
  (map findNumberIn lines)
  )

(defn sumNumberListIn
  "Extract numbers in each row, then sum them up"
  [text]
  (def numbers (findNumberListIn text))
  (reduce + numbers))

(def text (slurp (.getFile (io/resource "day01.txt"))))

(sumNumberListIn text)