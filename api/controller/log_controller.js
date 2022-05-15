const Log = require('../models/log')

exports.getCarTollLogs = (req, res) => {
    let params = req.params;
    Log.getCarTollLogs(params.driver_id, (error, result) => {
        if (error) {
            res.status(500)
        }
        if(result.length <= 0)
            res.status(404)
        res.send(JSON.stringify(result))
    })
}

exports.getDriverTollLogs = (req, res) => {
    let params = req.params;
    Log.getDriverTollLogs(params.driver_id, (error, result) => {
        if (error) {
            res.status(500)
        }
        if(result.length <= 0)
            res.status(404)
        res.send(JSON.stringify(result))
    })
}