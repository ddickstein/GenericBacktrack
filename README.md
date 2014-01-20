## Backtracking Algorithm ##

There are a lot of different types of algorithms that require a depth-first
search of a tree.  I decided to write a tool that does backtracking through a
generic tree and lets you decide what to do when you come to each individual
node.

Currently it supports three different functions that are run on a per-node
basis.  You can define a `found()` function to determine whether or not this
is the node for which you were searching, a `prune()` function to determine
whether or not you should continue searching down this branch, and an
`isBetter()` function to determine whether or not the current result is better
than a result you found at a previous point during the search.