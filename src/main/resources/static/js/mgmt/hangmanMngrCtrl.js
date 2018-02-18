
hangmanAppMngr.controller('HangmanMngrCtrl', function($scope, $http, $resource, HangmanMngrResrcSvc) {
	
	$scope.sample = "SAMPLE FROM HangmanController";
	
	HangmanMngrResrcSvc.allStartedGames.get(function(result){
		$scope.numOfStartedGames = result.numberOfStartedGames;
	});
	
	HangmanMngrResrcSvc.allStatsReport.get(function(result){
		$scope.fullReport = result.allReports;
	});
	
});





























