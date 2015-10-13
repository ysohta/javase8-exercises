/*
 * Usage: jrunscript -f long_word_finder.js
 */
var Files = java.nio.file.Files
var Paths = java.nio.file.Paths
var Arrays = java.util.Arrays
var patternSplit = /[\p:\"#$%&\'()*+,-.\/:;<=>?@\[¥\]^_`{|}~\s]+/

var lines = Files.lines(Paths.get('../../../res/pg2600.txt'))
var words = lines.flatMap( function(s) Arrays.stream(Java.to(s.split(patternSplit), 'java.lang.Object[]')))
var filtered = words.filter( function(s) s.length > 12 )
var distinct = filtered.distinct()
var sorted = distinct.sorted()
sorted.forEach( function(s) print(s))
