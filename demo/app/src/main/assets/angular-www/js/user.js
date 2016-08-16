var User = {};
var BRIDGE_KEY = 'test';

User.list = function(){
    return UserApi.list();
}
User.callBack = function(result){

     if(result.success){

          alert("login success");

     }

}