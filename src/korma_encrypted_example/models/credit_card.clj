(ns korma-encrypted-example.models.credit-card
  (:require [korma.core :refer :all]
            [korma-encrypted.core :refer [encrypted-field]]
            [korma-encrypted-example.key-service :refer [key-service]]))

(defentity credit-cards
  (pk :pk)
  (table :credit_cards)
  (encrypted-field :number key-service))
