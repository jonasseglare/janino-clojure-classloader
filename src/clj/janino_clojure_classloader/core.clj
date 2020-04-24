(ns janino-clojure-classloader.core
  (:require [janino-clojure-classloader.other :as other] :reload)
  (:import Mjao))

(println "The result is " (Mjao/magicOp 3))

(println "Same class" (= (class (other/get-instance))
                         (class (Mjao.))))

