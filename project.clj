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

                 ; static
                 [stasis "2.1.1"]
                 [hiccup "1.0.5"]
                 [optimus "0.15.0"]
                 [inliner "0.1.0"]

                 ; dynamic
                 [om "0.6.5"]
                 [kioo "0.4.0"]]

  :source-paths ["src/clj"]

  :main templater.core
  :aot [templater.core]

  :jar-exclusions [#"\.(cljx|swp|swo|DS_Store)"]

  :plugins [[com.keminglabs/cljx "0.4.0"]
            [lein-cljsbuild "1.0.4-SNAPSHOT"]]

  :hooks [cljx.hooks]

  :cljx {:builds [{:source-paths ["src/cljx"]
                   :output-path "target/classes"
                   :rules :clj}
                  {:source-paths ["src/cljx"]
                   :output-path "target/generated/cljs"
                   :rules :cljs}]}

  :cljsbuild {:builds [{:source-paths ["src/cljs" "target/generated/cljs"]
                        :compiler {:output-to "target/templater.js"
                                   :optimizations :advanced
                                   :pretty-print false}}]})
