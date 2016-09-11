var Router = require("vertx-web-js/router");

var router = Router.router(vertx);
var message = "Hello World! " + Math.random();
router.route().handler(function (routingContext) {
    routingContext.response().putHeader("content-type", "text/html").end(message);
});

vertx.createHttpServer().requestHandler(router.accept).listen(8080);