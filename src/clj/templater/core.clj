(ns templater.core
  (:require [clojure.string :as string]
            [clojure.tools.cli :refer [parse-opts]]
            [com.stuartsierra.component :as component]
            [templater.handler :refer [create-handler]]
            [templater.server :refer [create-server]])
  (:gen-class))

(defn system [options]
  (component/system-map
    :config options
    :ring-handler (create-handler (:env options) "resources/templates")
    :http-server (component/using
                   (create-server options)
                   {:handler :ring-handler})))

(def cli-options
  [["-p" "--port PORT" "Port Number"
    :default 9393
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]]
   ["-e" "--env ENV" "Environment"
    :default :development
    :parse-fn #(-> % string/lower-case keyword)
    :validate [(fn [e] (some #(= % e) [:production :development]))
               "Must be production or development"]]
   ["-h" "--help"]])

(defn usage [options-summary]
  (->> ["Options:" options-summary] (string/join \newline)))

(defn exit [status msg]
  (println msg)
  (System/exit status))

(defn -main [& args]
  (let [{:keys [options summary]} (parse-opts args cli-options)]
    (if (:help options)
      (exit 0 (usage summary))
      (let [sys (component/start (system options))]
        (println (str "Server running at "
                      (-> sys :http-server :ip)
                      ":"
                      (-> sys :http-server :port)))))))
