(ns aoc.day02
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))


(comment
(read-available-pieces 
 "12 red cubes, 13 green cubes, and 14 blue cubes") 
  (let
   [available (read-available-pieces "12 red cubes, 13 green cubes, and 14 blue cubes")
    game1 (read-game "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")
    game4 (read-game "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red")
    game1possible (is-game-possible available game1)
    game4possible (is-game-possible available game4)
    ]
    game4possible
    )
  

  (read-games-list
          "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
   Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
   Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
   Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
   Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
)
  )
(defn is-game-possible
  "At each turn, the number of cubes should be lower than the
   the number of available cubes"
  [available game]
  (let [turns_list (get game :turns)
        turns_possible (map (fn
                              [turn]
                              (and
                               (<= (get turn "red" 0) (get available "red" 0))
                               (<= (get turn "green" 0) (get available "green" 0))
                               (<= (get turn "blue" 0) (get available "blue" 0))))
                            turns_list)]
    (reduce (fn [a b] (and a b)) true turns_possible)
    )
  ) 
(defn filter-possible-games
  "Filter the games which are possible using the available cubes"
  [available
   games]
  (filter (fn [game] (is-game-possible available game)) games)
  )

(defn read-available-pieces
  "Read the available pieces text"
  [available-pieces-description]
  (let [matches
        (re-matches
         #"(\d+) red cubes, (\d+) green cubes, and (\d+) blue cubes"
         available-pieces-description)]
    {"red" (Integer/parseInt (get matches 1))
     "green" (Integer/parseInt (get matches 2))
     "blue" (Integer/parseInt (get matches 3))}))
(defn read-game
  "Read one game row consisting of a game id followed by some game turns descriptions"
  [game-line]
  (let [header-and-turns (str/split game-line #":")
        header (get header-and-turns 0)
        game-index (Integer/parseInt (re-find #"\d+" header))
        turns-text (str/split (get header-and-turns 1) #";")
        turns (map
               (fn [text]
                 (let [moves (str/split (str/trim text) #",")
                       moves-pairs (mapv
                                    (fn
                                      [move]
                                      (let [a (str/split (str/trim move) #" ")
                                            color (get a 1)
                                            number (Integer/parseInt (get a 0))]
                                        [color number]))
                                    moves)
                       moves-map (into {} moves-pairs)]
                   moves-map))
               turns-text)]
    {:game-index game-index
     :turns turns}))
(defn read-games-list
  "Read a list of game descriptions"
  [games-list]
  (let [lines (str/split-lines games-list)
        result (map read-game lines)]
    result))

(defn sum-ids-of-possible-games
  "Sum up the ids of possible games"
  [games-descriptions
   available-pieces-description]
  (let [available (read-available-pieces available-pieces-description)
        games (read-games-list games-descriptions)
        possible-games (filter-possible-games available games)
        possible-games-ids (map (fn [g] (get g :game-index)) possible-games)]
    (reduce + possible-games-ids)))

(defn compute-minimum-set
  [game]
  (let [turns (get game :turns)]
    (reduce (fn 
              [minimum turn]
              {"red" (max (get minimum "red" 0)(get turn "red" 0))
               "green" (max (get minimum "green" 0) (get turn "green" 0))
               "blue" (max (get minimum "blue" 0) (get turn "blue" 0))})
            {
             "red" 0
             "green" 0
             "blue" 0
            }
            turns)))

(defn compute-power 
  [map-of-minimums]
  (reduce * (vals map-of-minimums)))
(defn powers-of-minimum-for-all-sets
  "Get the powers of all sets"
  [games-descriptions]
  (let [games (read-games-list games-descriptions)
        minimum-sets (map compute-minimum-set games)
        powers (map compute-power minimum-sets)]
    (reduce + powers)))

(comment
(read-available-pieces 
 "12 red cubes, 13 green cubes, and 14 blue cubes") 
  (let
   [available (read-available-pieces "12 red cubes, 13 green cubes, and 14 blue cubes")
    game1 (read-game "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")
    game4 (read-game "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red")
    game1minimum (compute-minimum-set game1)
    game4minimum (compute-minimum-set game4)
    game1power (compute-power game1minimum)
    game4power (compute-power game4minimum)
    ]
    game1power
    ))


(comment
(def text (slurp (.getFile (io/resource "day02.txt"))))

(powers-of-minimum-for-all-sets
 text)
  )