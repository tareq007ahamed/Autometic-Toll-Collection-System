const db = require('../db')

function response(error, res, callback) {
    if (error) {
        console.log("Query Error: ", error);
        callback(error, null);
    } else {
        callback(null, res);
    }
}

exports.getDriverCars = (driver_id, callback) => {
    db.query("SELECT Car.id, Car.model, Car.rfid, Car.image, Car.no_plate, CarType.type FROM Car INNER JOIN DriverCar ON Car.id = DriverCar.Car_id INNER JOIN CarType ON Car.CarType_id = CarType.id WHERE DriverCar.Driver_id = ?", [driver_id], (error, res) => {
        response(error, res, callback)
    })
}