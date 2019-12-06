(ns janino-clojure-classloader.core
  (:require [clojure.java.io :as io])
  (:import [clojure.lang DynamicClassLoader]
           [org.codehaus.janino JavaSourceClassLoader]
           Mjao
           ))

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
    (.loadClass janino-loader "Mjao"))





  )
