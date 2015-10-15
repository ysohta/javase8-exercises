/*
 * Usage: jjs -scripting
 */
var ProcessBuilder = java.lang.ProcessBuilder
var InputStreamReader = java.io.InputStreamReader
var BufferedReader = java.io.BufferedReader

var b = new ProcessBuilder('/bin/sh', '-c', 'find . | grep -v class | sort')
var buf = new BufferedReader(new InputStreamReader(b.start().getInputStream()))

var line = ""
while((line = buf.readLine()) != null) print(line)
