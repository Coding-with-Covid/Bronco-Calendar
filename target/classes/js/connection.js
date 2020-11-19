/* This uses nodejs to connect to mongodb and display the event collection. */
const MongoClient = require('mongodb').MongoClient;
const assert = require('assert');

const url = 'mongodb+srv://dbUser:2yIDatv8tauD7iNI@broncocalendar.8cdgd.mongodb.net/test?retryWrites=true&w=majority';

MongoClient.connect(url, function(err, client) {
   if(!err) console.log('Successfuly connected to mongodb');
   assert.strictEqual(null, err);
   client.close;
   
   const db = client.db("myDB");
   var cursor = db.collection('event').find({});
   
   function iterateFunc(doc) {
    console.log(JSON.stringify(doc, null, 4));
   }
 
   function errorFunc(error) {
    console.log(error);
   }
 
   cursor.forEach(iterateFunc, errorFunc);
});