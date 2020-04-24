# janino-clojure-classloader

Load and compile Java source code directly whenever it is imported, so that we don't have to restart the REPL.

## Example



## Usage

Add this project as a dependency (haven't tested that yet). Then, in Leiningen, set the `janino_src_path` system property to where the Java sources to be reloaded are stored:
```
  :jvm-opts ["-Djanino_src_path=reloaded_java_sources"]
```

Possibly add `:reload` at the end of the relevant `require`-forms so that sources are reimported (to prevent incompatibilities between different loaded versions of the same class).

## How it is implemented

The `DynamicClassLoader.java` file from the Clojure source repository is duplicated and tweaked in this repository at `src/java/clojure/lang/DynamicClassLoader.java`. It is tweaked in such a way that it also uses an instance of the JaninoSourceClassLoader to attempt to load and compile Java sources from disk. Every time we import a class, we also check if any file in the source directory has changed, and in that case we create a new instance of this class loader.

## Issues and things to improve

In short, there are two main issues:

    * There is a possibility that instances of a class with the same name, but different class definition, exist simultaneously. The risk of this happening can be reduced by reloading all the namespaces that import a class, e.g. with the help of `:reload` added at the end of require, e.g. `(require [my.other.namespace :as x] :reload)`.

    * The Java source code is compiled using Janino, so it may not have all the advanced Java features.

### To do

Extend the `load-lib` function in `clojure/core.clj` to automatically do a reload-all when necessary:

This is what part of it currently looks like:
```
(defn- load-lib
  "Loads a lib with options"
  [prefix lib & options]
  (throw-if (and prefix (pos? (.indexOf (name lib) (int \.))))
            "Found lib name '%s' containing period with prefix '%s'.  lib names inside prefix lists must not contain periods"
            (name lib) prefix)
  (let [lib (if prefix (symbol (str prefix \. lib)) lib)
        opts (apply hash-map options)
        {:keys [as reload reload-all require use verbose]} opts
```

## License

Copyright © 2020 Jonas Östlund

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
