//Initialisation des replica sets et du cluster MongoDB sharded

rs.initiate({
    _id: "configReplSet",
    configsvr: true,
    members: [
      { _id: 0, host: "localhost:26050" },
      { _id: 1, host: "localhost:26051" },
      { _id: 2, host: "localhost:26052" }
    ]
  })
  
rs.initiate({
    _id: "shard1",
    members: [
      { _id: 0, host: "localhost:27010" },
      { _id: 1, host: "localhost:27011" },
      { _id: 2, host: "localhost:27012" }
    ]
})
  

rs.initiate({
    _id: "shard2",
    members: [
      { _id: 0, host: "localhost:27013" },
      { _id: 1, host: "localhost:27014" },
      { _id: 2, host: "localhost:27015" }
    ]
})
  
rs.initiate({
    _id: "shard3",
    members: [
      { _id: 0, host: "localhost:27016" },
      { _id: 1, host: "localhost:27017" },
      { _id: 2, host: "localhost:27018" }
    ]
})
  
sh.addShard("shard1/localhost:27010,localhost:27011,localhost:27012")
sh.addShard("shard2/localhost:27013,localhost:27014,localhost:27015")
sh.addShard("shard3/localhost:27016,localhost:27017,localhost:27018")
  
sh.enableSharding("labeldb")
  