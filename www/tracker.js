var Constants = require('./constants');

var exec = require('cordova/exec');
var run = require('./run');

exports.track = function(name, values, success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "track", [name, values]);
    }, success, error);
}

exports.identify = function(values, success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "identify", [values]);
    }, success, error);
}

exports.view = function(viewName, title, values, success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "view", [viewName, title, values]);
    }, success, error);
}
