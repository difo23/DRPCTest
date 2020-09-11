var NodeDRPCClient = require('node-drpc');
var hostName = "10.10.11.119";
var portNo = 3772;

var timeout = null;
var stormFunctionName = 'exclamation';

var Array_String = ['A','B','C','D','E','F','G','H','I','J','K'];
var nodeDrpcClient = new NodeDRPCClient(hostName, portNo, timeout);

Array_String.forEach(function(item){
        console.time(item.toString());
        nodeDrpcClient.execute(stormFunctionName, item, function(err, response) {
        if (err) {
            console.error(err);
        } else {
            console.log("Storm DRPC Response : ", response);
            console.timeEnd(item.toString());
        }
       });
    });