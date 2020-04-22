# Ultimate Tic-Tac-Toe

A command-line, Java implementation of Ultimate Tic-Tac-Toe



## Rules

The rules of Ultimate Tic-Tac-Toe are fairly simple. The board is a 9x9 grid, organized as a 3x3 grid of 3x3 standard boards. 

### Choosing a Move

Just like regular tic-tac-toe, one player plays X and the other player O. Play alternates between the players. Each turn, the player chooses an open space on the board to move to. 

The first difference from normal tic-tac-toe is that your opponent's move dictates what board you must move to on your turn. For example, if your opponent moves to the bottom left of one of the small 3x3 boards, you must then move somewhere on the bottom left 3x3 board. Your opponent is then similarly bound by your move. 

If there are no spaces available on the board that your opponent selected for you, you are free to move to any open space on the board. 

### Winning the Game

The second difference from normal tic-tac-toe is that getting three in a row is not enough to win. If you score three in a row on one of the smaller boards, good for you. You have won that board, but not the game. To win the game, you must win three **boards** in a row, viewing the entire 9x9 board as a 3x3 board made of 3x3 boards. 

Note that your opponent can force you to move on a board that has already been won. In this case, your move cannot change who captured that board. Once a player has won one of the smaller boards, it remains theirs for the duration of the game. 



## Use

In order to use this project, just download the code, compile it, and follow the console menu. Note that you can also run the test cases included with the project if you intend to modify it. 

When entering moves, note that the spaces are numbered from 0 to 8, right-to-left and top-to-bottom. You will need to use this system to dictate your moves to the program. 

This project was built and tested in Eclipse IDE on Windows 10. 



## Documentation

The design of this project is such that it should be easily extendable, while still remaining easy to work with. Extendibility, easy modification, and reliability are more important in this project than performance (it is, after all, only a command-line game). 

### Major Classes

* **Board** and **SmallBoard** - Represent a 9x9 and 3x3 board, respectively
* **Player** - Extendable abstract class that requires subclasses to describe move logic
* **Game** - Orchestrates the other classes and generally runs the game
* **Piece** - Decouples the Player from the Board by hiding some of the Player methods
* **Move** - Decouples the Game and Board from the Player by allowing players to read but not change game state and limiting them to a single move each turn
* **Spectator** - Allows custom actions to be taken whenever a player makes a move. The only current subclass is DisplaySpectator, which displays the game state after each move as well as the move that was made, allowing a human to watch as a game is played between computer players

These are the major classes in the project, but various subclasses and exception classes are not included here. Obviously classes meant for testing purposes only are also not included. 

#### Implementation Note - AI Player

The AI Player built using a Monte Carlo simulation. To decide on a move, it essentially clones the board, makes whatever move it is considering, and then sets up two random players playing against each other on that board. Moves are ranked based on how many of the simulated games the AI won. 

The reason this is not done with minmax is because it is difficult to score states in tic-tac-toe (but see the note below).

A possible improvement to this algorithm would be to use a minmax search for a few levels, and then use Monte Carlo for ranking. 

### Testing

Much of the project's internal logic is accompanied by a set of JUnit tests, and a number of utility classes have been created to facilitate easier testing: 

* **TestingPlayer**: A player with no move-making logic whatsoever. In the future this will likely include a way to determine which moves it makes in order to test the Move class. 
* **BoardTester** and **SmallBoardTester**: These allow tests to make several moves on a Board or SmallBoard in one method call. 
* **ScoreAIPlayer**: Tests the AI of AIPlayer by running several games against RandomPlayer very quickly and reporting a score. 

### Extending

To extend this project, you will need to create a subclass of one of the following classes: 

* **Player** - Extend this and override the makeMove() method to create a new type of player. Possibilities include any new type of AI player or a network player. 
* **Spectator** - Extend this class to create a class that can watch the game without interfering. Each time a move is made you will be passed the Move object that was used to make it. This will allow you to read the current game state as well as seeing what move was just made. 