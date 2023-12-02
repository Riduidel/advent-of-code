(ns aoc.day01
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))


(comment
  (+ 1 2)
  (findNumberIn "two1nine")
  (replaceNumberTextIn "two1nine")
  (findNumberIn "Treb7uchet")
  (findNumberIn "eightwothree")
  (findNumberListIn "1abc2
                     Treb7uchet"))

(defn replaceNumberTextIn
  "Replace all texts of numbers by digits"
  [text]
  (reduce-kv (fn [t key value] (str/replace t (str key) (str value)))
   text
    ; Why that weird replacement ? because there is at least 
             ; one case with eightwo
             ; two gets replaced, so eight cant
             ; to avoid that, just rewrite the letters with the number in
   {"one" "o1ne" "two" "t2wo" "three" "t3hree" "four" "f4our" 
    "five" "f5ive" "six" "s6ix" "seven" "s7even" 
    "eight" "e8ight" "nine" "n9ine" "zero" "z0ero"}
   )
)

(defn findNumberIn
  "Find the numbers in the given string"
  [text]
  (let [text (replaceNumberTextIn text)
        replaced (str/replace text #"\D" "")
        numberString (str (first replaced) (last replaced))]
      (Integer/parseInt numberString)))

(defn findNumberListIn
  "Convert each row to number by applying the findNumberIn to each row"
  [text]
  (let [lines (str/split-lines text)]
    (map findNumberIn lines)))

(defn sumNumberListIn
  "Extract numbers in each row, then sum them up"
  [text]
  (let [numbers (findNumberListIn text)]
    (reduce + numbers)))

(def text (slurp (.getFile (io/resource "day01-1.txt"))))

(sumNumberListIn text)