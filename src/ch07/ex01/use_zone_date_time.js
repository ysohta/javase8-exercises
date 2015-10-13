/*
 * Usage: jrunscript -f use_zone_date_time.js
 */
var ZonedDateTime = java.time.ZonedDateTime
var now = ZonedDateTime.now()
print(now)
print(now.getHour() + ':' + now.getMinute() + ':' + now.getSecond())
