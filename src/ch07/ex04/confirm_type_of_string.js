/*
 * Usage: jrunscript -f confirm_type_of_string.js
 */
var sliced = 'HelloWorld'.slice(5)

// World
print(sliced)

// casted is a string of JavaScript
var casted = java.lang.String.class.cast(sliced)

// rld
print(casted.slice(2))
