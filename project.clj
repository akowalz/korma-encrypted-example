(defproject korma-encrypted-example "0.1.0-SNAPSHOT"
  :description "Example application using korma-encrypted for encryption"
  :url "https://github.com/akowalz/korma-encrypted-example"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [korma "0.4.2"]
                 [korma-encrypted "0.1.0-SNAPSHOT"]
                 [org.postgresql/postgresql "9.2-1002-jdbc4"]]
  :main korma-encrypted-example.core
  :aliases {"dbdo" ["run" "-m" "korma-encrypted-example.db/build-db"]})
