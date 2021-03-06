(defproject eq-server "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :profiles {:dev {:plugins [[lein-midje "3.0.1"]
                             [drift "1.5.2"]]
                   :dependencies [[midje "1.6-alpha2"]
                                  [ring-mock "0.1.5"]]}}
  :plugins [[lein-beanstalk "0.2.7"]]
  :min-lein-version "2.3.3"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/java.jdbc "0.3.0-beta1"]
                 [org.clojure/tools.logging "0.2.6"]
                 [log4j/log4j "1.2.17"]
                 [org.postgresql/postgresql "9.3-1100-jdbc41"]
                 [c3p0/c3p0 "0.9.1.2"]
                 [ring "1.2.1"]
                 [compojure "1.1.6"]
                 [cheshire "5.2.0"]
                 [crypto-password "0.1.1"]
                 [clj-time "0.6.0" :exclusions [[clojure :classifier "*"]]]
                 [liberator "0.10.0"]
                 [com.cemerick/friend "0.2.0"]]
  :ring {:handler eq-server.routes/app
         :init eq-server.core/init!
         :auto-reload? true
         :reload-paths "src"
         :nrepl {:start? true :port 55555}})
