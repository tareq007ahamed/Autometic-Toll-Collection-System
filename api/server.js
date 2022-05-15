const express = require('express')
const bodyParser = require('body-parser')
const cors = require('cors')
const routes = require('./routes')
const db = require('./db')

const app = express()
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({
    extended: true
}))
app.use(cors())

db.connect((error) => {
    if (error) {
        throw error
    }
    console.log('Database connected!')
})

const server = app.listen(3000, '0.0.0.0', () => {
    console.log('Server running at %s', server.address().address)
})

routes(app)
