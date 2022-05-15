const Car = require('../models/car')

exports.getDriverCars = (req, res) => {
    let params = req.params;
    Car.getDriverCars(params.driver_id, (error, result) => {
        if (error) {
            res.status(500)
        }
        if(result.length <= 0)
            res.status(404)
        res.send(JSON.stringify(result))
    })
}