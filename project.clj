(defproject eq-server "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :profiles {:dev {:plugins [[lein-midje "3.0.1"]]
                   :dependencies [[midje "1.6-alpha2"]
                                  [ring-mock "0.1.5"]]}}
  :plugins [[lein-beanstalk "0.2.7"]]
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/java.jdbc "0.3.0-alpha4"]
                 [org.clojure/tools.logging "0.2.6"]
                 [log4j/log4j "1.2.17"]
                 [postgresql "9.1-901-1.jdbc4"]
                 [c3p0/c3p0 "0.9.1.2"]
                 [drift "1.4.5"]
                 [ring "1.2.0-beta3"]
                 [compojure "1.1.5"]
                 [cheshire "5.2.0"]
                 [crypto-password "0.1.0"]
                 [clj-time "0.5.1" :exclusions [[clojure :classifier "*"]]]
                 [com.netflix.curator/curator-framework "1.3.3"]
                 [com.netflix.curator/curator-recipes "1.3.3"]]
  :ring {:handler eq-server.routes/app :init eq-server.core/init! :auto-reload? true}
  :aws {:beanstalk {:environments [{:name "eq-dev" :cname-prefix "eq-dev"}]}})
