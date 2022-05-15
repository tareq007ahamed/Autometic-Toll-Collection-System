const db = require('../db')

function response(error, res, callback) {
    if (error) {
        console.log("Query Error: ", error);
        callback(error, null);
    } else {
        callback(null, res);
    }
}

exports.checkUser = (email, pass, callback) => {
    db.query("SELECT id FROM USER WHERE email = ? AND PASSWORD = ?", [email, pass], (error, res) => {
        response(error, res, callback)
    })
}

exports.getUser = (id, callback) => {
    db.query("SELECT * FROM USER WHERE id = ?", [id], (error, res) => {
        response(error, res, callback)
    })
}