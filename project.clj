(defproject advent-of-code-2023 "0.1-SNAPSHOT"
  :description "Some solutions to some of the advent of code"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]]
  :repl-options {:init-ns aoc-clj.core} 
  :profiles {:kaocha {:dependencies [[lambdaisland/kaocha "1.87.1366"]]}}
  :aliases {"kaocha" ["with-profile" "+kaocha" "run" "-m" "kaocha.runner"]})