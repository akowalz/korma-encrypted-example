(ns korma-encrypted-example.core
  (:require [korma-encrypted-example.models.credit-card :refer [credit-cards]]
            [korma-encrypted-example.db :refer [dev]]
            [korma.core :refer :all]
            [korma.db :refer [with-db]]
            [korma-encrypted.core :refer [update-encrypted-entity]]))

(defn -main []
  (with-db dev
    (println "Creating credit card record.")
    (let [stored (insert credit-cards
                         (values {:name "Bob Jim" :number "1111222233334444"}))]
      (println "Encrypted credit card record created successfully")

      (println "Retrieving raw database record:")
      (println
        (str "Raw record retrieved, encrypted number: "
             (:encrypted_number
               (first (exec-raw ["SELECT * FROM credit_cards where pk = ?;" [(:pk stored)]] :results)))))


      (println "Retrieving record")
      (println
        (str "Credit card retrieved, number: "
             (:number
               (first (select credit-cards
                              (where {:pk (:pk stored)})))))))))
