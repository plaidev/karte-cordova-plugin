var Constants = require('./constants');

var exec = require('cordova/exec');
var run = require('./run');

exports.appendingQueryParameter = function(url, success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "appendingQueryParameter", [url]);
    }, success, error);
}

exports.getUserSyncScript = function(url, success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "getUserSyncScript", []);
    }, success, error);
}
