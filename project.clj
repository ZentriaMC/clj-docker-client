;   This file is part of clj-docker-client.
;
;   clj-docker-client is free software: you can redistribute it and/or modify
;   it under the terms of the GNU Lesser General Public License as published by
;   the Free Software Foundation, either version 3 of the License, or
;   (at your option) any later version.
;
;   clj-docker-client is distributed in the hope that it will be useful,
;   but WITHOUT ANY WARRANTY; without even the implied warranty of
;   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
;   GNU Lesser General Public License for more details.
;
;   You should have received a copy of the GNU Lesser General Public License
;   along with clj-docker-client. If not, see <http://www.gnu.org/licenses/>.

(defproject lispyclouds/clj-docker-client "0.5.2"
  :author "Rahul De <rahul@mailbox.org>"
  :url "https://github.com/lispyclouds/clj-docker-client"
  :description "An idiomatic data-driven clojure client for Docker."
  :license {:name "LGPL 3.0"
            :url  "https://www.gnu.org/licenses/lgpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [clj-commons/clj-yaml "0.7.0"]
                 [metosin/jsonista "0.2.5"]
                 [com.squareup.okhttp3/okhttp "4.4.0"]
                 [com.github.jnr/jnr-unixsocket "0.27"]]
  :plugins [[lein-ancient "0.6.15"]]
  :global-vars {*warn-on-reflection* true}
  :aot [clj-docker-client.socket.TunnelingUnixSocket
        clj-docker-client.socket.UnixDomainSocketFactory]
  :resource-paths ["resources"]
  :profiles {:kaocha {:dependencies [[lambdaisland/kaocha "0.0-590"]]}
             :rebl   {:repl-options   {:nrepl-middleware [nrebl.middleware/wrap-nrebl]}
                      :injections     [(require '[cognitect.rebl :as rebl])]
                      :dependencies   [[rickmoynihan/nrebl.middleware "0.3.1"]
                                       [org.clojure/core.async "1.0.567"]
                                       [lein-cljfmt "0.6.6"]
                                       [org.openjfx/javafx-fxml "13.0.2"]
                                       [org.openjfx/javafx-controls "13.0.2"]
                                       [org.openjfx/javafx-media "13.0.2"]
                                       [org.openjfx/javafx-swing "13.0.2"]
                                       [org.openjfx/javafx-base "13.0.2"]
                                       [org.openjfx/javafx-web "13.0.2"]]
                      :resource-paths [~(System/getenv "REBL_PATH")]}}
  :aliases {"kaocha" ["with-profile" "+kaocha" "run" "-m" "kaocha.runner"]})
