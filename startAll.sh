# start DataSimulator, redis server and DataPusher

# start DataSimulator on port 9000 defaultly
java -jar ./DataSimulator/bin/dataSimulator.jar &

# start redis server
redis-server ./redis/redis.conf &

# start DataPusher after starting redis successfully
node ./DataPusher/pusher.js;
 

