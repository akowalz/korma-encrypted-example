(ns korma-encrypted-example.key-service
  (:require [korma-encrypted.core :refer [KeyService]]))

(deftype FakeKeyService []
  KeyService
  (encrypt [self data] data)
  (decrypt [self data] data))

(def key-service (FakeKeyService.))
