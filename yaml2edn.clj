#!/usr/bin/env bb
(require '[clj-yaml.core :as yaml])
(require '[clojure.java.io :as io])

(defn filter-nested [root keys-to-remove]
  (let [should-remove? (set keys-to-remove)
        ;; A recursive function to search through the map
        f (fn rec [node]
            (reduce-kv (fn [acc k v]
                         (cond
                           ;; If it's in the set, remove the key from the node
                           (should-remove? k) (dissoc acc k)

                           ;; If the value is a map, recursively search it too
                           (map? v) (assoc acc k (rec v))

                           ;; If it's a vector, map a recursive call over the vector
                           (vector? v) (assoc acc k (mapv rec v))

                           ;; Else do nothing
                           :else acc))
                       node
                       node))]
    (f root)))

(-> (io/reader *in*)
    (slurp)
    (yaml/parse-string)
    (filter-nested #{:description :examples :summary})
    (yaml/generate-string)
    (println))
