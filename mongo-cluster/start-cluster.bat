@echo off

REM Lancer les config servers
start mongod --configsvr --replSet configReplSet --port 26050 --dbpath config\cfg1 --logpath config\cfg1.log
start mongod --configsvr --replSet configReplSet --port 26051 --dbpath config\cfg2 --logpath config\cfg2.log
start mongod --configsvr --replSet configReplSet --port 26052 --dbpath config\cfg3 --logpath config\cfg3.log

REM Lancer les shards
start mongod --shardsvr --replSet shard1 --port 27010 --dbpath shard1\s1a --logpath shard1\s1a.log
start mongod --shardsvr --replSet shard1 --port 27011 --dbpath shard1\s1b --logpath shard1\s1b.log
start mongod --shardsvr --replSet shard1 --port 27012 --dbpath shard1\s1c --logpath shard1\s1c.log

start mongod --shardsvr --replSet shard2 --port 27013 --dbpath shard2\s2a --logpath shard2\s2a.log
start mongod --shardsvr --replSet shard2 --port 27014 --dbpath shard2\s2b --logpath shard2\s2b.log
start mongod --shardsvr --replSet shard2 --port 27015 --dbpath shard2\s2c --logpath shard2\s2c.log

start mongod --shardsvr --replSet shard3 --port 27016 --dbpath shard3\s3a --logpath shard3\s3a.log
start mongod --shardsvr --replSet shard3 --port 27017 --dbpath shard3\s3b --logpath shard3\s3b.log
start mongod --shardsvr --replSet shard3 --port 27018 --dbpath shard3\s3c --logpath shard3\s3c.log

REM Lancer mongos
start mongos --configdb configReplSet/localhost:26050,localhost:26051,localhost:26052 --port 27020 --logpath mongos.log
