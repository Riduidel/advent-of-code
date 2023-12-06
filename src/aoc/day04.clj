(ns aoc.day04
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [clojure.set :as aset]
            [clojure.math :as math]))

(comment
  (def cards {
              :1 (parse-line "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")
  })
   (count-all-winning-cards "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
  Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
  Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
  Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
  Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
  Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11")
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
    {:index line-index :winning winning :my my :line input}
    )
  )

(defn
  compute-score-of-card
  [detail]
  (let [
        inter (aset/intersection (get detail :winning) (get detail :my))
        matched-count (count inter)
        score (if (= matched-count 0)
                0
                (int (math/pow 2 (- matched-count 1))))
        ]
    score
  )
  )

(defn
  sum-scores-of-winning-cards
  [input]
  (let [lines (str/split-lines input)
        cards (map parse-line lines)
        scores (map compute-score-of-card cards)
        ]
    (reduce + scores)))

(defn extend-with-score
  [c]
  (let [score {:score (compute-score-of-card c)}]
    (merge c score)
    )
)
(defn repeat-winning-cards-in
  [input-list index]
  (if (= index (count input-list))
    input-list
    (let [examined (nth input-list index)
          size-of-won-cards (min (get examined :score)(- (count input-list) index))
          won-cards (take size-of-won-cards input-list)
          updated-list (concat input-list won-cards)]
      (repeat-winning-cards-in updated-list (inc index)))
    ))
(defn
  count-all-winning-cards
  [input]
  (let [lines (str/split-lines input)
        cards (map parse-line lines)
        scored-cards (map extend-with-score cards)
        repeated-cards (repeat-winning-cards-in scored-cards 0)
        ]
    repeated-cards
    ))

(def text (slurp (.getFile (io/resource "day04.txt"))))
(sum-scores-of-winning-cards text)
