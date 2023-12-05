(ns aoc.day04
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [clojure.set :as aset]
            [clojure.math :as math]))

(comment
  (parse-line
   "Card   1: 98 16 95 90 53 33 43  7 46 45 | 85 15 78 57 34 10 46 90 33 13  8 54  4 37 25 63 55 41  7 82 69 16 30 76  2"
   )
  )

(defn
  parse-line
  [input]
  (let [[prefix suffix] (str/split input #":")
        line-index (subs prefix (+ (str/last-index-of prefix " ") 1))
        [winning-text my-text] (str/split (str/trim suffix) #"\|")
        winning (set (filter (fn [text] (> (count text)0)) (str/split (str/trim winning-text) #" ")))
        my (set (filter (fn [text] (> (count text) 0))  (str/split (str/trim my-text) #" ")))
        ]
    {:index line-index :winning winning :my my}
    )
  )

(defn
  compute-score-of-line
  [input]
  (let [detail (parse-line input)
        inter (aset/intersection (get detail :winning) (get detail :my))
        matched-count (count inter)
        score (if (= matched-count 0)
                 0
                 (int (math/pow 2 (- matched-count 1))))
        ]
;    (println (format "%s => %s" (get detail :index) score))
    score
  )
  )

(defn
  sum-scores-of-winning-cards
  [input]
  (let [lines (str/split-lines input)
        scores (map compute-score-of-line lines)
        ]
    (reduce + scores)))

(def text (slurp (.getFile (io/resource "day04.txt"))))
(sum-scores-of-winning-cards text)
