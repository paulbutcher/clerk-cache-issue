(ns hello
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [clojure.string :as string]
            [nextjournal.clerk :as clerk]))

(def csv-data (with-open [reader (io/reader "20210506_1625_10.csv")]
                                   (doall
                                    (csv/read-csv reader :separator \;))))

(def raw-titles (first csv-data))
(def data (rest csv-data))
(def titles (map #(keyword (string/trim (last (string/split % #"[\._]")))) raw-titles))

(defn toDouble [x]
  (if (string/blank? x) ##NaN (Double/parseDouble x)))

(def tx (comp
         (map #(map toDouble %))
         (map #(zipmap titles %))))

(into [] tx data)
