/*
 * Usage: jjs -fx javafx_piechart.js
 */
var Files = java.nio.file.Files;
var Paths = java.nio.file.Paths;
var PieChart = javafx.scene.chart.PieChart
var FXCollections = javafx.collections.FXCollections

function readIniFile(file) {
    var map = new java.util.HashMap()
    for each (var line in Files.readAllLines(Paths.get(file))) {
        var arr = line.split(/=/)
        map[arr[0]] = arr[1]
    }

    return map
}

var data = readIniFile('data.ini');
var pieChartData = FXCollections.observableArrayList()
for each (var e in data.entrySet()) {
    pieChartData.add(new PieChart.Data(e.key, e.value))
}
var chart = new PieChart(pieChartData)
chart.setTitle('Imported Fruits')
$STAGE.scene = new javafx.scene.Scene(chart)
