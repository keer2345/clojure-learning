(ns ch04.core
  (:gen-class))

(defn who-are-you
  [input]
  (cond
    (= java.lang.String (class input))     "String - Who are you?"
    (= clojure.lang.Keyword (class input)) "Keyword - Who are you?"
    (= java.lang.Long (class input))       "Number - Who are you?"))

(defmulti who-are-you-2 class)

(defmethod who-are-you-2 java.lang.String [input]
  (str "String - Who are you 2? " input))

(defmethod who-are-you-2 clojure.lang.Keyword [input]
  (str "Keyword - Who are you 2? " input))

(defmethod who-are-you-2 java.lang.Long [input]
  (str "Number - Who are you 2? " input))

(defmethod who-are-you-2 :default [input]
  (str "I don't konw - who are you? " input))

(defmulti eat-mushroom #(if (< % 3) :grow :shrink))

(defmethod eat-mushroom :grow [_]
  "Eat the right side to grow.")

(defmethod eat-mushroom :shrink [_]
  "Eat the left side to shrink.")

(defprotocol BigMushroom
  (eat-mushroom-2 [this]))

(extend-protocol BigMushroom
  java.lang.String
  (eat-mushroom-2 [this]
    (str (.toUpperCase this) " mmmm tasty!"))
  clojure.lang.Keyword
  (eat-mushroom-2 [this]
    (case this
      :grow   "Eat grow"
      :shrink "Eat shrink"))
  java.lang.Long
  (eat-mushroom-2 [this]
    (if (< this 8)
      "Eat less than 8"
      "Eat more than 8")))

(defrecord Mushroom [color height])

(defprotocol Edible
  (bite-right-side [this])
  (bite-left-side [this]))

(defrecord WonderlandMushroom [color height]
  Edible
  (bite-right-side [this]
    (str "The " color " bite make you grow bigger"))
  (bite-left-side [this]
    (str "The " color " bite makes you grow smaller")))

(defrecord RegularMushroom [color height]
  Edible
  (bite-right-side [this]
    (str "The " color " bite tastes bad"))
  (bite-left-side [this]
    (str "The " color " bite tastes bad too")))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
