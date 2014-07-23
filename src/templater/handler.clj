(ns templater.handler
  (:require [com.stuartsierra.component :as component]
            [stasis.core :as stasis]
            [ring.util.response :as response]
            [ring.middleware
              [reload :refer [wrap-reload]]
              [file :refer [wrap-file]]
              [content-type :refer [wrap-content-type]]
              [not-modified :refer [wrap-not-modified]]]
            [templater.pages :as pages]))

(defn static-site-handler [dir]
  (do
    (stasis/empty-directory! dir)
    (stasis/export-pages pages/get-pages dir)
    (-> (wrap-file dir)
        wrap-not-modified)))

(defn site-handler [env static-dir]
  (if (= :production env)
    (static-site-handler static-dir)
    (stasis/serve-pages pages/get-pages)))

(defrecord Handler [environment static-dir ring-handler]
  component/Lifecycle

  (start [this]
    (if ring-handler
      this
      (assoc this :ring-handler (site-handler environment static-dir))))

  (stop [this]
    (dissoc this :ring-handler)))

(defn create-handler
  [environment]
  (map->Handler environment))
