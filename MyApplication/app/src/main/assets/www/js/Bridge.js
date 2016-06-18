var Bridge = {};
var BRIDGE_KEY = 'test';

Bridge.login = function(state,name,password){

     //build json string

     var message = {method:"login", state:state, name:name, password:password}

     prompt(BRIDGE_KEY, JSON.stringify(message));

}

Bridge.callBack = function(result){

     if(result.success){

          alert("login success");

     }

}