const authController = require('./controller/auth_controller')
const userController = require('./controller/user_controller')
const driverController = require('./controller/driver_controller')
const carController = require('./controller/car_controller')
const logController = require('./controller/log_controller')

const router = (app) => {
    app.post('/login', authController.login)
    app.get('/user/:id', userController.getUser)
    app.get('/driver/:user_id', driverController.getDriver)
    app.get('/driver/:driver_id/cars', carController.getDriverCars)
    app.get('/driver/car/:driver_id/logs', logController.getCarTollLogs)
    app.get('/driver/:driver_id/logs', logController.getDriverTollLogs)

}

module.exports = router