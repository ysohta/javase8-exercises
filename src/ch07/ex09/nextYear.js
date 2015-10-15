/*
 * Usage: jjs -scripting nextYear.js -- 30
 * Usage: jjs -scripting nextYear.js
 */
var age = $ARG[0]
if(age == undefined) age = $ENV.AGE
if(age == undefined)  age = readLine('Age: ')

print("Next year, you will be " + (new Number(age) + 1))
