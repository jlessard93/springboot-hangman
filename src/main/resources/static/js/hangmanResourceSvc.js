/**
 * 
 */

hangmanApp.service('HangmanResourceSvc', function($http, $resource, configConstants) {
	
	var newGameUrl = configConstants.hostPort + configConstants.mainApiUrl + "newgame";
	var guessWordUrl = configConstants.hostPort + configConstants.mainApiUrl + "guessword";
	var anotherGameUrl = configConstants.hostPort + configConstants.mainApiUrl + "anothergame";
	var updateGameUrl = configConstants.hostPort + configConstants.mainApiUrl + "updategame";
	
	return {
		newGame : $resource(newGameUrl),
		guess: $resource(guessWordUrl,
			{},
			{
				process : {
					method: 'POST'
				}
			},
			{}
		),
		anotherGame : $resource(anotherGameUrl,
				{},
				{
					now : {
						method : 'POST'
					}
				},
				{}
		),
		updateGame : $resource(updateGameUrl,
				{},
				{
					process : {
						method : 'POST'
					}
				},
				{}
		)
	}
	
});




