(ns aoc.day03
    (:require [clojure.string :as str]
             [clojure.java.io :as io]))

(comment
  (def playground (str/split-lines "467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598.."))
  (has-symbol-around 
                    playground 6 6)
  (locate-valid-parts-in playground)
  )
(defn is-number-char?
  [character]
  (and (>= (int character) (int \0)) (<= (int character) (int \9))))
(defn assemble-number-around
  ([index text] (assemble-number-around index index text))
  ([from to text]
   (if (and (> from 0) (is-number-char? (get text (- from 1))))
     (assemble-number-around (- from 1) to text)
     (if (and (< to (- (count text) 1)) (is-number-char? (get text (+ to 1))))
       (assemble-number-around from (+ to 1) text)
       {:number (Integer/parseInt (subs text from (+ to 1))) :from from :to to}
       ))))
(defn has-symbol-around
  "Check if the current position has at least one non dot symbol around"
  [playground row col]
  (let [offsets [[-1 -1]
                 [-1 0]
                 [-1 1]
                 [0 -1]
                 [0 1]
                 [1 -1]
                 [1 0]
                 [1 1]]
        updated-positions (map 
                           (fn [offset]
                             [(+ row (get offset 0)) (+ col (get offset 1))]
                             )
                           offsets)
        possible-positions (filter
                            (fn [position]
                              (and
                              (>= (get position 0) 0)
                              (>= (get position 1) 0)
                              (< (get position 0) (count playground))
                              (< (get position 1) (count (get playground (get position 0))))
                               )
                              )
                            updated-positions)
        has-symbol (map 
                    (fn [position]
                      (let [character (get (get playground (get position 0)) (get position 1))
                        valid (and
                         (not (= character \.))
                         (not (is-number-char? character)))]
                        valid)
                      )
                    possible-positions)]
    (some true? has-symbol))
  )
(defn extract-parts-from
  [playground
   located-parts]
  ; For each line, extract numbers, then flatmap those lists
  (let [extracted-parts 
        (map-indexed 
         (fn 
           [row-index part-identifier-row]
           (let 
            [text-row (get playground row-index)
             ; This is a list of maps!
             numbers-in-list (map-indexed
                              (fn [col-index is-a-part?]
                                (if is-a-part?
                                  (assemble-number-around col-index text-row)
                                  nil))
                              part-identifier-row)
             reduced-numbers
             (reduce 
              (fn [list value]
                (if (nil? value)
                  list
                  (if (= value (peek list))
                    list
                    (conj list value)))
                )
              (vector) numbers-in-list)
             ; Fetch from the previous list of map a list of numbers
             only-numbers
             (map (fn [m] (get m :number)) reduced-numbers)
             ]
             only-numbers
             )
           )
         located-parts)]
    (flatten extracted-parts))
  )
(defn locate-valid-parts-in
  "Get all valid parts in playground"
  [playground]
  (let [numbers-with-neighbors (map-indexed
                                (fn [array-index row]
                                  (map-indexed (fn [char-index character]
                                                 (if (is-number-char? character)
                                                 (has-symbol-around playground array-index char-index)
                                                   false)
                                                 )
                                               row))
                                playground)
         ; Now we have the cells having markers, let's extract the numbers
        ]
    (extract-parts-from playground numbers-with-neighbors)) 
  )
(defn
  sum-all-parts-numbers
  [input]
  (let [
        lines (str/split-lines input)
        valid-parts (locate-valid-parts-in lines)
  ]
    (reduce + valid-parts))
  )

(def text (slurp (.getFile (io/resource "day03.txt"))))
(sum-all-parts-numbers text)

