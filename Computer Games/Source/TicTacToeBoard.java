import java.util.Arrays;
import java.util.Scanner;
/**
 * @author Zakariya Hashmi
 * @version 1.6
 */
public class TicTacToeBoard extends GameParent
{
    /**
     * The instance variables make a 2D array ticTacToe board
     * It also makes a 2D array to store all the coordinates filled
     * To put all coordinates in order, an index checker called currentIndexCU is on there for coordsUsed
     * Finally, to fix a bug that didn't allow coord 0,0 to be used, I made a ZeroZero boolean.
     */
    private String[][] ticTacToe;
    private int[][] coordsUsed;
    private int currentIndexCU;
    private boolean coordZeroZeroUsed;
    private boolean hardModeOn;
    private boolean comGoesFirst;
    
    /**
     * Initialized all variables with sizes and values<br>
     * gives the ticTacToe array "blank" elements
     */
    public TicTacToeBoard()
    {
        ticTacToe = new String[3][3]; //dimensions of ticTacToe board
        coordsUsed = new int[9][2]; //spaces to add coords of used elements
        currentIndexCU = 0; //index tracked of next empty array space in coordsUsed (to add new coords to the array)
        coordZeroZeroUsed = false; //tracks if coords 0,0 is used yet (just to fix a bug)
        hardModeOn = false; //used to activate hard mode (basically the computer changes moves depending on board instead of randomly)
        comGoesFirst = false; //the boolean that decides who goes first in the tic tac toe
        for (int i = 0; i < ticTacToe.length; i++)
        {
            for (int j = 0; j < ticTacToe[i].length; j++)
            {
                ticTacToe[i][j] = "___";
            }
        }
    }
    
    /**
     * local main method
     */
    public static void main(String[] args)
    {
        TicTacToeBoard board = new TicTacToeBoard();
        board.execute();
    }
    
    /**
     * getter method for ticTacToe board<br>
     * @returns ticTacToe instance variable
     */
    public String[][] getBoard()
    {
        return ticTacToe;
    }
    
    /**
     * getter method for coordsUsed array<br>
     * @returns coordsUsed array
     */
    public int[][] getCoordsUsed()
    {
        return coordsUsed;
    }
    
    /**
     * prints the board on the terminal to see progress
     */
    public void printBoard()
    {
        for (String[] row : ticTacToe)
        {
            for (String element : row)
            {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    /**
     * makes the board blank by filling it with blank elements<br>
     * clears coordsUsed array and changes currentIndexCU to 0<br>
     * also makes coordZeroZeroUsed false to be able to go to 0,0
     */
    public void clearBoard()
    {
        for (int i = 0; i < ticTacToe.length; i++)
        {
            for (int j = 0; j < ticTacToe[i].length; j++)
            {
                ticTacToe[i][j] = "___";
            }
        }
        
        coordsUsed = new int[20][2];
        currentIndexCU = 0;
        coordZeroZeroUsed = false;
    }
    
    /**
     * first, specifically checks if coords 0,0 is used with checkIfZero() method (cus i didn't know how to fix the bug lol)<br>
     * then, checks if the given coordinate on the board is empty or not<br>
     * precondition: coords are more than or equal to zero and less than 4
     */
    public boolean coordEmpty(int firstCoord, int secondCoord)
    {
        if (checkZeroZero(firstCoord, secondCoord))
        {
            return true;
        }
        
        int[] arrayToCheck = {firstCoord, secondCoord};
        for (int i = 0; i < coordsUsed.length; i++)
        {
            if (Arrays.equals(arrayToCheck, coordsUsed[i]))
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * a method specifically for checking if coords 0,0 has been used yet
     */
    public boolean checkZeroZero(int firstCoord, int secondCoord)
    {
        if (firstCoord == secondCoord && firstCoord == 0)
        {
            if (coordZeroZeroUsed == false)
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * checks if the given coordinates are in bounds
     */
    public boolean coordInBounds(int firstCoord, int secondCoord)
    {
        boolean firstTrue = false;
        boolean secondTrue = false;
        firstTrue = (firstCoord < 3 && firstCoord >= 0);
        secondTrue = (secondCoord < 3 && secondCoord >= 0);
        
        if (firstTrue && secondTrue)
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * uses both coordsInBounds and coordsEmpty methods to check if coords are ok to put on board
     */
    public boolean coordsValid(int firstCoord, int secondCoord)
    {
        if (coordInBounds(firstCoord, secondCoord) && coordEmpty(firstCoord, secondCoord))
        {
            return true;
        }
        return false;
    }
    
    /**
     * changes an element in the board with a parameter<br>
     * adds the new coords of changed element to coordsUsed<br>
     * Increments the currentIndex number by 1<br>
     * finally, checks coord element and puts in respective array using cheeckAndSaveElement method<br>
     * precondition: String xOrO is either an _X_ or an _O_
     */
    public void changeBoard(int firstCoord, int secondCoord, String xOrO)
    {
        if (coordsValid(firstCoord, secondCoord))
        {
            if (firstCoord == secondCoord && secondCoord == 0)
            {
                coordZeroZeroUsed = true;
            }
            ticTacToe[firstCoord][secondCoord] = xOrO;
        
            coordsUsed[currentIndexCU][0] = firstCoord;
            coordsUsed[currentIndexCU][1] = secondCoord;
            
            currentIndexCU++;
        }
        
        else
        {
            System.out.println("Not valid, coords " + firstCoord + " and " + secondCoord + " is already taken or out of bounds.");
        }
    }
    
    /**
     * gets the first coordinate from human input in the Scanner class
     */
    public int getHumanFirstCoords(int[] coords)
    {
        return coords[0];
    }
    
    /**
     * gets the second coordinate from human input in the Scanner class
     */
    public int getHumanSecondCoords(int[] coords)
    {
        return coords[1];
    }
    
    /**
     * asks the player to put a coord from the num pad in terminal<br>
     * first, checks the string to see if there is anything wrong with it<br>
     * then, turns it into a number that is sent to the enterHumanCoords() method<br>
     * if the conditional is missed somehow, 99 is returned (this will cause the exception in enterHumanCoords() to activate)
     */
    public int validNumPadCoord()
    {
        Scanner urMom = new Scanner(System.in);
        System.out.println("Enter your position.");
        String wordResp = urMom.nextLine();
        if (!wordResp.equals(""))
        {
            return Integer.parseInt(wordResp);
        }
        
        else
        {
            System.out.println("Not valid. Try again.");
            validNumPadCoord();
        }
        return 99;
    }
    
    /**
     * turns the number entered in the keypad (due to its grid shape) to become coordinates on the board<br>
     * checks if number is ok with validNumPadCoord() method, then puts respective coordinates in an array and returns it
     */
    public int[] enterHumanCoords()
    {
        int[] newArr = new int[2];
        int num = validNumPadCoord();
        if (num < 4)
        {
            newArr[0] = 2;
            newArr[1] = num - 1;
        }
        
        else if (num < 7)
        {
            newArr[0] = 1;
            newArr[1] = num - 4;
        }
        
        else if (num < 10)
        {
            newArr[0] = 0;
            newArr[1] = num - 7;
        }
        
        else
        {
            System.out.println("that is not allowed. enter a normal one :(");
            enterHumanCoords();
        }
        return newArr;
    }
    
    /**
     * gives a random number between 0 and 2 for computer coords (since the 1st and 2nd coords have same domain, we don't need a second method)
     */
    public int getComputerCoords()
    {
        return (int)(Math.random()*3);
    }
    
    /**
     * outputs the winner of the game, or nothing if no winner yet
     */
    public void announceWinner()
    {
        if (getWinningCondition().equals("_X_"))
        {
            System.out.println("You are the winner!");
        }
        
        else if (getWinningCondition().equals("_O_"))
        {
            System.out.println("Computer is winner :(");
        }
        
        else if (checkIfTie())
        {
            System.out.println("It's a tie. Cool I guess :/");
        }
    }
    
    /**
     * checks the board to see if there is a tie game<br>
     * looks to see if there are no empty elements, if thats the case (& there is no winner), then it is a tie
     */
    public boolean checkIfTie()
    {
        boolean isTie = true;
        for (String[] row : ticTacToe)
        {
            for (String element : row)
            {
                if (element.equals("___"))
                {
                    isTie = false;
                    break;
                }
            }
        }
        return isTie;
    }
    
    /**
     * checks to see if there is a winning condition for a specified element type<br>
     * precondition: type is _O_ or _X_
     */
    public String getWinningCondition()
    {
        if (isVertical())
        {
            return verticalIndex();
        }
        
        else if (isHorizontal())
        {
            return horizontalIndex();
        }
        
        else if (isDiagonal())
        {
            return diagonalIndex();
        }
        
        else
        {
            return "___";
        }
    }
    
    /**
     * return a boolean to see if someone has won yet
     */
    public boolean hasWon()
    {
        if (checkIfTie())
        {
            return true;
        }
        
        else if (getWinningCondition().equals("___"))
        {
            return false;
        }
        
        else
        {
            return true;
        }
    }
    
    /**
     * Checks if there is a vertical win condition present on the board<br>
     * analyzes the ticTacToe board
     */
    public boolean isVertical()
    {
        for (int i = 0; i < ticTacToe.length; i++)
        {
            if (ticTacToe[0][i].equals(ticTacToe[1][i]) && ticTacToe[1][i].equals(ticTacToe[2][i]))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * like the isVertical method, but returns the element that satisfied the win condition instead
     */
    public String verticalIndex()
    {
        for (int i = 0; i < ticTacToe.length; i++)
        {
            if (ticTacToe[0][i].equals(ticTacToe[1][i]) && ticTacToe[1][i].equals(ticTacToe[2][i]))
            {
                return ticTacToe[0][i];
            }
        }
        return "";
    }
    
    /**
     * Checks if there is a horizontal win condition present on the board
     * analyzes the ticTacToe board
     */
    public boolean isHorizontal()
    {
        for (int i = 0; i < ticTacToe.length; i++)
        {
            if (ticTacToe[i][0].equals(ticTacToe[i][1]) && ticTacToe[i][1].equals(ticTacToe[i][2]))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * like the isHorizontal method, but returns the element that satisfied the win condition instead
     */
    public String horizontalIndex()
    {
        for (int i = 0; i < ticTacToe.length; i++)
        {
            if (ticTacToe[i][0].equals(ticTacToe[i][1]) && ticTacToe[i][1].equals(ticTacToe[i][2]))
            {
                return ticTacToe[i][0];
            }
        }
        return "";
    }
    
    /**
     * Checks if there is a diagonal win condition present on the board<br>
     * analyzes the ticTacToe board
     */
    public boolean isDiagonal()
    {
        if ((ticTacToe[0][0].equals(ticTacToe[1][1]) && ticTacToe[1][1].equals(ticTacToe[2][2])) || (ticTacToe[0][2].equals(ticTacToe[1][1]) && ticTacToe[1][1].equals(ticTacToe[2][0])))
        {
            return true;
        }
        return false;
    }
    
    /**
     * like the isDiagonal method, but returns the element that satisfied the win condition instead
     */
    public String diagonalIndex()
    {
        if ((ticTacToe[0][0].equals(ticTacToe[1][1]) && ticTacToe[1][1].equals(ticTacToe[2][2])) || (ticTacToe[0][2].equals(ticTacToe[1][1]) && ticTacToe[1][1].equals(ticTacToe[2][0])))
        {
            return ticTacToe[1][1];
        }
        return "";
    }
    
    /**
     * Human puts coordinates and the method checks if they are ok, if they are ok, then the element is put on the board.
     */
    public void humanTurn()
    {
        System.out.println("Put down your coordinates.");
        int[] coordArray = enterHumanCoords();
        int firstHumanCoord = getHumanFirstCoords(coordArray);
        int secondHumanCoord = getHumanSecondCoords(coordArray);
        if (coordsValid(firstHumanCoord, secondHumanCoord))
        {
            System.out.println("You put your X at coordinates " + firstHumanCoord + " and " + secondHumanCoord + ".");
            changeBoard(firstHumanCoord, secondHumanCoord, "_X_");
            printBoard();
            System.out.println();
        }
        
        else
        {
            System.out.println("Not valid coords. Put different ones.");
            humanTurn();
        }
    }
    
    /**
     * computer generates coords and they are put on the board
     * if on hard mode, calls AIMoves() method to make a calculated move instead of a randomized one
     * if not, makes a randomized move
     * if not, and all spaces are used up or otherwise, do nothing
     */
    public void computerTurn()
    {
        int firstComputerCoord = getComputerCoords();
        int secondComputerCoord = getComputerCoords();
        
        System.out.println("Computer's turn now.");
        System.out.println();
        if (hardModeOn && moveAIPossible())
        {
            AIMoves();
        }
        
        else if (coordsValid(firstComputerCoord, secondComputerCoord))
        {
            System.out.println("Computer put its O at coordinates " + firstComputerCoord + " and " + secondComputerCoord + ".");
            changeBoard(firstComputerCoord, secondComputerCoord, "_O_");
            printBoard();
            System.out.println();
        }
        
        else if (checkIfTie())
        {
            System.out.println();
            System.out.println();
        }
        
        else
        {
            computerTurn();
        }
        
    }
    
    /**
     * analyzes the board checking if any conditions from various methods are true<br>
     * part of AI methods
     */
    public boolean moveAIPossible()
    {
        int[] baseCase = {6,9};
        if (!(Arrays.equals(checkAllVerti(), baseCase)))
        {
            return true;
        }
        
        else if (!(Arrays.equals(checkAllHoriz(), baseCase)))
        {
            return true;
        }
        
        else if (!(Arrays.equals(checkAllDiag(), baseCase)))
        {
            return true;
        }
        
        else
        {
            return false;
        }
    }
    
    /**
     * checks if situation warrants the computer putting a blocking O, then puts the coordinates on the board with the putCoordinates() method
     * part of AI methods
     */
    public void AIMoves()
    {
        int[] baseCase = {6,9};
        if (!Arrays.equals(checkAllVerti(), baseCase))
        {
            putCoordinates(checkAllVerti());
        }
        
        else if (!Arrays.equals(checkAllHoriz(), baseCase))
        {
            putCoordinates(checkAllHoriz());
        }
        
        else if (!Arrays.equals(checkAllDiag(), baseCase))
        {
            putCoordinates(checkAllDiag());
        }
    }
    
    /**
     * a method that puts coordinates on a board with an int[] array parameter
     * differs from changeBoard() because of int[] parameter
     * part of AI methods
     */
    public void putCoordinates(int[] coords)
    {
        int firstCoord = 0;
        int secondCoord = 0;
        int[] baseCase = {6,9};
        if (!Arrays.equals(coords, baseCase))
        {
            firstCoord = coords[0];
            secondCoord = coords[1];
            
            System.out.println("Computer put its O at " + firstCoord + " and " + secondCoord);
            changeBoard(firstCoord, secondCoord, "_O_");
            printBoard();
            System.out.println();
        }
    }
    
    /**
     * this method checks if only a middle square was put down by human
     * NOT USED CURRENTLY
     */
    public boolean checkIfPutMid()
    {
        return true;
    }
    
    /**
     * checks if there is a vertical condition for every column using checkVertiTactic() method<br>
     * returns the coordinates of the element if there need be one. Returns base case if n/a
     */
    public int[] checkAllVerti()
    {
        int[] baseCase = {6,9};
        for (int i = 0; i < ticTacToe.length; i++)
        {
            if (!Arrays.equals(checkVertiTactic(i), baseCase))
            {
                return checkVertiTactic(i);
            }
        }
        return baseCase;
    }
    
    /**
     * checks the specified board column to see if an element can be placed there to block<br>
     * checks conditions for each scenario and changes coordinates for the situation<br>
     * returns coordinates in int[] array, if no scenarios apply then the default coordinates {6, 9} are returned
     */
    public int[] checkVertiTactic(int index)
    {
        int[] coordArray = {6, 9};
        coordArray[1] = index;
        if ((!ticTacToe[1][index].equals("___")) && ticTacToe[1][index].equals(ticTacToe[2][index]) && ticTacToe[0][index].equals("___"))
        {
            coordArray[0] = 0;
        }
        
        else if ((!ticTacToe[0][index].equals("___")) && ticTacToe[0][index].equals(ticTacToe[2][index]) && ticTacToe[1][index].equals("___"))
        {
            coordArray[0] = 1;
        }
        
        else if ((!ticTacToe[0][index].equals("___")) && ticTacToe[0][index].equals(ticTacToe[1][index]) && ticTacToe[2][index].equals("___"))
        {
            coordArray[0] = 2;
        }
        
        else
        {
            coordArray[1] = 9;
        }
        return coordArray;
    }
    
    /**
     * unlike the other AI methods, this one checks all the diagonal conditions at once since there aren't alot (5 instead of 9)<br>
     * it returns the coordinates for the diagonal element, but if not applicable, returns the base case
     */
    public int[] checkAllDiag()
    {
        int[] coordsReturned = {6,9};
        
        if ((!ticTacToe[0][0].equals("___")) && ticTacToe[0][0].equals(ticTacToe[1][1]) && ticTacToe[2][2].equals("___"))
        {
            coordsReturned[0] = 2;
            coordsReturned[1] = 2;
        }
        
        else if ((((!ticTacToe[0][0].equals("___")) && ticTacToe[0][0].equals(ticTacToe[2][2])) || ((!ticTacToe[0][2].equals("___")) && ticTacToe[0][2].equals(ticTacToe[2][0])) && ticTacToe[1][1].equals("___")))
        {
            coordsReturned[0] = 1;
            coordsReturned[1] = 1;
        }
        
        else if ((!ticTacToe[2][2].equals("___")) && ticTacToe[2][2].equals(ticTacToe[1][1]) && ticTacToe[0][0].equals("___"))
        {
            coordsReturned[0] = 0;
            coordsReturned[1] = 0;
        }
        
        else if ((!ticTacToe[0][2].equals("___")) && ticTacToe[0][2].equals(ticTacToe[1][1]) && ticTacToe[2][0].equals("___"))
        {
            coordsReturned[0] = 2;
            coordsReturned[1] = 0;
        }
        
        else if ((!ticTacToe[2][0].equals("___")) && ticTacToe[2][0].equals(ticTacToe[1][1]) && ticTacToe[0][2].equals("___"))
        {
            coordsReturned[0] = 0;
            coordsReturned[1] = 2;
        }
        return coordsReturned;
    }
    
    /**
     * checks if there is a horizontal condition for every row using checkHorizTactic() method<br>
     * returns the coordinates of the element if there need be one. Returns base case if n/a
     */
    public int[] checkAllHoriz()
    {
        int[] baseCase = {6,9};
        for (int i = 0; i < ticTacToe.length; i++)
        {
            if (!Arrays.equals(checkHorizTactic(i), baseCase))
            {
                return checkHorizTactic(i);
            }
        }
        return baseCase;
    }
    
    /**
     * checks the specified board row to see if an element can be placed there to block<br>
     * checks conditions for each scenario and changes coordinates for the situation<br>
     * returns coordinates in int[] array, if no scenarios apply then the default coordinates {6, 9} are returned
     */
    public int[] checkHorizTactic(int index)
    {
        int[] coordArray = {6, 9};
        coordArray[0] = index;
        if ((!ticTacToe[index][1].equals("___")) && ticTacToe[index][1].equals(ticTacToe[index][2]) && ticTacToe[index][0].equals("___"))
        {
            coordArray[1] = 0;
        }
        
        else if ((!ticTacToe[index][0].equals("___")) && ticTacToe[index][0].equals(ticTacToe[index][2]) && ticTacToe[index][1].equals("___"))
        {
            coordArray[1] = 1;
        }
        
        else if ((!ticTacToe[index][0].equals("___")) && ticTacToe[index][0].equals(ticTacToe[index][1]) && ticTacToe[index][2].equals("___"))
        {
            coordArray[1] = 2;
        }
        
        else
        {
            coordArray[0] = 6;
        }
        return coordArray;
    }
    
    /**
     * 
     */
    public void playersBattle()
    {
        if (comGoesFirst)
        {
            System.out.println("The computer will go first this round.");
            System.out.println();
            while (hasWon() == false)
            {
                computerTurn();
                if (hasWon())
                {
                    break;
                }
                humanTurn();
                if (checkIfTie())
                {
                    break;
                }
            }
        }
        
        else
        {
            System.out.println("You will go first this round.");
            System.out.println();
            while (hasWon() == false)
            {
                humanTurn();
                if (hasWon())
                {
                    break;
                }
                computerTurn();
            }
        }
        comGoesFirst = !comGoesFirst;
    }
    
    /**
     * asks if the player wants to play again. if yes, clears board and calls execute() method
     */
    public void playAgain()
    {
        System.out.println("Do you want to play again?\ny for yes (easy mode)\nn for no\nh for hard mode");
        Scanner urMom = new Scanner(System.in);
        String response = urMom.nextLine();
        
        if (response.equals("y"))
        {
            System.out.println("Ok.");
            System.out.println();
            clearBoard();
            hardModeOn = false;
            execute();
        }
        
        else if (response.equals("h"))
        {
            System.out.println("Ok.");
            System.out.println();
            clearBoard();
            hardModeOn = true;
            execute();
        }
        
        else
        {
            System.out.println("Ok, see ya!");
            System.out.println();
        }
    }
    
    /**
     * the whole tic tac toe game
     */
    public void execute()
    {
        System.out.println("Let's play Tic Tac Toe!\nUse the num pad to put X on");
        printBoard();
        playersBattle();
        announceWinner();
        playAgain();
    }
}
