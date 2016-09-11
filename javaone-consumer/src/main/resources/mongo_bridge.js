var eb = vertx.eventBus();

var Vertx = require("vertx-js/vertx");
var MongoClient = require("vertx-mongo-js/mongo_client");

var config = Vertx.currentContext().config();

var uri = config.mongo_uri;
if (uri === null) {
    uri = "mongodb://localhost:27017";
}
var db = config.mongo_db;
if (db === null) {
    db = "test";
}

var mongoconfig = {
    "connection_string": uri,
    "db_name": db
};

var mongoClient = MongoClient.createShared(vertx, mongoconfig);


eb.consumer("system.process.in", function (message) {
    //var msg = "Msg: " + message.body().id + ", " + message.body().name + " from " + message.body().system;
    save(message);
});

function save(message) {
    mongoClient.insert("messages", message, function (res, res_err) {

        if (res_err == null) {

            var id = res;
            console.log("Inserted msg with id " + id);

        } else {
            res_err.printStackTrace();
        }

    });
}