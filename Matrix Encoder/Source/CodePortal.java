import java.util.Scanner;
/**
 * The portal for all of my code for Linear Algebra <br>
 * All code by: Zakariya Hashmi
 */
public class CodePortal
{
    /**
     * this is a static class because I need no object to interact with <br>
     * this is just is a portal for all the linear algebra code that I've made< br>
     * just main method and you can see all my stuff<br>
     * the 2 static variables I have are codes, to store all the codes for bonuses and tests (MatrixSuperclass objects), and codeNames, to store the names of all the codes
     */
    
    private static MatrixSuperclass[] codes = {new MatrixEncoder(), new BasesAndCoordinates(), new RankAndSpaces()}; //An array of MatrixSuperclass objects to call each code
    private static String[] codeNames = {"Linear Algebra Bonus One - Matrix Encode/Decode", "Linear Algebra Test 2 - Option 2: Part 1", "Linear Algebra Test 2 - Option 2: Part 2"};
    
    /**
     * main method
     */
    public static void main(String[] args)
    {
        System.out.println("Welcome to my coding projects!");
        try
        {
            doCodes();
        }
        catch (java.lang.Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * displays all of the codes in the codeNames array w/ printCodes() method <br>
     * has you pick a game to play with pickCode() method <br>
     * asks you if you want to play again w/ doAgain() method <br>
     * its really that easy lol
     */
    public static void doCodes() throws java.lang.Exception
    {
        printCodes();
        System.out.println();
        pickCode();
        doAgain();
    }
    
    /**
     * prints all the codes in the codeNames array
     */
    public static void printCodes()
    {
        System.out.println("Here is a list of all of codes you can execute...");
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < codeNames.length; i++)
        {
            System.out.println((i + 1) + ". " + codeNames[i]);
        }
        System.out.println("---------------------------------------------------------------");
    }
    
    /**
     * this method has you enter a number to pick and execute the code that you want
     */
    public static void pickCode() throws java.lang.Exception
    {
        System.out.println("Pick your assignment. Enter a number.");
        Scanner urMom = new Scanner(System.in);
        int resp = Integer.parseInt(urMom.nextLine());
        if (resp > 0 && resp <= codes.length)
        {
            codes[resp - 1].execute();
        }
        
        else
        {
            System.out.println("That's not a number we have. Try again.");
            pickCode();
        }
    }
    
    /**
     * the method that asks if you want to execute a code again <br>
     * executes the doGames() method if you want to
     */
    public static void doAgain() throws java.lang.Exception
    {
        System.out.println("The code is done.\n\nDo you want to try another one of my projects?\ny for yes\nn for no");
        Scanner urMom = new Scanner(System.in);
        String response = urMom.nextLine();
        
        if (response.equals("y"))
        {
            System.out.println("Ok.");
            System.out.println();
            doCodes();
        }
        
        else
        {
            System.out.println("Ok, see ya!");
        }
    }
}
