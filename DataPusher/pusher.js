/*
 * DataPusher
 * Author Jackie
 */
//default arguments
var port = 9090;           //http server port
var redisIp = "127.0.0.1"; //redis server ip address
var redisPort = 6379;      //redis server port
var srcIp = "127.0.0.1";   //DataSimulator server ip address
var srcPort = 9000;        //DataSimulator server port

//handle input arguments
//-p  --> http server port
//-r  --> redis ip
//-rp --> redis port
//-s  --> data simulator ip
//-sp --> data simulator port
process.argv.forEach(function(val,index,array){
   if(val == "-p" && index+1<array.length){
     port = array[index+1];     
     console.log(port);
   } 
   if(val == "-r" && index+1<array.length){
     redisIp = array[index+1];
     console.log(redisIp);     
   }
   if(val == "-rp" && index+1<array.length){
     redisPort = array[index+1];     
   }
   if(val == "-s" && index+1<array.length){
     srcIp = array[index+1];     
   }
   if(val == "-sp" && index+1<array.length){
     srcPort = array[index+1];     
   }

});

//require modules
var express = require('express');
var redis = require('redis');
var socketio = require('socket.io');
var http = require('http');
var redis = require('redis');

//create http server and websocket server
var app = express();
var httpServer = http.createServer(app);
var httpIo = socketio.listen(httpServer);


//-----------------redis cache-------------------------
var cache = redis.createClient(redisPort,redisIp);    
var stocksKey = "allstocks";  //the key of the keyset storing all the stocks
    
cache.on("error",function(err){
    console.log("Error: "+err);
});



//----------------http server------------------------

httpServer.listen(port);
console.log('http server listening on '+port);

app.get('/',function(req,res){
    res.sendfile(__dirname+'/index.html');
});
//
app.use(function(err,req,res,next){
    console.error(err.stack);
    res.send(500,'something broke!');
});

//-----------------socket.io(WebSocket)----------------
//when connect successfully
httpIo.sockets.on('connection',function(socket){
      console.log("a web socket acceptd.");
      socket.on('ready',function(){
        //send all the stocks
        sendAllStocks(stocksKey,socket);
        
      }); 
 
});


//get all stocks from redis, transfer to json string, send it to client.
var sendAllStocks = function(key,socket){
  cache.hkeys(key,function(err,keys){
    for(var i=0;i<keys.length;i++){
       //get a stock by a key
       cache.get(keys[i],function(err,value){
          socket.emit('one',value);   //send data of one stock to client
          //console.log(value);
       });
    }
  });
};


//-------------------data fetcher implemented by tcp socket---------------------
var net = require('net');

var client = net.createConnection(
    srcPort,
    srcIp,
  function(){
    console.log('fetcher connected');
});
//listen 'data' event
client.on('data',function(data){
    //send data to all the web brower clients via Web Socket(socket.io)
    httpIo.sockets.emit('data',data.toString());

    //console.log(data.toString());
    var obj = JSON.parse(data.toString());
    var stocks = obj.content;
    //save update data to redis cache
    for(var i=0;i<stocks.length;i++){
     cache.hset(stocksKey,stocks[i].code,"");     //save the keys of the stocks to the 'keyset' in redis
     cache.set(stocks[i].code,JSON.stringify(stocks[i]));  //save the data of stocks to redis
     //console.log(stocks[i].code+" "+JSON.stringify(stocks[i]));
    }

});
client.on('error',function(e){
   console.log(e);
});
client.on('end',function(){
  console.log('client disconnected');
});
