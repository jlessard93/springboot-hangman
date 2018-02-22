# springboot-hangman
Sample implementation of hangman game using Spring Boot

Game features:

Players are able to start a new game. Even if there is an existing on going game.

Players are able to interact to the game system and guess a character for an ongoing game through a provided web app.

Notifications are received when the game ends (win/game over).

Players can resume an incomplete game.

Players are able to access a Management Page and see all the games that have been played so far and the ones that are currently ongoing.

Error notifications are also rendered when the player triggers an action which results in a server failure.

The program should keeps the current game state persistent across server and browser re-starts.
