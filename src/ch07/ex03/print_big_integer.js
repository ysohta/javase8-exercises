/*
 * Usage: jrunscript -f print_big_integer.js
 */
var b = new java.math.BigInteger('1234567890987654321')

// 1234567890987654400
print('b=' + b)

var mod = b.mod(java.math.BigInteger.TEN)
print('mod10=' + mod)

var sb = '0x'
for each (var elem in b.toByteArray()) sb += new Number(elem & (255)).toString(16)
// 0x112210f4b16c1cb1 is equivalent to 1234567890987654321
print(sb)

