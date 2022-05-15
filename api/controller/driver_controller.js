const Driver = require('../models/driver')

exports.getDriver = (req, res) => {
    let params = req.params;
    Driver.getDriver(params.user_id, (error, result) => {
        if (error) {
            res.status(500)
        }
        if(result.length <= 0)
            res.status(404)
        res.send(JSON.stringify(result[0]))
    })
}