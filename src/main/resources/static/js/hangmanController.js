
hangmanApp.controller('HangmanController', function($scope, $http, $resource, HangmanResourceSvc, $window) {
	
	var answerObj = {};
	var playerStatus = "UNKNOWN";
	var gameId;
	var game = {}
	
	function startNewGame() {
		
		$scope.guessedLetter = "";
		$scope.hangmanLetters = [];
		$scope.guessedLetterArr = [];
		
		$scope.winner = false;
		$scope.loser = false;
		
		answerObj = {};
		game.playerStatus = playerStatus;
		game.gameId = $scope.hangman.gameId;
		
		//START NEW GAME AND DISCARD OLD ONE
		
		HangmanResourceSvc.anotherGame.now(game).$promise.then(function(result){
			
			$scope.hangman = result;
			$scope.guessWordId = result.guessWordId;
			$scope.guessWordLength = result.guessWordlength;
			
			var x;
			for(x = 1; x <= $scope.guessWordLength; x++) {
				$scope.hangmanLetters.push("_");
			}
		}).
		catch(function() {
			$scope.loser = true;
			alert("Something went wrong in the system. Please try again later");
		});
	}
	
	
	//START OF GAME
	
	$scope.guessedLetter = "";
	$scope.hangmanLetters = [];
	$scope.guessedLetterArr = [];
	
	$scope.winner = false;
	$scope.loser = false;
	
	// NOT WORKING!!
	// MUST UPDATE STATUS WHEN PLAYER WINS THE GAME!!
	var arrIndices;
	
	HangmanResourceSvc.newGame.get(function(result){
		
		$scope.hangman = result;
		$scope.guessWordId = result.guessWordId;
		$scope.guessWordLength = result.guessWordlength;
		
		var x;
		for(x = 1; x <= $scope.guessWordLength; x++) {
			$scope.hangmanLetters.push("_");
		}
		
		// NOT WORKING!!
		// MUST UPDATE STATUS WHEN PLAYER WINS THE GAME!!
		if(result.correctGuessedLetters != null && result.correctGuessedLetters.length > 0) {
			var y;
			var arrCorrectLetters = result.correctGuessedLetters.split('');
			arrIndices = result.correctIndices.split('');
			var correctIndex;
			var correctLetter;
			for(y = 0; y <= arrIndices.length; y++) {
				correctIndex = arrIndices[y];
				correctLetter = arrCorrectLetters[y];
				$scope.hangmanLetters[correctIndex] = correctLetter;
			}
		}
		
		
		
	}, function(error) {
		if(error.status == 500) {
			alert("Something went wrong in the system. Please try again later");
		}
		
	});
	
	
	
	$scope.startNewGame = function() {
		startNewGame();
	}
	
	
	$scope.guess = function() {
		
		if($scope.guessedLetter == null || $scope.guessedLetter == "") {
			$scope.guessedLetter = "xxx!!!";
		}
		
		answerObj.guessLetter = $scope.guessedLetter;
		answerObj.guessWordId = $scope.guessWordId;
		
		// NOT WORKING!!
		// MUST UPDATE STATUS WHEN PLAYER WINS THE GAME!!
		if(arrIndices != null && arrIndices.length > 0) {
			answerObj.prevLetterIndexList = arrIndices;
		}
		
		HangmanResourceSvc.guess.process(answerObj).$promise.then(function(result){
			
			answerObj = result;
			
			if(answerObj.correctAnswer == false) {
				//USED LETTERS
				$scope.guessedLetterArr.push($scope.guessedLetter);
				$scope.hangman.chances = $scope.hangman.chances - 1;
				$scope.guessedLetter = "";
				
				if($scope.hangman.chances == 0) {
					$scope.loser = true;
					playerStatus = "LOSER";
					
					game.playerStatus = playerStatus;
					game.gameId = $scope.hangman.gameId;
					updateGame(game);
				}
				
			} else {
				$scope.guessedLetter = "";
				var x;
				var newIndexValue;
				for(x = 0; x < answerObj.newLetterIndexList.length; x++) {
					newIndexValue = answerObj.newLetterIndexList[x];
					$scope.hangmanLetters[newIndexValue] = answerObj.correctLetter;
				}
				
				if(answerObj.winner == true) {
					$scope.winner = true;
					playerStatus = "WINNER";
					
					game.playerStatus = playerStatus;
					game.gameId = $scope.hangman.gameId;
					updateGame(game);
				}
			}
			
		}).
		catch(function() {
			$scope.loser = true;
			alert("Something went wrong. Please start a new game.");
		});
	}
	
	function updateGame(gameStat) {
		HangmanResourceSvc.updateGame.process(gameStat);
	}
	
	
});





























