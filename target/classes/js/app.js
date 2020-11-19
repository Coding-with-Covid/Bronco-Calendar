const express = require('express')
const app = express()
const port = 3000

/* These are the constants for mongodb */
const MongoClient = require('mongodb').MongoClient;
const assert = require('assert');
const url = 'mongodb+srv://dbUser:2yIDatv8tauD7iNI@broncocalendar.8cdgd.mongodb.net/test?retryWrites=true&w=majority';


app.set('view engine', 'ejs')
app.use(express.static(__dirname + '/views'));

app.get('/', (req, res) => res.send('hello world'))

/* This uses nodejs to connect to mongodb and display the event collection. */
MongoClient.connect(url, function(err, client) {
    assert.strictEqual(null, err);
    if(!err) console.log('Successfuly connected to mongodb');
    
    app.listen(port, () => console.log('Example app listening on port ${port}!'))

    app.get('/events', (req, res) => {
        //let device_list = [{'name':'dht22'}, {'name':'Bosh'}]
        const db = client.db("myDB");
        const collection = db.collection('event');
    
        // Find some documents
        collection.find({}).toArray(function(err, event_list) {
            assert.equal(err, null);
            res.render('events', {'events': event_list})
        });
    })
});