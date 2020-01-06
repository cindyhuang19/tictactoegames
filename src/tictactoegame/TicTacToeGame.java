/* ISU Game
 Cindy Huang
 Ms. Karasinska
 June 13, 2018
 
 Program description: This program allows the user to play several variations of the game "Tic Tac Toe", which can be 
 played against an AI or another human player. The player can choose between playing basic tic tac toe, tic tac toe on a 
 bigger board (where the dimensions can be 4x4 or 5x5 depending on the user's input), misere tic tac toe, or numerical tic
 tac toe.
 
 Data dictionary:
 game. - prefix indicates method belongs to instance
 rows - stores number of rows on board
 row - stores row coordinate of player's move
 rowVal - stores row coordinate of computer's move
 columns - stores number of columns on board
 col - stores column coordinate of player's move
 colVal - stores column coordinate of computer's move
 board - 2D array that stores String values on game board (e.g. ' ', 'X', or 'O' depending on move)
 numBoard - 2D array that stores String values on game board for numerical tic tac toe (e.g. numbers '1' to '9')
 turn - stores turn number for game
 number - stores number that player wants to place in numerical tic tac toe
 playerOne - stores character player one plays as
 playerTwo - stores character player two plays as
 playerOneTwo - stores player one and two's characters respectively in array to store values returned from players method (which determines which player will play as which character)
 playerChars -  stores player one and two's characters respectively in array within players method (which determines which player will play as which character)
 delay - stores input from user to create delay so user can read at own pace
 gameRule - stores user input for which game's rules the user would like to read
 gamePlay - stores user input for which game the user would like to play
 mode - stores user input for whether the user wants to play against an AI or not
 difficulty - stores user input for which difficulty level of AI the user wants to play against
 computerMove - stores AI's move coordinates (returned from compMove method) in array
 winningMove - stores coordinates for move which will win the game for the AI
 blockingMove - stores coordinates for move which will block the human player (for AI)
 playerMove - stores player's last move (used to calculate AI's next move in misere tic tac toe)
 move - stores AI's move coordinates to be returned by compMove method
 i - temporary value used in for loop (stores row number when going through loop)
 j - temporary value used in for loop (stores column number when going through loop)
 k - temporary value used in for loop (used to count number of times horizontal dividers on board should be printed)
 value - variable parameter (String or int depending on method) for cell method which is used to check the value to be printed for each board space
 result - stores value to be printed (" " or "X" or "O" on board based on 'value' parameter of cell method) and returned at end of cell method so value can be used in printBoard method
 even - stores possible even numbers that can be placed in numerical tic tac toe
 odd - stores possible odd numbers that can be placed in numerical tic tac toe
 tf - temporary value that stores boolean value in winningMove and blockingMove methods (if true, a winning/blocking move has been found and the rest of the method can be skipped)
 used - ArrayList that contains numbers already used in numerical tic tac toe
 */

package tictactoegame;

import java.util.ArrayList; // import statement - lets system known what ArrayList is and available methods

class TicTacToeGame // beginning of class
{   
  public static void main(String [] args) // beginning of main method
  {   
    TicTacToeGame game = new TicTacToeGame(); // creates new instance of class
    // declare and initialize variables
    int rows = 3; 
    int columns = 3; 
    String [][] board = new String [rows][columns]; // declares and initializes array
    int [][] numBoard = new int [rows][columns]; // declares and initializes array
    int row = 0;
    int col = 0;
    int turn = 0;
    String playerOne = "";
    String playerTwo = "";
    String [] playerOneTwo = new String [2]; // declares and initializes array
    int delay = 0;
    String gamePlay = "";
    String mode = "";
    String difficulty = "";
    
    // prints title graphic
    System.out.println("  _______         ______              ______         ");
    System.out.println(" /_  __(_)____   /_  __/___ ______   /_  __/___  ___ ");
    System.out.println("  / / / / ___/    / / / __ `/ ___/    / / / __ \\/ _ \\");
    System.out.println(" / / / / /__     / / / /_/ / /__     / / / /_/ /  __/");
    System.out.println("/_/ /_/\\___/    /_/  \\__,_/ \\___/   /_/  \\____/ \\___/ ");
    System.out.println("");
    
    System.out.println("Welcome to Tic Tac Toe"); // prints welcome message
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
    // prints game description
    System.out.println("Tic tac toe is a two-player game played on a three by three grid board. One player places X's on");
    System.out.println("the board and the other player places O's on the board.");
    System.out.println(); // prints empty line
    System.out.println("While alternating placing their respective symbols on the board, players try to get three in a row");
    System.out.println("of their symbol. The row can be vertical, horizontal, or diagonal. The game ends when either player");
    System.out.println("has three in a row or all nine squares are filled and the game ends in a draw. You can choose to play");
    System.out.println("against either another human player or against a computer.");
    System.out.println("Press enter to continue"); // prompts user to press enter
    delay = In.getInt(); // retrieves user input
    
    game.rules(); // runs rules method (lists rules according to user input)
    
    do // beginning of loop
    {
      System.out.println("Enter a letter from 'a' to 'd' to play the game (enter 0 to exit). If you would like to return to the rule menu, enter 'Y'."); // prompts user to enter game option to play corresponding game (or return to rule menu)
      gamePlay = In.getString(); // retrieves user input
      gamePlay = gamePlay.trim(); // removes spaces from beginning and end of user input
      
      if (gamePlay.equalsIgnoreCase("a")) // checks if user enters 'a' or 'A'
      {
        System.out.println("Welcome to Basic Tic Tac Toe"); // prints welcome message
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        rows = 3; // sets number of rows to 3
        columns = 3; // sets number of columns to 3
        board = new String [rows][columns]; // sets board array to new dimensions 
        do // beginning of loop
        {
          System.out.println("Would you like to play against the computer? Enter 'Y' for yes or 'N' for no."); // asks user if they want to play against the computer, prompts user to enter input
          mode = In.getString(); // retrieves user input
          mode = mode.trim(); // removes spaces from beginning and end of user input
          if (!mode.equalsIgnoreCase("Y") && !mode.equalsIgnoreCase("N")) // checks if user input is valid
          {
            System.out.println("Invalid answer. Re-enter your answer."); // prints error message, prompts user to re-enter input
          }
        } while (!mode.equalsIgnoreCase("Y") && !mode.equalsIgnoreCase("N")); // loops while user input is not 'y' or 'Y' or 'n' or 'N'
        
        if (mode.equalsIgnoreCase("Y")) // checks if user wants to play against computer
        {
          do // beginning of loop
          {
            System.out.println("Enter a difficulty mode:"); // prompts user to enter difficulty
            System.out.println("        E - easy"); // prints easy difficulty
            System.out.println("        M - medium"); // prints medium difficulty
            System.out.println("        H - hard"); // prints hard difficulty
            difficulty = In.getString(); // retrieves user input
            difficulty = difficulty.trim(); // removes spaces from beginning and end of user input
            if (!difficulty.equalsIgnoreCase("E") && !difficulty.equalsIgnoreCase("M") && !difficulty.equalsIgnoreCase("H")) // checks if user input is valid
            {
              System.out.println("Invalid character. Re-enter the difficulty you would like to play."); // prints error message, prompts user to re-enter input
            }
          } while (!difficulty.equalsIgnoreCase("E") && !difficulty.equalsIgnoreCase("M") && !difficulty.equalsIgnoreCase("H")); // loops while user input is invalid
        }
        playerOneTwo = game.players(playerOne, playerTwo); // sets array value to values from players method
        playerOne = playerOneTwo[0]; // sets playerOne character to first value (either 'X' or 'O') in playerOneTwo array
        playerTwo = playerOneTwo[1]; // sets playerTwo character to other value (either 'X' or 'O') in playerOneTwo array
        game.basic(row, col, turn, rows, columns, playerOne, playerTwo, board, mode, difficulty, delay, gamePlay); // runs basic method
      }
      else if (gamePlay.equalsIgnoreCase("b")) // checks if user enters 'b' or 'B'
      {
        System.out.println("Welcome to Bigger Board Tic Tac Toe"); // prints welcome message
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        do // beginning of loop
        {
          System.out.println("Enter the number of rows and columns you would like the tic tac toe board to have (minimum 4, maximum 5). (Note: board is square)"); // prompts user to enter dimensions of board
          rows = In.getInt(); // retrieves user input
          columns = rows; // sets number of columns to same number as number of rows
          if (rows != 4 && rows != 5) // checks if number of rows is 4 or 5
          {
            System.out.println("Invalid number. Re-enter your number."); // prints error message, prompts user to re-enter input
          }
        } while (rows != 4 && rows != 5); // loops while number of rows is invalid
        board = new String [rows][columns]; // sets up board dimensions based on user input
        do // beginning of loop
        {
          System.out.println("Would you like to play against the computer? Enter 'Y' for yes or 'N' for no."); // asks user if they want to play against the computer, prompts user to enter input
          mode = In.getString(); // retrieves user input
          mode = mode.trim(); // removes spaces from beginning and end of user input
          if (!mode.equalsIgnoreCase("Y") && !mode.equalsIgnoreCase("N")) // checks if user input is valid
          {
            System.out.println("Invalid answer. Re-enter your answer."); // prints error message, prompts user to re-enter input
          }
        } while (!mode.equalsIgnoreCase("Y") && !mode.equalsIgnoreCase("N")); // loops while user input is not 'y' or 'Y' or 'n' or 'N'
        
        if (mode.equalsIgnoreCase("Y")) // checks if user wants to play against computer
        {
          do // beginning of loop
          {
            System.out.println("Enter a difficulty mode:"); // prompts user to enter difficulty
            System.out.println("        E - easy"); // prints easy difficulty
            System.out.println("        M - medium"); // prints medium difficulty
            System.out.println("        H - hard"); // prints hard difficulty
            difficulty = In.getString(); // retrieves user input
            difficulty = difficulty.trim(); // removes spaces from beginning and end of user input
            if (!difficulty.equalsIgnoreCase("E") && !difficulty.equalsIgnoreCase("M") && !difficulty.equalsIgnoreCase("H")) // checks if user input is valid
            {
              System.out.println("Invalid character. Re-enter the difficulty you would like to play."); // prints error message, prompts user to re-enter input
            }
          } while (!difficulty.equalsIgnoreCase("E") && !difficulty.equalsIgnoreCase("M") && !difficulty.equalsIgnoreCase("H")); // loops while user input is invalid
        }
        playerOneTwo = game.players(playerOne, playerTwo); // sets array value to values from players method
        playerOne = playerOneTwo[0]; // sets playerOne character to first value (either 'X' or 'O') in playerOneTwo array
        playerTwo = playerOneTwo[1]; // sets playerTwo character to other value (either 'X' or 'O') in playerOneTwo array
        game.basic(row, col, turn, rows, columns, playerOne, playerTwo, board, mode, difficulty, delay, gamePlay); // runs basic method
      }
      else if (gamePlay.equalsIgnoreCase("c")) // checks if user enters 'c' or 'C'
      {
        System.out.println("Welcome to Mis�re Tic Tac Toe"); // prints welcome message
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        rows = 3; // sets number of rows to 3
        columns = 3; // sets number of columns to 3
        board = new String [rows][columns]; // sets board array to new dimensions 
        
        do // beginning of loop
        {
          System.out.println("Would you like to play against the computer? Enter 'Y' for yes or 'N' for no."); // asks user if they want to play against the computer, prompts user to enter input
          mode = In.getString(); // retrieves user input
          mode = mode.trim(); // removes spaces from beginning and end of user input
          if (!mode.equalsIgnoreCase("Y") && !mode.equalsIgnoreCase("N")) // checks if user input is valid
          {
            System.out.println("Invalid answer. Re-enter your answer."); // prints error message, prompts user to re-enter input
          }
        } while (!mode.equalsIgnoreCase("Y") && !mode.equalsIgnoreCase("N")); // loops while user input is not 'y' or 'Y' or 'n' or 'N'
        
        if (mode.equalsIgnoreCase("Y")) // checks if user wants to play against computer
        {
          do // beginning of loop
          {
            System.out.println("Enter a difficulty mode:"); // prompts user to enter difficulty
            System.out.println("        E - easy"); // prints easy difficulty
            System.out.println("        M - medium"); // prints medium difficulty
            System.out.println("        H - hard"); // prints hard difficulty
            difficulty = In.getString(); // retrieves user input
            difficulty = difficulty.trim(); // removes spaces from beginning and end of user input
            if (!difficulty.equalsIgnoreCase("E") && !difficulty.equalsIgnoreCase("M") && !difficulty.equalsIgnoreCase("H")) // checks if user input is valid
            {
              System.out.println("Invalid character. Re-enter the difficulty you would like to play."); // prints error message, prompts user to re-enter input
            }
          } while (!difficulty.equalsIgnoreCase("E") && !difficulty.equalsIgnoreCase("M") && !difficulty.equalsIgnoreCase("H")); // loops while user input is invalid
        }
        playerOneTwo = game.players(playerOne, playerTwo); // sets array value to values from players method
        playerOne = playerOneTwo[0]; // sets playerOne character to first value (either 'X' or 'O') in playerOneTwo array
        playerTwo = playerOneTwo[1]; // sets playerTwo character to other value (either 'X' or 'O') in playerOneTwo array
        game.misere(row, col, turn, rows, columns, playerOne, playerTwo, board, mode, delay, difficulty); // runs misere method
      }
      else if (gamePlay.equalsIgnoreCase("d")) // checks if user enters 'd' or 'D'
      {
        System.out.println("Welcome to Numerical Tic Tac Toe"); // prints welcome message
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        rows = 3; // sets number of rows to 3
        columns = 3; // sets number of rows to 3
        board = new String [rows][columns]; // sets board array to new dimensions 
        game.numerical(row, col, turn, rows, columns, playerOne, playerTwo, numBoard); // runs numerical method
      }
      else if (gamePlay.equalsIgnoreCase("Y")) // checks if user enters 'y' or 'Y'
      {
        game.rules(); // runs rules method
      }
      else if (gamePlay.equals("0")) // checks if user enters '0'
      {
        System.out.println("Goodbye!"); // prints goodbye message      
      }
      else // does not meet other conditions
      {
        System.out.println("Invalid input. Re-enter the game you want to play."); // error message, prompts user to re-enter input
      }
    } while (!gamePlay.equals("0")); // loops while user input is not '0'
  }
  
  public void rules() // beginning of method which lists game modes and prints the corresponding rules based on user input
  {
    String gameRule = ""; // declares and initializes local variable
    do // beginning of loop
    {
      System.out.println("There are several variations of tic-tac-toe that can be played:"); // prints message
      System.out.println("      a - basic"); // prints option
      System.out.println("      b - bigger board"); // prints option
      System.out.println("      c - misere"); // prints option
      System.out.println("      d - numerical"); // prints option
      
      System.out.println("Enter a letter from 'a' to 'd' to see the rules of each mode (enter 0 to skip instructions)."); // prompts user to enter game option to see corresponding rules
      gameRule = In.getString();  // retrieves user input
      gameRule = gameRule.trim(); // removes spaces from beginning and end of user input
      
      if (gameRule.equalsIgnoreCase("a")) // checks if user enters 'a' or 'A'
      {
        System.out.println("Basic: place X's or O's to get three in a row (can be horizontal, vertical, or diagonal).");// prints rules
      }
      else if (gameRule.equalsIgnoreCase("b")) // checks if user enters 'b' or 'B'
      {
        System.out.println("Bigger Board: enter dimensions of square board to be played on (up to 5). On a 4x4 board,"); // prints rules
        System.out.println("place X's and O's to get four in a row. For board size 5x5 place X's and O's to get"); // prints rules
        System.out.println("five in a row. Rows can be horizontal, vertical, or diagonal."); // prints rules
      }
      else if (gameRule.equalsIgnoreCase("c")) // checks if user enters 'c' or 'C'
      {
        System.out.println("Mis�re: players avoid getting three in a row. Player that gets three in a row loses the game."); // prints rules
        System.out.println("Rows can be horizontal, vertical, or diagonal."); // prints rules
      }
      else if (gameRule.equalsIgnoreCase("d")) // checks if user enters 'e' or 'E'
      {
        System.out.println("Numerical: players take turns placing numbers between 1 and 9 (inclusive). Numbers cannot be repeated."); // prints rules
        System.out.println("The goal is to get three numbers in a row that sum to 15. One player uses even numbers and"); // prints rules
        System.out.println("and the other player uses odd numbers. Rows can be horizontal, vertical, or diagonal."); // prints rules
      }
      else if (gameRule.equals("0")) // checks if user enters '0'
      {
        System.out.println("Okay."); // prints message
      }
      else
      {
        System.out.println("Invalid input. Re-enter the game you want to read the rules for."); // prints error message, prompts user to re-enter input
      }
    } while (!gameRule.equals("0")); // loops while user input is not '0'
  }
  
  public String [] players(String playerOne, String playerTwo) // beginning of players method which asks the user which character they would like to play as and returns an array containing the player one and player two characters respectively (in an array)
  {
    String [] playerChars = new String [2]; // declares and initializes array 
    do // beginning of loop
    {
      System.out.println("Player One, would you like to play as player 'X' or player 'O'?"); // asks player one which character they would like to use
      playerOne = In.getString(); // retrieves input from user
      playerOne = playerOne.trim(); // removes spaces from beginning and end of user input
      if (playerOne.equalsIgnoreCase("X")) // checks if player wants to play as 'X'
      {
        playerChars[0] = "X"; // sets playerOne to 'X'
        playerChars[1] = "O"; // sets playerTwo to 'O'
      }
      else if (playerOne.equalsIgnoreCase("O")) // checks if player wants to play as 'O'
      {
        playerChars[0] = "O"; // sets playerOne to 'O'
        playerChars[1] = "X"; // sets playerTwo to 'X'
      }
      else // does not meet other conditions
      {
        System.out.println("Invalid character. Re-enter the character you would like to play as."); // prints error message, prompts user to re-enter input
      }
    } while (!playerOne.equalsIgnoreCase("X") && !playerOne.equalsIgnoreCase("O")); // loops while user input is invalid
    return playerChars; // returns playerChars array (containing player characters)
  }
  
  public String playerTurn(int turn, String playerOne, String playerTwo) // beginning of playerTurn method which determines which player's turn it is
  {
    if (turn % 2 != 0) // checks if the turn is not an even number
    {
      return playerTwo; // returns playerTwo's character
    }
    else // does not meet other conditions
    {
      return playerOne; // returns playerOne's character
    }
  }
  
  public void basic(int row, int col, int turn, int rows, int columns, String playerOne, String playerTwo, String [][] board, String mode, String difficulty, int delay, String gamePlay) // beginning of method which runs the basic version of tic-tac-toe
  {
    System.out.println("Player One is " + playerTurn(turn, playerOne, playerTwo) + "."); // prints message
    if (mode.equalsIgnoreCase("Y")) // checks if user wants to play against computer
    {
      System.out.println("The computer is " + playerTurn(turn + 1, playerOne, playerTwo) + "."); // prints computer's character (value retrieved by calling playerTurn method)
    }
    else // does not meet other conditions
    {
      System.out.println("Player Two is " + playerTurn(turn + 1, playerOne, playerTwo) + "."); // prints player two's character (value retrieved by calling playerTurn method)
    }
    System.out.println("Press enter to continue"); // prompts user to press enter
    delay = In.getInt(); // retrieves user input
    initBoard(rows, columns, board); // initializes board to blank spaces
    printBoard(rows, columns, row, col, board); // prints board
    do // beginning of loop
    {
      do // beginning of loop
      {
        System.out.println("Player '" + playerTurn(turn, playerOne, playerTwo) + "', enter your move (row [1-" + rows + "] column [1-" + columns + "])"); // prompts player to enter move
        if (mode.equalsIgnoreCase("Y") && turn % 2 != 0) // checks if the player is playing against the computer, if yes, checks if it is the computer's turn
        {
          int [] computerMove = compMove(rows, columns, difficulty, board, playerTwo, playerOne); // sets values in computerMove array to array values returned by compMove method
          row = computerMove[0]; // sets row coordinate to first element in computerMove array
          col = computerMove[1]; // sets row coordinate to second element in computerMove array
        }
        else // does not meet other conditions
        {
          do // beginning of loop
          {
            System.out.println("Enter your row number."); // prompts user to enter row coordinate
            row = In.getInt(); // retrieves input from user
            if (row < 1 || row > rows) // checks if row number is valid
            {
              System.out.println("Invalid row number. Re-enter the row number."); // prints error message, prompts user to re-enter value
            }
          } while (row < 1 || row > rows); // loops while row number is invalid
          
          row = row - 1; // reduces row number by one (adjusts for array indices)
          
          do // beginning of loop
          {    
            System.out.println("Enter your column number"); // prompts user to enter column coordinate
            col = In.getInt(); // retrieves input from user
            if (col < 1|| col > columns) // checks if column number is valid
            {
              System.out.println("Invalid column number. Re-enter the column number."); // prints error message, prompts user to re-enter value
            }
          } while (col < 1 || col > columns); // loops while column number is invalid
          
          col = col - 1; // reduces column number by one (adjusts for array indices)
          
          if (!(board [row][col]).equals(" ")) // checks if space is empty
          {
            System.out.println("This space is already taken. Try another move."); // prints message, prompts user to re-enter coordinates
          }
        }
      } while (!(board [row][col]).equals(" ")); // loops while selected space is not empty (while move is invalid)
      
      board [row][col] = playerTurn(turn, playerOne, playerTwo); // sets space to current player's character
      printBoard(rows, columns, row, col, board); // prints board with moves
      turn++; // increases number of turns by one      
    } while (!hasWon(board, row, col, rows, columns) && turn < (Math.pow(rows,2))); // loops while no one has won and while number of turns is less than the number of spaces
    
    if (hasWon(board, row, col, rows, columns)) // checks if anyone has won
    {
      System.out.println("Player '" + playerTurn(turn - 1, playerOne, playerTwo) + "' wins!"); // prints winning message
    }
    else // does not meet other conditions
    {
      System.out.println("Tie!"); // prints tie message
    }
  }
  
  public int [] compMove(int rows, int columns, String difficulty, String [][] board, String playerTwo, String playerOne) // beginning of method which chooses the computer's move based on difficulty
  {
    int rowVal = 0; // declares and initializes variable
    int colVal = 0; // declares and initializes variable
    int [] winningMove = willWin(board, rows, columns, playerTwo); // declares and initializes array with values returned by willWin method
    int [] blockingMove = willBlock(board, rows, columns, playerOne); // declares and initializes array with values returned by willBlock method
    int [] move = new int [2]; // declares and initializes array 
    
    if (difficulty.equalsIgnoreCase("E")) // checks if difficulty is easy
    {
      do // beginning of loop
      {
        rowVal = (int)(rows * Math.random()); // sets row coordinate to randomly generated row number
        colVal = (int)(columns * Math.random()); // sets column coordinate to randomly generated column number
      } while (!(board [rowVal][colVal]).equals(" ")); // loops while selected space is not empty
    }
    else if (difficulty.equalsIgnoreCase("M")) // checks if difficulty is medium
    {
      if (winningMove != null) // checks if there is a winning move
      {
        rowVal = winningMove[0]; // sets row coordinate to first element in winningMove array
        colVal = winningMove[1]; // sets column coordinate to second element in winningMove array
      }
      else if (blockingMove != null) // checks if there is a blocking move
      {
        rowVal = blockingMove[0]; // sets row coordinate to first element in blockingMove array
        colVal = blockingMove[1]; // sets column coordinate to second element in blockingMove array       
      }
      else // does not meet other conditions
      {
        do // beginning of loop
        {
          rowVal = (int)(rows * Math.random()); // sets row coordinate to randomly generated row number
          colVal = (int)(columns * Math.random()); // sets column coordinate to randomly generated column number
        } while (!(board[rowVal][colVal]).equals(" ")); // loops while selected space is not empty
      }
    }
    else // does not meet other conditions
    {
      if ((board [rows/2][columns/2]).equals(" ")) // checks if centre space is empty (for 4x4 space is not exactly centered)
      {
        rowVal = rows/2; // sets row coordinate to centre row (for 4x4 row is not exactly centered)
        colVal = columns/2; // sets column coordinate to centre column (for 4x4 column is not exactly centered)
      }
      else if (winningMove != null) // checks if there is a winning move
      {
        rowVal = winningMove[0]; // sets row coordinate to first element in winningMove array
        colVal = winningMove[1]; // sets column coordinate to second element in winningMove array
      }
      else if (blockingMove != null) // checks if there is a blocking move
      {
        rowVal = blockingMove[0]; // sets row coordinate to first element in blockingMove array
        colVal = blockingMove[1]; // sets column coordinate to second element in blockingMove array              
      }
      else if (corner(board, rows, columns) == true) // checks if corners are available
      {
        do // beginning of loop
        {
          rowVal = (rows - 1) * ((int)(2 * Math.random())); // sets row coordinate to either first or last row (randomly generated)
          colVal = (columns - 1) * ((int)(2 * Math.random())); // sets column coordinate to either first or last column (rwndomly generated)
        } while (!(board [rowVal][colVal]).equals(" ")); // loops while selected space is not empty
      }
      else // does not meet other conditions
      {
        do // beginning of loop
        {
          rowVal = (int)(rows * Math.random()); // sets row coordinate to randomly generated row number
          if (rowVal == 0 || rowVal == (rows - 1)) // checks if row number is first or last row
          {
            colVal = (int)((columns - 2) * Math.random()) + 1; // sets column coordinate to randomly generated column number (excluding first and last)
          }
          else // does not meet other conditions
          {
            colVal = (columns - 1) * ((int)(2 * Math.random())); // sets column coordinate to either first or last column (randomly generated)
          }
        } while (!(board [rowVal][colVal]).equals(" ")); // loops while selected space is not empty
      }
    }
    move[0] = rowVal; // sets first element in move array to row coordinate
    move[1] = colVal; // sets second element in move array to column coordinate
    return move; // returns move array
  }
  
  public void initBoard(int rows, int columns, String [][] board) // beginning of method which initializes the board for normal tic tac toe
  {
    for (int i = 0; i < rows; i++) // loops through the number of rows
    {
      for (int j = 0; j < columns; j++) // loops through the number of columns
      {
        board [i][j] = " "; // sets empty values to spaces on board
      }
    }
  }
  
  public void initBoard(int rows, int columns, int [][] numBoard) // beginning of method which initializes the board for numerical tic tac toe
  {
    for (int i = 0; i < rows; i++) // loops through the number of rows
    {
      for (int j = 0; j < columns; j++) // loops through the number of columns
      {
        numBoard [i][j] = 0; // sets values of spaces on board to 0
      }
    }
  }
  
  public void printBoard(int rows, int columns, int row, int col, String [][] board) // beginning of method which prints the board for normal tic tac toe
  {
    for (int j = 1; j <= columns; j++) // loops through the number of columns
    {
      System.out.print("   " + j + " "); // prints column numbers
    }
    System.out.println(); // prints to next line
    for (int i = 0; i < rows; i++) // loops through the number of rows
    {
      System.out.print(i + 1 + " "); // prints row number
      for (int j = 0; j < columns; j++) // loops through the number of columns
      {
        System.out.print(" " + cell(board[i][j])); // prints cell value
        System.out.print(" | "); // prints vertical divider
      }
      System.out.println(); // prints to next line
      if (i != rows - 1) // checks if the loop is not on the last row
      {
        System.out.print(" "); // prints empty space
        for (int k = 0; k < rows; k++) // loops through number of columns
        {
          System.out.print("-----"); // prints horizontal divider
        }
        System.out.println(); // prints to next line
      }
    }
  }
  
  public void printBoard(int rows, int columns, int row, int col, int [][] numBoard) // beginning of method which prints the board for numerical tic tac toe
  {
    System.out.println("   1    2    3 "); // prints column headers
    for (int i = 0; i < rows; i++) // loops through number of rows
    {
      System.out.print(i + 1 + " "); // prints row number
      for (int j = 0; j < columns; j++) // loops through columns
      {
        System.out.print(" " + cell(numBoard[i][j])); // prints cell contents
        if (j != columns - 1) // checks if loop is at last column or not, if not, enters
        {
          System.out.print(" | "); // prints vertical divider
        }
      }
      System.out.println(); // prints to next line
      if (i != rows - 1) // checks if loop is at last row or not, if not, enters
      {
        System.out.println(" ---------------"); // prints horizontal divider
      }
    }
  }
  
  public String cell(String value) // beginning of method which returns the value of a cell on the board for normal tic tac toe
  {
    String result = " "; // declares and initializes variable
    switch (value.charAt(0)) // checks character at index 0 of String in cell
    {
      case ' ': // when character is space
        result = " "; // sets result to space
        break; // break
      case 'X': // when character is X
        result = "X"; // sets result to X
        break; // break
      case 'O': // when character is O
        result = "O"; // sets result to O
        break; // break
    }
    return result; // returns cell value
  }
  
  public String cell(int value) // beginning of method which returns the value of a cell on the board for numerical tic tac toe
  {
    String result = ""; // declares and initializes variable
    switch (value) // checks numerical value of cell
    {
      // assigns corresponding string values to integer values in cell
      case 0: // when value is 0
        result = " "; 
        break; // break
      case 1: // when value is 1
        result = "1";
        break; // break
      case 2: // when value is 2
        result = "2";
        break; // break
      case 3: // when value is 3
        result = "3";
        break; // break
      case 4: // when value is 4
        result = "4";
        break; // break
      case 5: // when value is 5
        result = "5";
        break; // break
      case 6: // when value is 6
        result = "6";
        break; // break
      case 7: // when value is 7
        result = "7";
        break; // break
      case 8: // when value is 8
        result = "8";
        break; // break
      case 9: // when value is 9
        result = "9";
        break; // break
    }
    return result; // returns result
  }
  
  public boolean hasWon(String [][] board, int row, int col, int rows, int columns) // beginning of method which checks if anyone has won in normal tic tac toe
  {
    if (hasWonHorizontal(board, rows) == true || hasWonVertical(board, columns) == true || hasWonDiagonal(board, rows) == true) // checks if there's three in a row in any direction
    {
      return true; // returns true
    }
    else // does not meet other conditions
    {
      return false; // returns false
    }
  }
     public boolean hasWon(int [][] numBoard, int row, int col, int rows, int columns) // beginning of method which checks if anyone has won three in a row on the numerical tic tac toe board
  {
    if (hasWonHorizontal(numBoard, rows) == true || hasWonVertical(numBoard, columns) == true || hasWonDiagonal(numBoard) == true) // checks if anyone has won three in a row in any direction
    {
      return true; // returns true
    }
    else // does not meet other conditions
    {
      return false; // returns false
    }
  }
    
  public boolean hasWonHorizontal(String [][] board, int rows) // beginning of method which checks if anyone has won horizontally
  {
    if (rows == 3) // checks if the number of rows is 3
    {
      for (int i = 0; i < rows; i++) // loops through rows
      {
        if ((board[i][0]).equals(board[i][1]) && (board[i][1]).equals(board[i][2]) && !(board[i][0]).equals(" ")) // checks if there are three in a row
        {
          return true; // returns true
        }
      }
      return false; // returns false
    }
    else if (rows == 4) // checks if the number of rows is 4
    {
      for (int i = 0; i < rows; i++) // loops through rows
      {
        if ((board[i][0]).equals(board[i][1]) && (board[i][1]).equals(board[i][2]) && (board[i][2]).equals(board[i][3]) && !(board[i][0]).equals(" ")) // checks if there are four in a row
        {
          return true; // returns true
        }
      }
      return false; // returns false
    }
    else // does not meet other conditions
    {
      for (int i = 0; i < rows; i++) // loops through rows
      {
        if ((board[i][0]).equals(board[i][1]) && (board[i][1]).equals(board[i][2]) && (board[i][2]).equals(board[i][3]) && (board[i][3]).equals(board[i][4])&& !(board[i][0]).equals(" ")) // checks if there are five in a row
        {
          return true; // returns true
        }
      }
      return false; // returns false
    }     
  }
  
   public boolean hasWonHorizontal(int [][] numBoard, int rows) // beginning of method which checks if anyone has won three in a row horizontally in numerical tic tac toe
  {
    for (int i = 0; i < rows; i++) // loops through rows
    {
      if (numBoard[i][0] + numBoard[i][1] + numBoard[i][2] == 15 && numBoard[i][0] != 0 && numBoard[i][1] != 0 && numBoard[i][2] != 0) // checks if numbers sum to 15 and that all the cells in the row are filled
      {
        return true; // returns true
      }
    }
    return false; // returns false
  }
  
  public boolean hasWonVertical(String [][] board, int columns) // beginning of method which checks if anyone has won vertically
  {
    if (columns == 3) // checks if the number of columns is 3
    {
      for (int j = 0; j < columns; j++) // loops through columns
      {
        if ((board[0][j]).equals(board[1][j]) && (board[1][j]).equals(board[2][j]) && !(board[0][j]).equals(" ")) // checks if there are three in a row (vertically)
        {
          return true; // returns true
        }
      }
      return false; // returns false
    }
    else if (columns == 4) // checks if the number of columns is 4
    {
      for (int j = 0; j < columns; j++) // loops through columns
      {
        if ((board[0][j]).equals(board[1][j]) && (board[1][j]).equals(board[2][j]) && (board[2][j]).equals(board[3][j]) && !(board[0][j]).equals(" ")) // checks if there are four in a row (vertically)
        {
          return true; // returns true
        }
      }
      return false; // returns false
    }
    else // does not meet other conditions
    {
      for (int j = 0; j < columns; j++) // loops through columns
      {
        if ((board[0][j]).equals(board[1][j]) && (board[1][j]).equals(board[2][j]) && (board[2][j]).equals(board[3][j]) && (board[3][j]).equals(board[4][j]) && !(board[0][j]).equals(" ")) // checks if there are five in a row (vertically)
        {
          return true; // returns true
        }
      }
      return false; // returns false
    }
  }
  public boolean hasWonVertical(int [][] numBoard, int columns) // beginning of method which checks if anyone has won three in a row vertically in numerical tic tac toe
  {
    for (int j = 0; j < columns; j++) // loops through columns
    {
      if (numBoard[0][j] + numBoard[1][j] + numBoard[2][j] == 15 && numBoard[0][j] != 0 && numBoard[1][j] != 0 && numBoard[2][j] != 0) // checks if numbers sum to 15 and that all the cells in the column are filled
      {
        return true; // returns true
      }
    }
    return false; // returns false
  }
    
  public boolean hasWonDiagonal(String [][] board, int rows) // beginning of method which checks if anyone has won diagonally
  {
    if (rows == 3) // checks if the number of rows is 3
    {
      if ((board[0][0]).equals(board[1][1]) && (board[1][1]).equals(board[2][2]) && !(board[0][0]).equals(" ")) // checks if there are three in a row (diagonally)
      {
        return true; // returns true
      }
      else if ((board[0][2]).equals(board[1][1]) && (board[1][1]).equals(board[2][0]) && !(board[0][2]).equals(" ")) // checks if there are three in a row (diagonally)
      {
        return true; // returns true
      }
      else // does not meet other conditions
      {
        return false; // returns false
      }
    }
    else if (rows == 4) // checks if the number of rows is 4
    {
      if ((board[0][0]).equals(board[1][1]) && (board[1][1]).equals(board[2][2]) && (board[2][2]).equals(board[3][3]) && !(board[0][0]).equals(" ")) // checks if there are four in a row (diagonally)
      {
        return true; // returns true
      }
      else if ((board[0][2]).equals(board[1][1]) && (board[1][1]).equals(board[2][0]) && (board[2][0]).equals(board[3][0]) && !(board[0][2]).equals(" ")) // checks if there are four in a row (diagonally)
      {
        return true; // returns true
      }
      else // does not meet other conditions
      {
        return false; // returns false
      }
    }
    else // does not meet other conditions
    {
      if ((board[0][0]).equals(board[1][1]) && (board[1][1]).equals(board[2][2]) && (board[2][2]).equals(board[3][3]) && (board[3][3]).equals(board[4][4]) &&  !(board[0][0]).equals(" ")) // checks if there are five in a row (diagonally)
      {
        return true; // returns true
      }
      else if ((board[0][2]).equals(board[1][1]) && (board[1][1]).equals(board[2][0]) && (board[2][0]).equals(board[3][0]) && (board[3][0]).equals(board[4][0]) && !(board[0][2]).equals(" ")) // checks if there are five in a row (diagonally)
      {
        return true; // returns true
      }
      else // does not meet other conditions
      {
        return false; // returns false
      }
    }
  }
  
  public boolean hasWonDiagonal(int [][] numBoard) // beginning of method which checks if anyone has won three in a row diagonally in numerical tic tac toe
  {
    if (numBoard[0][0] + numBoard[1][1] + numBoard[2][2] == 15 && numBoard[0][0] != 0 && numBoard[1][1] != 0 && numBoard[2][2] != 0) // checks if numbers sum to 15 and that all the cells in the diagonal are filled
    {
      return true; // returns true
    }
    else if (numBoard[0][2] + numBoard[1][1] + numBoard[2][0] == 15 && numBoard[0][2] != 0 && numBoard[1][1] != 0 && numBoard[2][0] != 0) // checks if numbers sum to 15 and that all the cells in the diagonal are filled
    {
      return true; // returns true
    }
    else // does not meet other conditions
    {
      return false; // returns false
    }
  }
  
  public int [] willWin(String [][] board, int rows, int columns, String playerTwo) // beginning of method which determines if there is a winning move for the computer
  {
    boolean tf = false; // declares and initializes variable
    int [] winningMove = new int [2]; // declares and initializes array
    if (rows == 3) // checks if number of rows is 3
    {
      for (int i = 0; i < rows; i++) //  loops through rows
      {
        /* checks if there are any winning moves for the computer (horizontally), if there are, tf is set to true, the row and column numbers are assigned, and the programs breaks 
           out of the loop. Otherwise, tf is set to false, and the program continues to loop until it breaks out or the for loop ends */
        if ((board[i][0]).equals(board[i][1]) && (board[i][2]).equals(" ") && (board[i][0]).equals(playerTwo)) 
        {
          tf = true;
          winningMove[0] = i; // sets row number for winning move
          winningMove[1] = 2; // sets column number for winning move
          break;
        }
        else if ((board[i][0]).equals(board[i][2]) && (board[i][1]).equals(" ") && (board[i][0]).equals(playerTwo))
        {
          tf = true;
          winningMove[0] = i; // sets row number for winning move
          winningMove[1] = 1; // sets column number for winning move
          break;
        }
        else if ((board[i][1]).equals(board[i][2]) && (board[i][0]).equals(" ") && (board[i][1]).equals(playerTwo))
        {
          tf = true;
          winningMove[0] = i; // sets row number for winning move
          winningMove[1] = 0; // sets column number for winning move
          break;
        }
        else // does not meet other conditions
        {
          tf = false;
        }
      }
      
      if (tf == false) // checks if a winning move has been found yet
      {
        for (int j = 0; j < columns; j++) // loops through columns
        {
          /* checks if there are any winning moves for the computer (vertically), if there are, tf is set to true, the row and column numbers are assigned, and the programs breaks 
           out of the loop. Otherwise, tf is set to false, and the program continues to loop until it breaks out or the for loop ends */
          if ((board[0][j]).equals(board[1][j]) && (board[2][j]).equals(" ") && (board[0][j]).equals(playerTwo))
          {
            tf = true;
            winningMove[0] = 2; // sets row number for winning move
            winningMove[1] = j; // sets column number for winning move
            break;
          }
          else if ((board[0][j]).equals(board[2][j]) && (board[1][j]).equals(" ") && (board[0][j]).equals(playerTwo))
          {
            tf = true;
            winningMove[0] = 1; // sets row number for winning move
            winningMove[1] = j; // sets column number for winning move
            break;
          }
          else if ((board[1][j]).equals(board[2][j]) && (board[0][j]).equals(" ") && (board[1][j]).equals(playerTwo))
          {
            tf = true;
            winningMove[0] = 0; // sets row number for winning move
            winningMove[1] = j; // sets column number for winning move
            break;
          }
          else // does not meet other conditions
          {
            tf = false;
          }
        }   
        if (tf == false) // checks if a winning move has been found yet
        {
          // checks if there are any winning moves for the computer (diagonally), if there are, the row and column numbers are assigned. Otherwise, winningMove is set to null
          if ((board[0][0]).equals(board[1][1]) && (board[2][2]).equals(" ") && (board[0][0]).equals(playerTwo))
          {
            winningMove[0] = 2; // sets row number for winning move
            winningMove[1] = 2; // sets column number for winning move
          }
          else if ((board[0][0]).equals(board[2][2]) && (board[1][1]).equals(" ") && (board[0][0]).equals(playerTwo))
          {
            winningMove[0] = 1; // sets row number for winning move
            winningMove[1] = 1; // sets column number for winning move
          }
          else if ((board[1][1]).equals(board[2][2]) && (board[0][0]).equals(" ") && (board[1][1]).equals(playerTwo))
          {
            winningMove[0] = 0; // sets row number for winning move
            winningMove[1] = 0; // sets column number for winning move
          } 
          else if ((board[0][2]).equals(board[2][0]) && (board[1][1]).equals(" ") && (board[0][2]).equals(playerTwo))
          {
            winningMove[0] = 1; // sets row number for winning move
            winningMove[1] = 1; // sets column number for winning move
          }
          else if ((board[0][2]).equals(board[1][1]) && (board[2][0]).equals(" ") && (board[0][2]).equals(playerTwo))
          {
            winningMove[0] = 2; // sets row number for winning move
            winningMove[1] = 0; // sets column number for winning move
          }
          else if ((board[1][1]).equals(board[2][0]) && (board[0][2]).equals(" ") && (board[1][1]).equals(playerTwo))
          {
            winningMove[0] = 0; // sets row number for winning move
            winningMove[1] = 2; // sets column number for winning move
          }
          else // does not meet other conditions
          {
            winningMove = null; // sets winning move to null (winning move does not exist)
          }
        }
      }
    }
    else if (rows == 4) // checks if number of rows is 4
    {
      for (int i = 0; i < rows; i++) // loops through rows
      {
        /* checks if there are any winning moves for the computer (horizontally), if there are, tf is set to true, the row and column numbers are assigned, and the programs breaks 
           out of the loop. Otherwise, tf is set to false, and the program continues to loop until it breaks out or the for loop ends */
        if ((board[i][0]).equals(board[i][1]) && (board[i][1]).equals(board[i][2]) && (board[i][3]).equals(" ") && (board[i][0]).equals(playerTwo))
        {
          tf = true;
          winningMove[0] = i; // sets row number for winning move
          winningMove[1] = 3; // sets column number for winning move
          break;
        }
        else if ((board[i][0]).equals(board[i][2]) && (board[i][2]).equals(board[i][3]) && (board[i][1]).equals(" ") && (board[i][0]).equals(playerTwo))
        {
          tf = true;
          winningMove[0] = i; // sets row number for winning move
          winningMove[1] = 1; // sets column number for winning move
          break;
        }
        else if ((board[i][0]).equals(board[i][1]) && (board[i][1]).equals(board[i][3]) && (board[i][2]).equals(" ") && (board[i][0]).equals(playerTwo))
        {
          tf = true;
          winningMove[0] = i; // sets row number for winning move
          winningMove[1] = 2; // sets column number for winning move
          break;
        }
        else if ((board[i][1]).equals(board[i][2]) && (board[i][2]).equals(board[i][3]) && (board[i][0]).equals(" ") && (board[i][1]).equals(playerTwo))
        {
          tf = true;
          winningMove[0] = i; // sets row number for winning move
          winningMove[1] = 0; // sets column number for winning move
          break;
        }
        else // does not meet other conditions
        {
          tf = false;
        }
      }
      if (tf == false) // checks if winning move has been found yet
      {
        for (int j = 0; j < columns; j++) // loops through columns
        {
          /* checks if there are any winning moves for the computer (vertically), if there are, tf is set to true, the row and column numbers are assigned, and the programs breaks 
         out of the loop. Otherwise, tf is set to false, and the program continues to loop until it breaks out or the for loop ends */
          if ((board[0][j]).equals(board[1][j]) && (board[1][j]).equals(board[2][j]) && (board[3][j]).equals(" ") && (board[0][j]).equals(playerTwo))
          {
            tf = true;
            winningMove[0] = 3; // sets row number for winning move
            winningMove[1] = j; // sets column number for winning move
            break;
          }
          else if ((board[0][j]).equals(board[2][j]) && (board[2][j]).equals(board[3][j]) && (board[1][j]).equals(" ") && (board[0][j]).equals(playerTwo))
          {
            tf = true;
            winningMove[0] = 1; // sets row number for winning move
            winningMove[1] = j; // sets column number for winning move
            break;
          }
          else if ((board[0][j]).equals(board[1][j]) && (board[1][j]).equals(board[3][j]) && (board[2][j]).equals(" ") && (board[0][j]).equals(playerTwo))
          {
            tf = true;
            winningMove[0] = 2; // sets row number for winning move
            winningMove[1] = j; // sets column number for winning move
            break;
          }
          else if ((board[1][j]).equals(board[2][j]) && (board[2][j]).equals(board[3][j]) && (board[0][j]).equals(" ") && (board[1][j]).equals(playerTwo))
          {
            tf = true;
            winningMove[0] = 0; // sets row number for winning move
            winningMove[1] = j; // sets column number for winning move
            break;
          }
          else // does not meet other conditions
          {
            tf = false; 
          }
        }   
        if (tf == false) // checks if winning move has been found yet
        {
          // checks if there are any winning moves for the computer (diagonally), if there are, the row and column numbers are assigned. Otherwise, winningMove is set to null
          if ((board[0][0]).equals(board[1][1]) && (board[1][1]).equals(board[2][2]) && (board[3][3]).equals(" ") && (board[0][0]).equals(playerTwo))
          {
            winningMove[0] = 3; // sets row number for winning move
            winningMove[1] = 3; // sets column number for winning move
          }
          else if ((board[0][3]).equals(board[1][2]) && (board[1][2]).equals(board[2][1]) && (board[3][0]).equals(" ") && (board[0][3]).equals(playerTwo))
          {
            winningMove[0] = 3; // sets row number for winning move
            winningMove[1] = 0; // sets column number for winning move
          }
          else if ((board[0][0]).equals(board[1][1]) && (board[1][1]).equals(board[3][3]) && (board[2][2]).equals(" ") && (board[0][0]).equals(playerTwo))
          {
            winningMove[0] = 2; // sets row number for winning move
            winningMove[1] = 2; // sets column number for winning move
          }
          else if ((board[0][3]).equals(board[1][2]) && (board[1][2]).equals(board[3][0]) && (board[2][1]).equals(" ") && (board[0][3]).equals(playerTwo))
          {
            winningMove[0] = 2; // sets row number for winning move
            winningMove[1] = 1; // sets column number for winning move
          } 
          else if ((board[0][0]).equals(board[2][2]) && (board[2][2]).equals(board[3][3]) && (board[1][1]).equals(" ") && (board[0][0]).equals(playerTwo))
          {
            winningMove[0] = 1; // sets row number for winning move
            winningMove[1] = 1; // sets column number for winning move
          }
          else if ((board[0][3]).equals(board[2][1]) && (board[2][1]).equals(board[3][0]) && (board[1][2]).equals(" ") && (board[0][3]).equals(playerTwo))
          {
            winningMove[0] = 1; // sets row number for winning move
            winningMove[1] = 2; // sets column number for winning move
          }
          else if ((board[1][1]).equals(board[2][2]) && (board[2][2]).equals(board[3][3]) && (board[0][0]).equals(" ") && (board[1][1]).equals(playerTwo))
          {
            winningMove[0] = 0; // sets row number for winning move
            winningMove[1] = 0; // sets column number for winning move
          }
          else if ((board[1][2]).equals(board[2][1]) && (board[2][1]).equals(board[3][0]) && (board[0][3]).equals(" ") && (board[1][2]).equals(playerTwo))
          {
            winningMove[0] = 0; // sets row number for winning move
            winningMove[1] = 3; // sets column number for winning move
          }
          else // does not meet other conditions
          {
            winningMove = null; // sets winning move to null (winning move does not exist)
          }
        }
      }
    }
    else // does not meet other conditions
    {
      for (int i = 0; i < rows; i++) // loops through rows
      {
        /* checks if there are any winning moves for the computer (horizontally), if there are, tf is set to true, the row and column numbers are assigned, and the programs breaks 
           out of the loop. Otherwise, tf is set to false, and the program continues to loop until it breaks out or the for loop ends */
        if ((board[i][0]).equals(board[i][1]) && (board[i][1]).equals(board[i][2]) && (board[i][2]).equals(board[i][3]) && (board[i][4]).equals(" ") && (board[i][0]).equals(playerTwo))
        {
          tf = true;
          winningMove[0] = i; // sets row number for winning move
          winningMove[1] = 4; // sets column number for winning move
          break;
        }
        else if ((board[i][0]).equals(board[i][2]) && (board[i][2]).equals(board[i][3]) && (board[i][3]).equals(board[i][4]) && (board[i][1]).equals(" ") && (board[i][0]).equals(playerTwo))
        {
          tf = true;
          winningMove[0] = i; // sets row number for winning move
          winningMove[1] = 1; // sets column number for winning move
          break;
        }
        else if ((board[i][0]).equals(board[i][1]) && (board[i][1]).equals(board[i][3]) && (board[i][3]).equals(board[i][4]) && (board[i][2]).equals(" ") && (board[i][0]).equals(playerTwo))
        {
          tf = true;
          winningMove[0] = i; // sets row number for winning move
          winningMove[1] = 2; // sets column number for winning move
          break;
        }
        else if ((board[i][0]).equals(board[i][1]) && (board[i][1]).equals(board[i][2]) && (board[i][2]).equals(board[i][4]) && (board[i][3]).equals(" ") && (board[i][0]).equals(playerTwo))
        {
          tf = true;
          winningMove[0] = i; // sets row number for winning move
          winningMove[1] = 3; // sets column number for winning move
          break;
        }
        else if ((board[i][1]).equals(board[i][2]) && (board[i][2]).equals(board[i][3]) && (board[i][3]).equals(board[i][4]) && (board[i][0]).equals(" ") && (board[i][4]).equals(playerTwo))
        {
          tf = true;
          winningMove[0] = i; // sets row number for winning move
          winningMove[1] = 0; // sets column number for winning move
          break;
        }
        else // does not meet other conditions
        {
          tf = false;
        }
      }
      if (tf == false) // checks if winning move has been found yet
      {
        for (int j = 0; j < columns; j++) // loops through columns
        {
          /* checks if there are any winning moves for the computer (vertically), if there are, tf is set to true, the row and column numbers are assigned, and the programs breaks 
           out of the loop. Otherwise, tf is set to false, and the program continues to loop until it breaks out or the for loop ends */
          if ((board[0][j]).equals(board[1][j]) && (board[1][j]).equals(board[2][j]) && (board[2][j]).equals(board[3][j]) && (board[4][j]).equals(" ") && (board[0][j]).equals(playerTwo))
          {
            tf = true;
            winningMove[0] = 4; // sets row number for winning move
            winningMove[1] = j; // sets column number for winning move
            break;
          }
          else if ((board[0][j]).equals(board[2][j]) && (board[2][j]).equals(board[3][j]) && (board[3][j]).equals(board[4][j]) && (board[1][j]).equals(" ") && (board[0][j]).equals(playerTwo))
          {
            tf = true;
            winningMove[0] = 1; // sets row number for winning move
            winningMove[1] = j; // sets column number for winning move
            break;
          }
          else if ((board[0][j]).equals(board[1][j]) && (board[1][j]).equals(board[3][j]) && (board[3][j]).equals(board[4][j]) && (board[2][j]).equals(" ") && (board[0][j]).equals(playerTwo))
          {
            tf = true;
            winningMove[0] = 2; // sets row number for winning move
            winningMove[1] = j; // sets column number for winning move
            break;
          }
          else if ((board[0][j]).equals(board[1][j]) && (board[1][j]).equals(board[2][j]) && (board[2][j]).equals(board[4][j]) && (board[3][j]).equals(" ") && (board[0][j]).equals(playerTwo))
          {
            tf = true;
            winningMove[0] = 3; // sets row number for winning move
            winningMove[1] = j; // sets column number for winning move
            break;
          }
          else if ((board[4][j]).equals(board[1][j]) && (board[1][j]).equals(board[2][j]) && (board[2][j]).equals(board[3][j]) && (board[0][j]).equals(" ") && (board[4][j]).equals(playerTwo))
          {
            tf = true;
            winningMove[0] = 0; // sets row number for winning move
            winningMove[1] = j; // sets column number for winning move
            break;
          }
          else // does not meet other conditions
          {
            tf = false;
          }
        }   
        if (tf == false) // checks if winning move has been found yet
        {
          // checks if there are any winning moves for the computer (diagonally), if there are, the row and column numbers are assigned. Otherwise, winningMove is set to null
          if ((board[0][0]).equals(board[1][1]) && (board[1][1]).equals(board[2][2]) && (board[2][2]).equals(board[3][3]) && (board[4][4]).equals(" ") && (board[0][0]).equals(playerTwo))
          {
            winningMove[0] = 4; // sets row number for winning move
            winningMove[1] = 4; // sets column number for winning move
          }
          else if ((board[0][4]).equals(board[1][3]) && (board[1][3]).equals(board[2][2]) && (board[2][2]).equals(board[3][1]) && (board[4][0]).equals(" ") && (board[0][4]).equals(playerTwo))
          {
            winningMove[0] = 4; // sets row number for winning move
            winningMove[1] = 0; // sets column number for winning move
          }
          else if ((board[0][0]).equals(board[1][1]) && (board[1][1]).equals(board[2][2]) && (board[2][2]).equals(board[4][4]) && (board[3][3]).equals(" ") && (board[0][0]).equals(playerTwo))
          {
            winningMove[0] = 3; // sets row number for winning move
            winningMove[1] = 3; // sets column number for winning move
          }
          else if ((board[0][4]).equals(board[1][3]) && (board[1][3]).equals(board[2][2]) && (board[2][2]).equals(board[4][0]) && (board[3][1]).equals(" ") && (board[0][4]).equals(playerTwo))
          {
            winningMove[0] = 3; // sets row number for winning move 
            winningMove[1] = 1; // sets column number for winning move
          }
          else if ((board[0][0]).equals(board[1][1]) && (board[1][1]).equals(board[3][3]) && (board[3][3]).equals(board[4][4]) && (board[2][2]).equals(" ") && (board[0][0]).equals(playerTwo))
          {
            winningMove[0] = 2; // sets row number for winning move
            winningMove[1] = 2; // sets column number for winning move
          }
          else if ((board[0][4]).equals(board[1][3]) && (board[1][3]).equals(board[3][1]) && (board[3][1]).equals(board[4][0]) && (board[2][2]).equals(" ") && (board[0][4]).equals(playerTwo))
          {
            winningMove[0] = 2; // sets row number for winning move
            winningMove[1] = 2; // sets column number for winning move
          }
          else if ((board[0][0]).equals(board[2][2]) && (board[2][2]).equals(board[3][3]) && (board[3][3]).equals(board[4][4]) && (board[1][1]).equals(" ") && (board[0][0]).equals(playerTwo))
          {
            winningMove[0] = 1; // sets row number for winning move
            winningMove[1] = 1; // sets column number for winning move
          }
          else if ((board[0][4]).equals(board[2][2]) && (board[2][2]).equals(board[3][1]) && (board[3][1]).equals(board[4][0]) && (board[1][3]).equals(" ") && (board[0][4]).equals(playerTwo))
          {
            winningMove[0] = 1; // sets row number for winning move
            winningMove[1] = 3; // sets column number for winning move
          }
          else if ((board[1][1]).equals(board[2][2]) && (board[2][2]).equals(board[3][3]) && (board[3][3]).equals(board[4][4]) && (board[0][0]).equals(" ") && (board[1][1]).equals(playerTwo))
          {
            winningMove[0] = 0; // sets row number for winning move
            winningMove[1] = 0; // sets column number for winning move
          }
          else if ((board[1][3]).equals(board[2][2]) && (board[2][2]).equals(board[3][1]) && (board[3][1]).equals(board[4][0]) && (board[0][4]).equals(" ") && (board[1][3]).equals(playerTwo))
          {
            winningMove[0] = 0; // sets row number for winning move
            winningMove[1] = 4; // sets column number for winning move
          }
          else // does not meet other conditions
          {
            winningMove = null; // sets winning move to null (winning move does not exist)
          }
        }
      }
    }
    return winningMove; // returns winningMove
  }
  
  public int [] willBlock(String [][] board, int rows, int columns, String playerOne)
  {
    boolean tf = false; // declares and initializes variable
    int [] blockingMove = new int [2]; // declares and initializes array
    if (rows == 3) // checks if number of rows is 3
    {
    for (int i = 0; i < rows; i++) // loops through rows
    {
      /* checks if there are any blocking moves for the computer (horizontally), if there are, tf is set to true, the row and column numbers are assigned, and the programs breaks 
           out of the loop. Otherwise, tf is set to false, and the program continues to loop until it breaks out or the for loop ends */
      if ((board[i][0]).equals(board[i][1]) && !(board[i][0]).equals(" ") && (board[i][2]).equals(" ") && (board[i][0]).equals(playerOne))
      {
        tf = true;
        blockingMove[0] = i; // sets row number for blocking move
        blockingMove[1] = 2; // sets column number for blocking move
        break;
      }
      else if ((board[i][0]).equals(board[i][2]) && !(board[i][0]).equals(" ") && (board[i][1]).equals(" ") && (board[i][0]).equals(playerOne))
      {
        tf = true;
        blockingMove[0] = i; // sets row number for blocking move
        blockingMove[1] = 1; // sets column number for blocking move
        break;
      }
      else if ((board[i][1]).equals(board[i][2]) && !(board[i][1]).equals(" ") && (board[i][0]).equals(" ") && (board[i][1]).equals(playerOne))
      {
        tf = true;
        blockingMove[0] = i; // sets row number for blocking move
        blockingMove[1] = 0; // sets column number for blocking move
        break;
      }
      else // does not meet other conditions
      {
        tf = false;
      }
    }
    if (tf == false) // checks if a blocking move has been found yet
    {
      for (int j = 0; j < columns; j++) // loops through columns
      {
        /* checks if there are any blocking moves for the computer (vertically), if there are, tf is set to true, the row and column numbers are assigned, and the programs breaks 
           out of the loop. Otherwise, tf is set to false, and the program continues to loop until it breaks out or the for loop ends */
        if ((board[0][j]).equals(board[1][j]) && !(board[0][j]).equals(" ") && (board[2][j]).equals(" ") && (board[0][j]).equals(playerOne))
        {
          tf = true;
          blockingMove[0] = 2; // sets row number for blocking move
          blockingMove[1] = j; // sets column number for blocking move
          break;
        }
        else if ((board[0][j]).equals(board[2][j]) && !(board[0][j]).equals(" ") && (board[1][j]).equals(" ") && (board[0][j]).equals(playerOne))
        {
          tf = true;
          blockingMove[0] = 1; // sets row number for blocking move
          blockingMove[1] = j; // sets column number for blocking move
          break;
        }
        else if ((board[1][j]).equals(board[2][j]) && !(board[1][j]).equals(" ") && (board[0][j]).equals(" ") && (board[1][j]).equals(playerOne))
        {
          tf = true;
          blockingMove[0] = 0; // sets row number for blocking move
          blockingMove[1] = j; // sets column number for blocking move
          break;
        }
        else // does not meet other conditions
        {
          tf = false;
        }
      }   
      if (tf == false) // checks if a blocking move has been found yet
      {
        // checks if there are any blocking moves for the computer (diagonally), if there are, the row and column numbers are assigned. Otherwise, winningMove is set to null
        if ((board[0][0]).equals(board[1][1]) && !(board[0][0]).equals(" ") && (board[2][2]).equals(" ") && (board[0][0]).equals(playerOne))
        {
          blockingMove[0] = 2; // sets row number for blocking move
          blockingMove[1] = 2; // sets column number for blocking move
        }
        else if ((board[0][0]).equals(board[2][2]) && !(board[0][0]).equals(" ") && (board[1][1]).equals(" ") && (board[0][0]).equals(playerOne))
        {
          blockingMove[0] = 1; // sets row number for blocking move
          blockingMove[1] = 1; // sets column number for blocking move
        }
        else if ((board[1][1]).equals(board[2][2]) && !(board[1][1]).equals(" ") && (board[0][0]).equals(" ") && (board[1][1]).equals(playerOne))
        {
          blockingMove[0] = 0; // sets row number for blocking move
          blockingMove[1] = 0; // sets column number for blocking move
        }
        else if ((board[0][2]).equals(board[2][0]) && !(board[0][2]).equals(" ") && (board[1][1]).equals(" ") && (board[0][2]).equals(playerOne))
        {
          blockingMove[0] = 1; // sets row number for blocking move
          blockingMove[1] = 1; // sets column number for blocking move
        }
        else if ((board[0][2]).equals(board[1][1]) && !(board[0][2]).equals(" ") && (board[2][0]).equals(" ") && (board[0][2]).equals(playerOne))
        {
          blockingMove[0] = 2; // sets row number for blocking move
          blockingMove[1] = 0; // sets column number for blocking move
        }
        else if ((board[1][1]).equals(board[2][0]) && !(board[1][1]).equals(" ") && (board[0][2]).equals(" ") && (board[1][1]).equals(playerOne))
        {
          blockingMove[0] = 0; // sets row number for blocking move
          blockingMove[1] = 2; // sets column number for blocking move
        }
        else // does not meet other conditions
        {
          blockingMove = null; // sets winning move to null (winning move does not exist)
        }
      }
    }
    }
     else if (rows == 4) // checks if number of rows is 4
    {
      for (int i = 0; i < rows; i++) // loops through rows
      {
        /* checks if there are any blocking moves for the computer (horizontally), if there are, tf is set to true, the row and column numbers are assigned, and the programs breaks 
           out of the loop. Otherwise, tf is set to false, and the program continues to loop until it breaks out or the for loop ends */
        if ((board[i][0]).equals(board[i][1]) && (board[i][1]).equals(board[i][2]) && (board[i][3]).equals(" ") && (board[i][0]).equals(playerOne))
        {
          tf = true;
          blockingMove[0] = i; // sets row number for blocking move
          blockingMove[1] = 3; // sets column number for blocking move
          break;
        }
        else if ((board[i][0]).equals(board[i][2]) && (board[i][2]).equals(board[i][3]) && (board[i][1]).equals(" ") && (board[i][0]).equals(playerOne))
        {
          tf = true;
          blockingMove[0] = i; // sets row number for blocking move
          blockingMove[1] = 1; // sets column number for blocking move
          break;
        }
        else if ((board[i][0]).equals(board[i][1]) && (board[i][1]).equals(board[i][3]) && (board[i][2]).equals(" ") && (board[i][0]).equals(playerOne))
        {
          tf = true;
          blockingMove[0] = i; // sets row number for blocking move
          blockingMove[1] = 2; // sets column number for blocking move
          break;
        }
        else if ((board[i][1]).equals(board[i][2]) && (board[i][2]).equals(board[i][3]) && (board[i][0]).equals(" ") && (board[i][1]).equals(playerOne))
        {
          tf = true;
          blockingMove[0] = i; // sets row number for blocking move
          blockingMove[1] = 0; // sets column number for blocking move
          break;
        }
        else // does not meet other conditions
        {
          tf = false;
        }
      }
      if (tf == false) // checks if a blocking move has been found yet
      {
        for (int j = 0; j < columns; j++) // loops through columns
        {
          /* checks if there are any blocking moves for the computer (vertically), if there are, tf is set to true, the row and column numbers are assigned, and the programs breaks 
           out of the loop. Otherwise, tf is set to false, and the program continues to loop until it breaks out or the for loop ends */
          if ((board[0][j]).equals(board[1][j]) && (board[1][j]).equals(board[2][j]) && (board[3][j]).equals(" ") && (board[0][j]).equals(playerOne))
          {
            tf = true;
            blockingMove[0] = 3; // sets row number for blocking move
            blockingMove[1] = j; // sets column number for blocking move
            break;
          }
          else if ((board[0][j]).equals(board[2][j]) && (board[2][j]).equals(board[3][j]) && (board[1][j]).equals(" ") && (board[0][j]).equals(playerOne))
          {
            tf = true;
            blockingMove[0] = 1; // sets row number for blocking move
            blockingMove[1] = j; // sets column number for blocking move
            break;
          }
          else if ((board[0][j]).equals(board[1][j]) && (board[1][j]).equals(board[3][j]) && (board[2][j]).equals(" ") && (board[0][j]).equals(playerOne))
          {
            tf = true; 
            blockingMove[0] = 2; // sets row number for blocking move
            blockingMove[1] = j; // sets column number for blocking move
            break;
          }
          else if ((board[1][j]).equals(board[2][j]) && (board[2][j]).equals(board[3][j]) && (board[0][j]).equals(" ") && (board[1][j]).equals(playerOne))
          {
            tf = true;
            blockingMove[0] = 0; // sets row number for blocking move
            blockingMove[1] = j; // sets column number for blocking move
            break;
          }
          else // does not meet other conditions
          {
            tf = false;
          }
        }   
        if (tf == false) // checks if a blocking move has been found yet
        {
          // checks if there are any blocking moves for the computer (diagonally), if there are, the row and column numbers are assigned. Otherwise, winningMove is set to null
          if ((board[0][0]).equals(board[1][1]) && (board[1][1]).equals(board[2][2]) && (board[3][3]).equals(" ") && (board[0][0]).equals(playerOne))
          {
            blockingMove[0] = 3; // sets row number for blocking move
            blockingMove[1] = 3; // sets column number for blocking move
          }
          else if ((board[0][3]).equals(board[1][2]) && (board[1][2]).equals(board[2][1]) && (board[3][0]).equals(" ") && (board[0][3]).equals(playerOne))
          {
            blockingMove[0] = 3; // sets row number for blocking move
            blockingMove[1] = 0; // sets column number for blocking move
          }
          else if ((board[0][0]).equals(board[1][1]) && (board[1][1]).equals(board[3][3]) && (board[2][2]).equals(" ") && (board[0][0]).equals(playerOne))
          {
            blockingMove[0] = 2; // sets row number for blocking move
            blockingMove[1] = 2; // sets column number for blocking move
          }
          else if ((board[0][3]).equals(board[1][2]) && (board[1][2]).equals(board[3][0]) && (board[2][1]).equals(" ") && (board[0][3]).equals(playerOne))
          {
            blockingMove[0] = 2; // sets row number for blocking move
            blockingMove[1] = 1; // sets column number for blocking move
          }
          else if ((board[0][0]).equals(board[2][2]) && (board[2][2]).equals(board[3][3]) && (board[1][1]).equals(" ") && (board[0][0]).equals(playerOne))
          {
            blockingMove[0] = 1; // sets row number for blocking move
            blockingMove[1] = 1; // sets column number for blocking move
          }
          else if ((board[0][3]).equals(board[2][1]) && (board[2][1]).equals(board[3][0]) && (board[1][2]).equals(" ") && (board[0][3]).equals(playerOne))
          {
            blockingMove[0] = 1; // sets row number for blocking move
            blockingMove[1] = 2; // sets column number for blocking move
          }
          else if ((board[1][1]).equals(board[2][2]) && (board[2][2]).equals(board[3][3]) && (board[0][0]).equals(" ") && (board[1][1]).equals(playerOne))
          {
            blockingMove[0] = 0; // sets row number for blocking move
            blockingMove[1] = 0; // sets column number for blocking move
          }
          else if ((board[1][2]).equals(board[2][1]) && (board[2][1]).equals(board[3][0]) && (board[0][3]).equals(" ") && (board[1][2]).equals(playerOne))
          {
            blockingMove[0] = 0; // sets row number for blocking move
            blockingMove[1] = 3; // sets column number for blocking move
          }
          else // does not meet other conditions
          {
            blockingMove = null; // sets blocking move to null (blocking move does not exist)
          }
        }
      }
    }
    else // does not meet other conditions
    {
      for (int i = 0; i < rows; i++) // loops through rows
      {
        /* checks if there are any blocking moves for the computer (horizontally), if there are, tf is set to true, the row and column numbers are assigned, and the programs breaks 
           out of the loop. Otherwise, tf is set to false, and the program continues to loop until it breaks out or the for loop ends */
        if ((board[i][0]).equals(board[i][1]) && (board[i][1]).equals(board[i][2]) && (board[i][2]).equals(board[i][3]) && (board[i][4]).equals(" ") && (board[i][0]).equals(playerOne))
        {
          tf = true;
          blockingMove[0] = i; // sets row number for blocking move
          blockingMove[1] = 4; // sets column number for blocking move
          break;
        }
        else if ((board[i][0]).equals(board[i][2]) && (board[i][2]).equals(board[i][3]) && (board[i][3]).equals(board[i][4]) && (board[i][1]).equals(" ") && (board[i][0]).equals(playerOne))
        {
          tf = true;
          blockingMove[0] = i; // sets row number for blocking move
          blockingMove[1] = 1; // sets column number for blocking move
          break;
        }
        else if ((board[i][0]).equals(board[i][1]) && (board[i][1]).equals(board[i][3]) && (board[i][3]).equals(board[i][4]) && (board[i][2]).equals(" ") && (board[i][0]).equals(playerOne))
        {
          tf = true;
          blockingMove[0] = i; // sets row number for blocking move
          blockingMove[1] = 2; // sets column number for blocking move
          break; 
        }
        else if ((board[i][0]).equals(board[i][1]) && (board[i][1]).equals(board[i][2]) && (board[i][2]).equals(board[i][4]) && (board[i][3]).equals(" ") && (board[i][0]).equals(playerOne))
        {
          tf = true;
          blockingMove[0] = i; // sets row number for blocking move
          blockingMove[1] = 3; // sets column number for blocking move
          break;
        }
        else if ((board[i][1]).equals(board[i][2]) && (board[i][2]).equals(board[i][3]) && (board[i][3]).equals(board[i][4]) && (board[i][0]).equals(" ") && (board[i][4]).equals(playerOne))
        {
          tf = true;
          blockingMove[0] = i; // sets row number for blocking move
          blockingMove[1] = 0; // sets column number for blocking move
          break;
        }
        else // does not meet other conditions
        {
          tf = false;
        }
      }
      if (tf == false) // checks if a blocking move has been found yet
      {
        for (int j = 0; j < columns; j++) // loops through columns
        {
          /* checks if there are any blocking moves for the computer (vertically), if there are, tf is set to true, the row and column numbers are assigned, and the programs breaks 
           out of the loop. Otherwise, tf is set to false, and the program continues to loop until it breaks out or the for loop ends */
          if ((board[0][j]).equals(board[1][j]) && (board[1][j]).equals(board[2][j]) && (board[2][j]).equals(board[3][j]) && (board[4][j]).equals(" ") && (board[0][j]).equals(playerOne))
          {
            tf = true;
            blockingMove[0] = 4; // sets row number for blocking move
            blockingMove[1] = j; // sets column number for blocking move
            break;
          }
          else if ((board[0][j]).equals(board[2][j]) && (board[2][j]).equals(board[3][j]) && (board[3][j]).equals(board[4][j]) && (board[1][j]).equals(" ") && (board[0][j]).equals(playerOne))
          {
            tf = true;
            blockingMove[0] = 1; // sets row number for blocking move
            blockingMove[1] = j; // sets column number for blocking move
            break;
          }
          else if ((board[0][j]).equals(board[1][j]) && (board[1][j]).equals(board[3][j]) && (board[3][j]).equals(board[4][j]) && (board[2][j]).equals(" ") && (board[0][j]).equals(playerOne))
          {
            tf = true;
            blockingMove[0] = 2; // sets row number for blocking move
            blockingMove[1] = j; // sets column number for blocking move
            break;
          }
          else if ((board[0][j]).equals(board[1][j]) && (board[1][j]).equals(board[2][j]) && (board[2][j]).equals(board[4][j]) && (board[3][j]).equals(" ") && (board[0][j]).equals(playerOne))
          {
            tf = true;
            blockingMove[0] = 3; // sets row number for blocking move
            blockingMove[1] = j; // sets column number for blocking move
            break;
          }
          else if ((board[4][j]).equals(board[1][j]) && (board[1][j]).equals(board[2][j]) && (board[2][j]).equals(board[3][j]) && (board[0][j]).equals(" ") && (board[4][j]).equals(playerOne))
          {
            tf = true;
            blockingMove[0] = 0; // sets row number for blocking move
            blockingMove[1] = j; // sets column number for blocking move
            break;
          }
          else // does not meet other conditions
          {
            tf = false;
          }
        }   
        if (tf == false) // checks if a blocking move has been found yet
        {
          // checks if there are any blocking moves for the computer (diagonally), if there are, the row and column numbers are assigned. Otherwise, winningMove is set to null
          if ((board[0][0]).equals(board[1][1]) && (board[1][1]).equals(board[2][2]) && (board[2][2]).equals(board[3][3]) && (board[4][4]).equals(" ") && (board[0][0]).equals(playerOne))
          {
            blockingMove[0] = 4; // sets row number for blocking move
            blockingMove[1] = 4; // sets column number for blocking move
          }
          else if ((board[0][4]).equals(board[1][3]) && (board[1][3]).equals(board[2][2]) && (board[2][2]).equals(board[3][1]) && (board[4][0]).equals(" ") && (board[0][4]).equals(playerOne))
          {
            blockingMove[0] = 4; // sets row number for blocking move
            blockingMove[1] = 0; // sets column number for blocking move
          }
          else if ((board[0][0]).equals(board[1][1]) && (board[1][1]).equals(board[2][2]) && (board[2][2]).equals(board[4][4]) && (board[3][3]).equals(" ") && (board[0][0]).equals(playerOne))
          {
            blockingMove[0] = 3; // sets row number for blocking move
            blockingMove[1] = 3; // sets column number for blocking move
          }
          else if ((board[0][4]).equals(board[1][3]) && (board[1][3]).equals(board[2][2]) && (board[2][2]).equals(board[4][0]) && (board[3][1]).equals(" ") && (board[0][4]).equals(playerOne))
          {
            blockingMove[0] = 3; // sets row number for blocking move
            blockingMove[1] = 1; // sets column number for blocking move
          }
          else if ((board[0][0]).equals(board[1][1]) && (board[1][1]).equals(board[3][3]) && (board[3][3]).equals(board[4][4]) && (board[2][2]).equals(" ") && (board[0][0]).equals(playerOne))
          {
            blockingMove[0] = 2; // sets row number for blocking move
            blockingMove[1] = 2; // sets column number for blocking move
          }
          else if ((board[0][4]).equals(board[1][3]) && (board[1][3]).equals(board[3][1]) && (board[3][1]).equals(board[4][0]) && (board[2][2]).equals(" ") && (board[0][4]).equals(playerOne))
          {
            blockingMove[0] = 2; // sets row number for blocking move
            blockingMove[1] = 2; // sets column number for blocking move
          }
          else if ((board[0][0]).equals(board[2][2]) && (board[2][2]).equals(board[3][3]) && (board[3][3]).equals(board[4][4]) && (board[1][1]).equals(" ") && (board[0][0]).equals(playerOne))
          {
            blockingMove[0] = 1; // sets row number for blocking move
            blockingMove[1] = 1; // sets column number for blocking move
          }
          else if ((board[0][4]).equals(board[2][2]) && (board[2][2]).equals(board[3][1]) && (board[3][1]).equals(board[4][0]) && (board[1][3]).equals(" ") && (board[0][4]).equals(playerOne))
          {
            blockingMove[0] = 1; // sets row number for blocking move
            blockingMove[1] = 3; // sets column number for blocking move
          }
          else if ((board[1][1]).equals(board[2][2]) && (board[2][2]).equals(board[3][3]) && (board[3][3]).equals(board[4][4]) && (board[0][0]).equals(" ") && (board[1][1]).equals(playerOne))
          { 
            blockingMove[0] = 0; // sets row number for blocking move
            blockingMove[1] = 0; // sets column number for blocking move
          }
          else if ((board[1][3]).equals(board[2][2]) && (board[2][2]).equals(board[3][1]) && (board[3][1]).equals(board[4][0]) && (board[0][4]).equals(" ") && (board[1][3]).equals(playerOne))
          {
            blockingMove[0] = 0; // sets row number for blocking move
            blockingMove[1] = 4; // sets column number for blocking move
          }
          else // does not meet other conditions
          {
            blockingMove = null; // sets blocking move to null (blocking move does not exist)
          }
        }
      }
    }
    return blockingMove; // returns blockingMove
  }
  
  public boolean corner(String [][] board, int rows, int columns) // beginning of method which checks if any corners are available
  {
    boolean tf = false;
    if ((board[0][0]).equals(" ")) // checks if top left corner is empty
    {
      tf = true; // sets tf to true
    }
    else if ((board[0][columns - 1]).equals(" ")) // checks if top right corner is empty
    {
      tf = true; // sets tf to true
    }
    else if ((board[rows - 1][0]).equals(" ")) // checks if bottom left corner is empty
    {
      tf = true; // sets tf to true
    }
    else if ((board[rows - 1][columns - 1]).equals(" ")) // checks if bottom right corner is empty
    {
      tf = true; // sets tf to true
    }
    else // does nto meet other conditions
    {
      tf = false; // sets tf to false (since there is not corner move available)
    }
    return tf; // returns true/false
  }
     
  public void misere(int row, int col, int turn, int rows, int columns, String playerOne, String playerTwo, String [][] board, String mode, int delay, String difficulty) // beginning of method which runs the misere version of the game
  {
    int [] playerMove = new int [2]; // declares and initializes array
    int [] winningMove = new int [2]; // declares and initializes array
    System.out.println("Player One is " + playerTurn(turn, playerOne, playerTwo) + "."); // prints message
    if (mode.equalsIgnoreCase("Y")) // checks if user is playing against computer
    {
      System.out.println("The computer is " + playerTurn(turn + 1, playerOne, playerTwo) + "."); // prints message
    }
    else // does not meet other conditions
    {
      System.out.println("Player Two is " + playerTurn(turn + 1, playerOne, playerTwo) + "."); // prints message
    }
    System.out.println("Press enter to continue"); // prompts user to press enter
    delay = In.getInt(); // retrieves user input
    initBoard(rows, columns, board); // initializes all board cells to empty spaces
    printBoard(rows, columns, row, col, board); // prints board
    
    do // beginning of loop
    {
      do // beginning of loop
      {
        System.out.println("Player '" + playerTurn(turn, playerOne, playerTwo) + "', enter your move (row [1-3] column [1-3])"); // prompts user to enter move
        if (mode.equalsIgnoreCase("Y") && turn % 2 != 0) // checks if user is playing against computer, if yes, checks if it is the computer's turn
        {
          winningMove = losingMove(difficulty, board, playerMove); // sets values of winning move to array returned by losingMove method
          row = winningMove[0]; // sets row coordinate to first element in winningMove array
          col = winningMove[1]; // sets column coordinate to second element in winningMove array
        }
        else // does not meet other conditions
        {
          do // beginning of loop
          {
            System.out.println("Enter your row number."); // prompts user to enter row number
            row = In.getInt(); // retreives user input
            if (row < 1 || row > rows) // checks if row number is valid
            {
              System.out.println("Invalid row number. Re-enter the row number."); // prints error message
            }
          } while (row < 1 || row > rows); // loops while row number is invalid
          row = row - 1; // reduces row number by one (adjusts for array indices)
          playerMove[0] = row; // sets first element in playerMove array to value of row coordinate
          do // beginning of loop
          {    
            System.out.println("Enter your column number"); // prompts user to enter column number
            col = In.getInt(); // retrieves user input
            if (col < 1|| col > columns) // checks if column number is valid
            {
              System.out.println("Invalid column number. Re-enter the column number."); // prints error message
            }
          } while (col < 1 || col > columns); // loops while column number is invalid
          col = col - 1; // reduces column number by one (adjusts for array indices)
          playerMove[1] = col; // sets second element in playerMove array to value of column coordinate
          if (!(board [row][col]).equals(" ")) // checks if selected space is available
          {
            System.out.println("This space is already taken. Try another move."); // prints error message, prompts user to re-enter move
          }
        }
      } while (!(board [row][col]).equals(" ")); // loops while selected space is noty available
      board [row][col] = playerTurn(turn, playerOne, playerTwo); // sets value of space to current player's character
      printBoard(rows, columns, row, col, board); // prints board
      turn++; // increases number of turns by one
    } while (!hasWon(board, row, col, rows, columns) && turn < 9); // loops while no one has won and the board is not full
    
    if (hasWon(board, row, col, rows, columns)) // checks if anyone has gotten three in a row
    {
      System.out.println("Player '" + playerTurn(turn - 1, playerOne, playerTwo) + "' loses!"); // prints losing message
    }
    else // does not meet other conditions
    {
      System.out.println("Tie!"); // prints tie message
    }
  }
  
  public int [] losingMove(String difficulty, String [][] board, int [] playerMove) // beginning of method which determines the computer's next move for misere tic tac toe
  {
    int [] move = new int [2]; // declares and initializes array    
    if (difficulty.equalsIgnoreCase("E")) // checks if difficulty is easy
    {
      do // beginning of loop
      {
        move[0] = (int)(3 * Math.random()); // sets row number to randomly generated number
        move[1] = (int)(3 * Math.random()); // sets column number to randomly generated number
      } while (!(board [move[0]][move[1]]).equals(" ")); // loops while space is unavailable
    }
    else if (difficulty.equalsIgnoreCase("M")) // checks if difficulty is medium
    {
      for (int i = 0; i < playerMove.length; i++) // loops through playerMove array
      {
        if (playerMove[i] == 0) // checks if the row number is 0
        {
          move[i] = 2; // sets row number to opposite (first to last row)
        }
        else if (playerMove[i] == 2) // checks if the row number is 2
        {
          move[i] = 0; // sets row number to opposite (last to first row)
        }
        else // does not meet other conditions
        {
          move[i] = 1; // row number stays the same
        }
        if (!(board [move[0]][move[1]]).equals(" ")) // checks if selected space is available
        {
          do // beginning of loop
          {
            move[0] = (int)(3 * Math.random()); // sets row coordinate to randomly generated row number
            move[1] = (int)(3 * Math.random()); // sets column coordinate to randomly generated column number
          } while (!(board [move[0]][move[1]]).equals(" ")); // loops while selected space is unavailable
        }
      }
    }
    else // does not meet other conditions
    {
      if ((board[1][1]).equals(" ")) // checks if centre space is available
      {
        move[0] = 1; // sets row coordinate to centre row
        move[1] = 1; // sets column coordinate to centre column
      }
      else // does not meet other conditions
      {
        for (int i = 0; i < playerMove.length; i++) // loops through playerMove array
        {
          if (playerMove[i] == 0) // checks if row number is 0
          {
            move[i] = 2; // sets row number to oppostie (first to last)
          }
          else if (playerMove[i] == 2) // checks if row number is 2
          {
            move[i] = 0; // sets row number to opposite (last to first)
          }
          else // does not meet other conditions
          {
            move[i] = 1; // row number stays the same
          }
        }
        if (!(board [move[0]][move[1]]).equals(" ")) // checks if selected space is available
        {
          do // beginning of loop
          {
            move[0] = (int)(3 * Math.random()); // sets row coordinate to randomly generated row number
            move[1] = (int)(3 * Math.random()); // sets column coordinate to randomly generated column number
          } while (!(board [move[0]][move[1]]).equals(" ")); // loops while selected space is unavailable
        }
      }
    }
    return move; // returns move
  }
  
  public void numerical(int row, int col, int turn, int rows, int columns, String playerOne, String playerTwo, int [][] numBoard) // beginning of method which runs the numerical version of tic tac toe
  {
    String even = "2468"; // declares and initializes variable
    String odd = "13579"; // declares and initializes variable
    ArrayList<Integer> used = new ArrayList<Integer>(); // declares and initializes ArrayList
    int number = 0; // declares and initializes variable
    do // beginning of loop
    {
      System.out.println("Player One, would you like to play with even numbers or odd numbers? Enter 'even' for even numbers or 'odd' for odd numbers."); // prompts user to enter the type of numbers they want to play with
      playerOne = In.getString(); // retrieves user input
      playerOne = playerOne.trim(); // removes spaces from beginning and end of user input
      if (playerOne.equalsIgnoreCase("even")) // checks if player enters 'even'
      {
        playerOne = "even"; // sets player one to even numbers
        playerTwo = "odd"; // sets player two to odd numbers
      }
      else if (playerOne.equalsIgnoreCase("odd")) // checks if player enters 'odd'
      {
        playerOne = "odd"; // sets player one to odd numbers
        playerTwo = "even"; // sets player two to even numbers
      }
      else // does not meet other conditions
      {
        System.out.println("Invalid option. Re-enter the type of numbers you would like to play with."); // prints error message, prompts user to re-enter input
      }
    } while (!playerOne.equalsIgnoreCase("even") && !playerOne.equalsIgnoreCase("odd")); // loops while user input is invalid
    initBoard(rows, columns, numBoard); // initializes board to empty spaces
    printBoard(rows, columns, row, col, numBoard); // prints board    
    do // beginning of loop
    {
      System.out.println("Player '" + playerTurn(turn, playerOne, playerTwo) + "', enter your move (row [1-3] column [1-3])"); // [rompts user to enter move
      do // beginning of loop
      {
        System.out.println("Enter your row number."); // prompts user to enter row number
        row = In.getInt(); // retrieves user input
        if (row < 1 || row > rows) // checks if row number is valid
        {
          System.out.println("Invalid row number. Re-enter the row number."); // prints error message, prompts user to re-enter number
        }
      } while (row < 1 || row > rows); // loops while number is invalid
      row = row - 1; // reduces row number by one (adjusts for array indices)
      do // beginning of loop
      {    
        System.out.println("Enter your column number"); // prompts user to enter column number
        col = In.getInt(); // retrieves user input
        if (col < 1|| col > columns) // checks if column number is valid
        {
          System.out.println("Invalid column number. Re-enter the column number."); // prints error message, prompts user to re-enter number
        }
      } while (col < 1 || col > columns); // loops while number is invalid
      col = col - 1; // reduces column number by one (adjusts for array indices)
      if ((numBoard [row][col]) != 0) // checks if selected space is available
      {
        System.out.println("This space is already taken. Try another move."); // prints error message, prompts user to re-enter move
      }
      else // does not meet other conditions
      {
        do // beginning of loop
        {
          System.out.println("Which number would you like to place? Enter an " + playerTurn(turn, playerOne, playerTwo) + " number from 1-9 (inclusive)."); // prompts user to enter number to place
          number = In.getInt(); // retrieves input from user
          if ((playerTurn(turn, playerOne, playerTwo)).equalsIgnoreCase("even")) // checks if player must place even numbers
          {
            if (number % 2 == 0 && even.indexOf(String.valueOf(number)) != -1) // checks if number is even and is between 1 and 9 (inclusive)
            {
              if (used.indexOf(number) == -1) // checks if number has already been used
              {
                used.add(number); // adds number to array list
                numBoard [row][col] = number; // sets space to player's number
                turn++; // increase turn number
              }
              else // does not meet other conditions
              {
                System.out.println("This number has already been used! Re-enter your number."); // prints error message, prompts user to re-enter number
              }
            }
            else // does not meet other conditions
            {
              System.out.println("Invalid number. You must place an even number between 1 and 9! Re-enter your number."); // prints error message, prompts user to re-enter number
            }
          }
          else // does not meet other conditions
          {
            if (number % 2 != 0 && odd.indexOf(String.valueOf(number)) != -1) // checks if number is odd and is between 1 and 9 (inclusive)
            {
              if (used.indexOf(number) == -1)
              {
                used.add(number); // adds number to array list
                numBoard [row][col] = number; // sets space to player's number
                turn++; // increase turn number
              }
              else // does not meet other conditions
              {
                System.out.println("This number has already been used! Re-enter your number."); // prints error message, prompts user to re-enter number
              }
            }
            else // does not meet other conditions
            {
              System.out.println("Invalid number. You must place an odd number between 1 and 9 (inclusive)! Re-enter your number."); // prints error message, prompts user to re-enter number
            }
          }
        } while (numBoard[row][col] != number); // loops while space is not set to the player's number
        printBoard(rows, columns, row, col, numBoard); // prints board
      }
    } while (!hasWon(numBoard, row, col, rows, columns) && turn < 9); // loops while no one has won and the board is not full
    
    if (hasWon(numBoard, row, col, rows, columns)) // checks if anyone has won
    {
      System.out.println("Player '" + playerTurn(turn - 1, playerOne, playerTwo) + "' wins!"); // prints winning message
    }
    else // does not meet other conditions
    {
      System.out.println("Tie!"); // prints tie message
    }
  }
}
