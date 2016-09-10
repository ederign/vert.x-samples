var EventBus = require('vertx3-eventbus-client');

var eb = new EventBus('http://localhost:8080/eventbus/');

eb.onopen = function () {
    setInterval(function () {
        eb.send("events", {
            "id": "99999",
            "name": "nodejs"
        });
    }, 1000);
};