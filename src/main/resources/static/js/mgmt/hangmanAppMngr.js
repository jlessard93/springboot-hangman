/**
 * 
 */

var hangmanAppMngr = angular.module('hangmanAppMngr', ['ngResource']);

var hostName = location.hostname;
var port = location.port;

hangmanAppMngr.constant('configMngrConstants', {
//	hostPort : "http://" + hostName + ":8080/",
	hostPort : "http://" + hostName + ":" + port + "/",
	mainApiUrl : "api/v1/hangman/management/"
});

