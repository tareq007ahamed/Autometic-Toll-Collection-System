const User = require('../models/user')

exports.login = (req, res) => {
    let body = req.body;
    User.checkUser(body.email, body.password, (error, result) => {
        if(error){
            res.status(404)
        }
        if(result.length == 1){
            result[0]['status'] = 'authenticated'
            res.end(JSON.stringify(result[0]))
        }
        else
            res.end(JSON.stringify({status: 'unauthenticated'}))
    })
}