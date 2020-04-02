var Constants = require('./constants');

var exec = require('cordova/exec');
var run = require('./run');

exports.isPresenting = function(success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "isPresenting", []);
    }, success, error);
}

exports.dismiss = function(success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "dismiss", []);
    }, success, error);
}

exports.suppress = function(success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "suppress", []);
    }, success, error);
}

exports.unsuppress = function(success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "unsuppress", []);
    }, success, error);
}
