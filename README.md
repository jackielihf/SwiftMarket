
Copyright (C) Jackie Lee <jackie.lihf#gmail.com>
## README of Swift Market
The system generates information of stock market randomly, and push the information data to Web Brower User.

# =================SETUP/installation=====================

# -----------prepare-----------
## 1. Operation System
Swift Market is developed and tested on Redhat Enterprise Linux 6.1 x64. You could set up the same OS or other Linux distribution.

## 2. Tools and version

1) SUN JDK1.7

2) node.js 0.10.0 linux x64  -- web backend runtime and language

3) npm 1.2.14                -- node.js package management 

4) express 3.1.0             -- web framework for node

5) redis client for node.js: redis 0.8.2  -- redis client for node

6) socket.io 0.9.14                       -- WebSocket for node

7) redis server: redis 2.6.12             -- redis server to cache data


# --------- install tools -------
## 1. install SUN JDK 1.7
go to SUN website and download JDK1.7, then install it,and set up the environment(with root):

    #vi /etc/profile

    export JAVA_HOME=/usr/java/jdk1.7.0_15
    export PATH=$PATH:$JAVA_HOME/bin
    export CLASSPATH=.:$JAVA_HOME/lib


## 2. install node.js
1)Go to http://nodejs.org/download/, and download the linux binaries: node-v0.10.0-linux-x64.tar.gz. 
You may download newer version of it.

2)unpack the package to /usr/local/node
    #tar xzvf node-v0.10.0-linux-x64.tar.gz

3)set up evironment
    # vi /etc/profile
add the following content into the file's end:

    export NODE_HOME=/usr/local/node/node-v0.10.0-linux-x64
    export PATH=$PATH:$NODE_HOME/bin
    export NODE_PATH=$NODE_HOME/lib/node_modules

save it then source it with:
    #source /etc/profile

4) you can check it's version with:
    #node -v

## 3. install npm
node.js bundles npm now, we needn't to install it. Check it with:
    # npm -v

## 4. install express globally using npm tool
    # npm install -g express
After installation, you will find the express dir in: /usr/local/node/node-v0.10.0-linux-x64/lib/node_modules

## 5. install redis client globally
like express, install it with:
    #npm install -g redis

## 6. install socket.io globally
    #npm install -g socket.io

## 7.install redis server
1)Go to http://www.redis.io/, download a steady distribution. like redis 2.6.12.

2)unpack it, then compile and install it.

    # tar xzvf redis-2.6.12.tar.gz
    # cd redis-2.6.12
    # make && make install

3) check it

    # redis-server -v

4) configuration file is "redis.conf" in the redis directory.

5) you could only compile it, but not to install it. Thus, when running reids-server, you need to find it in the very directory of redis.

# ---------SwiftMarket source codes-------
1) get source codes
you can download them from https://github.com/jackielihf/SwiftMarket
or check out them with git from https://github.com/jackielihf/SwiftMarket.git

2) architecture of source codes

    SwiftMarket/
        |--DataSimulator/
            |--bin/                
            |--build/
            |-src/
            |--MANIFEST.MF
        |--DataPusher/
            |--pusher.js
            |--index.html
        |--doc/
            |--some documents including Design Guide
        |--redis/
            |--redis.conf
        |--README.md
        |--startAll.sh

# ----------compile Swift Market----------
## 1. build DataSimulator
DataSimulator is coded in Java.

    $cd SwiftMarket/DataSimulator/build
    $sh build.sh
when finished, it will generate a jar file "dataSimulator.jar" in the current dir.

## 2. build DataPusher
DataPusher is written in node.js. We needn't to compile it.


# -----------run and test ----------------
## 1. run DataSimulator
you can run dataSimulator defaultly on port 9000, or specify another port by using option "-p".

    $java -jar dataSimulator.jar [-p port]

## 2. run redis server
redis.conf is the setting file of redis server. you can modify it. Defaultly, it runs locally on port 6379.

    $redis-server redis.conf

## 3. run DataPusher
After starting redis server, you can run the DataPusher.HTTP server listens on port 9090 defaultly.

    $cd DataPusher
    $node pusher.js

Also, you can use the options to specify settings:

    $node pusher.js [-p port] [-r redisServerIp][-rp redisServerPort] [-s dataSimulatorIp][-sp dataSimulatorPort]

## 4. startAll.sh
optionally, you can execute th shell script startAll.sh to run DataSimulator, redis server and DataPusher.
    
    $sh startAll.sh

## 5. access web site from web brower
if the steps above finished successfully, you can access the web site from web brower now.
open local web brower, input the default address:
    http://localhost:9090

or access from non-local web brower by inputing: 
    http://DataPusher's IP address:9090

You will see the Home page of SwiftMarket. The real-time stock data updates dynamically.





