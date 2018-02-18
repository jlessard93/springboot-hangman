/**
 * 
 */

var hangmanApp = angular.module('hangmanApp', ['ngResource']);

var hostName = location.hostname;
var port = location.port;

hangmanApp.constant('configConstants', {
//	hostPort : "http://" + hostName + ":8080/",
	hostPort : "http://" + hostName + ":" + port + "/",
	mainApiUrl : "api/v1/hangman/"
});

