var Constants = require('./constants');

var exec = require('cordova/exec');
var run = require('./run');

function Variable(name) {
    this.name = name;
}

Variable.prototype.getString = function(defaultValue, success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "string", [this.name, defaultValue]);
    }.bind(this), success, error);
}

Variable.prototype.getInteger = function(defaultValue, success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "integer", [this.name, defaultValue]);
    }.bind(this), success, error);
}

Variable.prototype.getDouble = function(defaultValue, success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "double", [this.name, defaultValue]);
    }.bind(this), success, error);
}

Variable.prototype.getBool = function(defaultValue, success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "bool", [this.name, defaultValue]);
    }.bind(this), success, error);
}

Variable.prototype.getArray = function(defaultValue, success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "array", [this.name, defaultValue]);
    }.bind(this), success, error);
}

Variable.prototype.getObject = function(defaultValue, success, error) {
    return run(function(success, error) {
        exec(success, error, Constants.PLUGIN_NAME, "object", [this.name, defaultValue]);
    }.bind(this), success, error);
}

module.exports = Variable;