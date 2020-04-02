var Constants = require('./constants');

var exec = require('cordova/exec');
var run = require('./run');

exports.getVisitorId = function(success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "visitorId", []);
    }, success, error);
}

exports.isOptOut = function(success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "isOptOut", []);
    }, success, error);
}

exports.optIn = function(success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "optIn", []);
    }, success, error);
}

exports.optOut = function(success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "optOut", []);
    }, success, error);
}

exports.renewVisitorId = function(success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "renewVisitorId", []);
    }, success, error);
}

exports.registerFCMToken = function(fcmToken, success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "registerFCMToken", [fcmToken]);
    }, success, error);
}