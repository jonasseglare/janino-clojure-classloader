(ns janino-clojure-classloader.core
  (:require [janino-clojure-classloader.other :as other]

            ;; This ':reload' is needed, so that the 'other' namespace
            ;; also imports an up-to-date class of ADNumber and Mjao. Otherwise,
            ;; the 'run-test' function will produce an error.
            :reload

            )
  (:import Mjao ADNumber))

;; When we reload the current file, this should display the *current* value
;; in the Mjao.java source code, always.
(println "The result is " (Mjao/magicOp 3))

;; This checks that the same class is also reimported in the required namespace
(println "Same class?" (if (= (class (other/get-instance))
                              (class (Mjao.)))
                         "YES (things look good :-) )"
                         "NO (something is wrong)"))

;; This function can be used to test that a function in another namespace
;; will also reimport the same class definitions, so that there are no
;; incompatibilities. Currently, that namespace is required with `:reload` so that
;; it gets reloaded whenever we reload this namespace.
(defn run-test []
  (other/add-ad (Mjao/getX)
                (Mjao/getY)))
