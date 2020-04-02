var Constants = require('./constants');
var Variable = require('./variable');

var exec = require('cordova/exec');
var run = require('./run');

function toKeys(variables) {
    var keys = [];
    for (var i = 0; i < variables.length; i++) {
        keys.push(variables[i].name);
    }
    return keys;
}

exports.fetch = function(success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "fetch", []);
    }, success, error);
}

exports.getVariable = function(name, success, error) {
    return run(function(success, error) {
        exec(function(name) {
            success(new Variable(name));
        }, error, Constants.PLUGIN_NAME, "variable", [name]);
    }, success, error);
}

exports.trackOpen = function(variables, values, success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "trackOpen", [toKeys(variables), values]);
    }, success, error);
}

exports.trackClick = function(variables, values, success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "trackClick", [toKeys(variables), values]);
    }, success, error);
}
