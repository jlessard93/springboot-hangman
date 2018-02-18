/**
 * 
 */

hangmanAppMngr.service('HangmanMngrResrcSvc', function($http, $resource, configMngrConstants) {
	
	var numOfStartedGamesUrl = configMngrConstants.hostPort + configMngrConstants.mainApiUrl + "allstartedgames";
	var allStatsReportUrl = configMngrConstants.hostPort + configMngrConstants.mainApiUrl + "allstatistics";
	
	return {
		allStartedGames : $resource(numOfStartedGamesUrl),
		allStatsReport : $resource(allStatsReportUrl)
	}
	
});




