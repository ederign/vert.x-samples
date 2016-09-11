var eb = new EventBus('http://localhost:9001/eventbus');
var total_server1 = 0;
var total_server2 = 0;
var total = 0;


eb.onopen = function () {
    eb.registerHandler('system.process.out.server1', function (err, msg) {
        total_server1 = total_server1 + 1;
        drawChart(total_server1, total_server2, total);
    });
    eb.registerHandler('system.process.in', function (err, msg) {
        total = total + 1;
        drawChart(total_server1, total_server2, total);
    });
    eb.registerHandler('system.process.out.server2', function (err, msg) {
        total_server2 = total_server2 + 1;
        drawChart(total_server1, total_server2, total);
    });
};
