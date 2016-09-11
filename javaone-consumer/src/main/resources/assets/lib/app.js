var eb = new EventBus('http://localhost:9001/eventbus');
var total8080 = 0;
var total8081 = 0;
var total = 0;


eb.onopen = function () {
    eb.registerHandler('system.process.out.S8081', function (err, msg) {
        total8081 = total8081 + 1;
        drawChart(total8080, total8081, total);
    });
    eb.registerHandler('system.process.in', function (err, msg) {
        total = total + 1;
        drawChart(total8080, total8081, total);
    });
    eb.registerHandler('system.process.out.S8080', function (err, msg) {
        total8080 = total8080 + 1;
        drawChart(total8080, total8081, total);
    });
};
