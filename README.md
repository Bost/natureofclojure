# natureofclojure

A bunch of Clojure [Quil](https://github.com/quil/quil "Quil") examples based on [Daniel Shiffman's Nature of Code video channel on Vimeo](https://vimeo.com/channels/natureofcode/ "Nature of Code videos").

[Check out the videos on Vimeo.](https://vimeo.com/channels/natureofcode/ "Nature of Code videos")  
Seriously, check them out, they're great.

## Caveat
I'm probably going to base this on the videos, as opposed to the website/book.  This is for no better reason than that I've read a lot of the book already and want to watch the videos now.

Go [buy the book](http://natureofcode.com "Nature of Code book") if you haven't alreaddy.  Seriously, [buy it](http://natureofcode.com "Nature of Code book"), it's the decent thing to do.

## Usage

These sketches require the [Clojure Quil library](https://github.com/quil/quil "Quil"), for working with [Processing](http://processing.org "Processing").  

Install dependencies in root dir:  

````
$ cd path/to/natureofclojure      
$ lein deps
````  

You can view these example sketches by running them from the repl.  I use Emacs and the Cider nREPL.  If you have [the same setup](https://gist.github.com/4698169 "My Clojure Emacs setup") you can just start a nREPL process and eval the sketch buffer.

Starting with Chapter 3, part 4, I've started working with Quil Middleware and Functional mode. This is how you get it to run.
````clojure
;; go to buffer for the example you wish to run
C-c M-j                ;;  M-x cider-jack-in
C-c M-n                ;;  M-x cider-repl-set-ns (optional)
M-x cider-eval-buffer  ;; keybinding?

;; Make changes
C-c C-e                ;; M-x cider-eval-last-sexp
                       ;;  This hot-reloades your changes.
````

Helpful buffer commands for working with nREPL:  
````
M-C-x (eval form under point in repl <-- allows live coding!!!)
C-c C-z (switch to repl buffer)
````  
See others at the [nREPL README](https://github.com/kingtim/nrepl.el "nREPL README").

## License

Copyright © 2014  [Pas de Chocolat, LLC](http://pasdechocolat.com/ "Awesome website")  
This work is licensed under a [Creative Commons Attribution-ShareAlike 2.0 Generic License](http://creativecommons.org/licenses/by-sa/2.0/ "Creative Commons Attribution-ShareAlike 2.0 Generic License").
 
