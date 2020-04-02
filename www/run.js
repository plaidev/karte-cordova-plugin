module.exports = function(operation, success, error) {
    var promise;
    if (typeof success !== 'function' && typeof error !== 'function') {
        promise = new Promise(function(resolve, reject) {
            success = function(res) {
                resolve(res);
            }
            error = function(err) {
                reject(err);
            }
        });
    }

    operation(success, error);
    return promise;
}
