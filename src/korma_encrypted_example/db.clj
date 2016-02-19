(ns korma-encrypted-example.db
  (:require [clojure.java.jdbc :as jdbc]
            [korma.db]
            [korma-encrypted-example.key-service :refer [key-service]]
            [korma-encrypted.core :refer [generate-and-save-data-encryption-key]]))

(def template-db-spec
  ; Connect to PG template database to create application database
  (korma.db/postgres {:db "template1"
                      :user "user"
                      :password "pw"
                      :host "localhost"
                      :port "5433"}))

(def db-spec
  ; Application database
  (korma.db/postgres {:db "korma_encrypted_example"
                      :user "user"
                      :password "pw"
                      :host "localhost"
                      :port "5433"}))

(defn build-db []
  (println "Setting up database...")
  (try
    (jdbc/db-do-commands
      template-db-spec
      false
      "DROP DATABASE IF EXISTS korma_encrypted_example"
      "CREATE DATABASE korma_encrypted_example")
    (jdbc/db-do-commands
      db-spec
      (jdbc/create-table-ddl "credit_cards"
                             [:pk "serial primary key"]
                             [:name "text"]
                             [:encrypted_number "text"]
                             [:data_encryption_key_fk "integer"])
      (jdbc/create-table-ddl "data_encryption_keys"
                             [:pk "serial primary key"]
                             [:data_encryption_key "text"]))
    (jdbc/db-do-commands
      db-spec
      (generate-and-save-data-encryption-key key-service db-spec))
    (catch Exception e (prn (.getNextException e))))
  (println "Database created successfully."))

(korma.db/defdb dev db-spec)
