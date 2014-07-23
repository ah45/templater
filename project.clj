(defproject templater "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.cli "0.3.1"]
                 [org.clojure/clojurescript "0.0-2268"]

                 [com.stuartsierra/component "0.2.1"]

                 [ring/ring-core "1.3.0"]
                 [ring/ring-devel "1.3.0"]
                 [http-kit "2.1.18"]

                 [stasis "2.1.1"]
                 [hiccup "1.0.5"]
                 [optimus "0.15.0"]
                 [inliner "0.1.0"]]

  :main templater.core
  :aot [templater.core])
