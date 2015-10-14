/*
 * Usage: jjs -scripting
 */
var pipe = function(commands) { for (var i in commands) { if(i == 0) { $EXEC(commands[i]) } else { $EXEC(commands[i], $OUT) } } return $OUT }

var commands = ['find .', 'grep -v class', 'sort']
pipe(commands)
