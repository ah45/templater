(ns templater.pages
  #+clj (:require [hiccup.core :refer [html]]
                  [hiccup.page :refer [html5]])
  #+cljs (:require [sablono.core :refer [html]]))

#+clj
(defn layout-page [page]
  (html5
    [:head
      [:meta {:charset "utf-8"}]
      [:meta {:name "viewport"
              :content "width=device-width,initial-scale=1.0"}]
      [:title "Templating Test"]]
    [:body page]))

(def index
  (html
    [:h1 "Hello Templating World"]
    [:p "This is a test, has been a test, will always be a test"]))

#+clj
(defn get-pages []
  {"/" (layout-page index)})
