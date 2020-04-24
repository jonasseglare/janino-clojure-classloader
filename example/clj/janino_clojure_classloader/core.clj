(ns janino-clojure-classloader.core
  (:require [janino-clojure-classloader.other :as other]

            ;; This ':reload' is needed, so that the 'other' namespace
            ;; also imports an up-to-date class of ADNumber and Mjao. Otherwise,
            ;; the 'run-test' function will produce an error.
            :reload

            )
  (:import Mjao ADNumber))

(println "The result is " (Mjao/magicOp 3))

(println "Same class" (= (class (other/get-instance))
                         (class (Mjao.))))

(defn run-test []
  (other/add-ad (Mjao/getX)
                (Mjao/getY)))
