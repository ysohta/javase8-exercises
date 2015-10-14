/*
 * Usage: jrunscript -f logged_arraylist_factory.js
 */
var createLoggedArrayList = function() {
    var arr = new (Java.extend(java.util.ArrayList)) {
        add: function(x) {
            print('Adding ' + x)
            return Java.super(arr).add(x)
        }
    }

    return arr
}

var arr1 = createLoggedArrayList()
arr1.add(1)
print('arr1:' + arr1)
arr1.add(2)
print('arr1:' + arr1)

var arr2 = createLoggedArrayList()
arr2.add(3)
print('arr2:' + arr2)

