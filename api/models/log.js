const db = require('../db')

function response(error, res, callback) {
    if (error) {
        console.log("Query Error: ", error);
        callback(error, null);
    } else {
        callback(null, res);
    }
}

exports.getCarTollLogs = (driver_id, callback) => {
    db.query("SELECT cost, location, tolled_at FROM TollLog WHERE Car_id = ? ORDER BY TollLog.tolled_at DESC", [driver_id], (error, res) => {
        response(error, res, callback)
    })
}

exports.getDriverTollLogs = (driver_id, callback) => {
    db.query("SELECT Car.model, TollLog.cost, TollLog.location, TollLog.tolled_at FROM Car INNER JOIN TollLog ON Car.id = TollLog.Car_id WHERE TollLog.Driver_id = ? ORDER BY TollLog.tolled_at DESC", [driver_id], (error, res) => {
        response(error, res, callback)
    })
}