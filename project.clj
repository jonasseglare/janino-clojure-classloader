(defproject janino-clojure-classloader "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}


  
  :profiles {:repl {

                    ;; Example configuration to try out in the REPL

                    ;; Where Clojure sources are stored
                    :source-paths ["example/clj"]

                    ;; Where the Java sources to reload are stored
                    :jvm-opts ["-Djanino_src_path=example/reloaded_java"]}}

  ;; Tweaked DynamicClassLoader.java is found in here:
  :java-source-paths ["src/java"]
  
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.codehaus.janino/janino "3.1.0"]]
  :repl-options {:init-ns janino-clojure-classloader.core})
