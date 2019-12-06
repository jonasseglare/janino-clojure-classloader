(ns janino-clojure-classloader.core
  (:require [clojure.java.io :as io])
  (:import [clojure.lang DynamicClassLoader]
           [org.codehaus.janino JavaSourceClassLoader]
           Mjao))
#_(import 'clojure.lang.AFn)

;(set! clojure.lang.DynamicClassLoader/custom_loader nil)
;(import 'Mjao)
#_(println "majo value: " (Mjao/magicOp 3))

(comment

  
  (defn current-loader []
    (.deref clojure.lang.Compiler/LOADER))


  (def janino-loader (JavaSourceClassLoader.
                      nil
                      (into-array [(io/file "janino")])
                      nil))

  
  (defn set-source-loader []
    (set! DynamicClassLoader/custom_loader janino-loader))


  (set-source-loader)

  (import 'Mjao)


  (defn load-mjao []
    (.loadClass janino-loader "Mjaoll"))





  )
