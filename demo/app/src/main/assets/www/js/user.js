var User = {};
var BRIDGE_KEY = 'test';

User.login = function(name,password){
    Demo.login(name,password);
}

User.register = function(name,password,email){
    Demo.register(name,password,email);
}

User.delete = function(id){
    Demo.delete(id);
}
User.callBack = function(result){

     if(result.success){

          alert("login success");

     }

}