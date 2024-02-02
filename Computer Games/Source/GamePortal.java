import java.util.Scanner;
/**
 * @author Zakariya Hashmi
 * @version 1.4
 */
public class GamePortal
{
    /**
     * this is a static class because I need no object to interact with<br>
     * this is just is a portal for all of the games that I've made<br>
     * just main method and you can play all of my games<br>
     * the 2 static variables I have are games, to store all the GameParent objects, and gameNames, to store the game names
     */
    
    private static GameParent[] games = {new TicTacToeBoard(), new MadLib(), new EightBall(), new Tunes(), new GuessTheNum()}; //An array of GameParent objects (and child objects) to call each game
    private static String[] gameNames = {"Tic Tac Toe", "MadLibs", "Eight Ball", "Tunes", "Guess The Number"};
    
    /**
     * main method
     */
    public static void main(String[] args)
    {
        System.out.println("Welcome to my games!");
        playGames();
    }
    
    /**
     * displays all of the games in the games array w/ printGames() method<br>
     * has you pick a game to play with pickGame() method<br>
     * asks you if you want to play again w/ playAgain() method<br>
     * its really that easy lol
     */
    public static void playGames()
    {
        printGames();
        System.out.println();
        pickGame();
        playAgain();
    }
    
    /**
     * prints all the games in the gameNames array
     */
    public static void printGames()
    {
        System.out.println("Here is a list of all of the games you can play...");
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < gameNames.length; i++)
        {
            System.out.println((i + 1) + ". " + gameNames[i]);
        }
        System.out.println("---------------------------------------------------------------");
    }
    
    /**
     * this method has you enter a number to pick and execute the game that you want
     */
    public static void pickGame()
    {
        System.out.println("Pick your game. Enter a number.");
        Scanner urMom = new Scanner(System.in);
        int resp = Integer.parseInt(urMom.nextLine());
        if (resp > 0 && resp <= games.length)
        {
            games[resp - 1].execute();
        }
        
        else
        {
            System.out.println("That's not a number we have. Try again.");
            pickGame();
        }
    }
    
    /**
     * the method that asks if you want to play again<br>
     * executes the playGames() method if you want to
     */
    public static void playAgain()
    {
        System.out.println("Do you want to try another game?\ny for yes\nn for no");
        Scanner urMom = new Scanner(System.in);
        String response = urMom.nextLine();
        
        if (response.equals("y"))
        {
            System.out.println("Ok.");
            System.out.println();
            playGames();
        }
        
        else
        {
            System.out.println("Ok, see ya!");
        }
    }
}
