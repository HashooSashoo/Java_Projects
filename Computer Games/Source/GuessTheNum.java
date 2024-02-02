import java.util.Scanner;
/**
 * @author Zakariya Hashmi
 * @version 1.0
 */
public class GuessTheNum extends GameParent
{
    /**
     * a very simple game
     * this basically has you guessing a random number between 1 and 100
     * you have 6 tries
     */
    
    private int randNum;
    private int numOfTurns;
    
    /**
     * constructs the game with a random number and the number of guesses left
     */
    public GuessTheNum()
    {
        randNum = (int)(Math.random()*100 + 1);
        numOfTurns = 6;
    }
    
    /**
     * main method
     */
    public static void main(String[] args)
    {
        GuessTheNum nut = new GuessTheNum();
        nut.execute();
    }
    
    /**
     * generates a new random number to randNum
     */
    public void newRandNum()
    {
        randNum = (int)(Math.random()*100 + 1);
    }
    
    /**
     * resets the number of guesses back to 6
     */
    public void addMoreTurns()
    {
        numOfTurns = 6;
    }
    
    /**
     * resets both the random number and number of guesses
     */
    public void resetNums()
    {
        newRandNum();
        addMoreTurns();
    }
    
    /**
     * takes and returns your guess for the random number
     */
    public int guessNum()
    {
        if (numOfTurns < 6)
        {
            System.out.println("Guess another number.");
        }
        
        else
        {
            System.out.println("Go ahead, pick a number between 1 and 100.");
        }
        
        Scanner urMom = new Scanner(System.in);
        int coocoo = Integer.parseInt(urMom.nextLine());
        return coocoo;
    }
    
    /**
     * checks if you got the number right and reacts accordingly<br>
     * if you got the number, you win and the program uses outputReaction() to congratulate you<br>
     * if you didn't but still have turns left, you get another turn<br>
     * if you don't have any turns left, outputReaction() occurs still
     */
    public void checkIfCorrect(int num)
    {
        if (num == randNum)
        {
            System.out.println("You got the number!");
            System.out.println(outputReaction(true));
        }
        
        else if (numOfTurns != 0)
        {
            incrementOrDecrement(num);
            checkIfCorrect(guessNum());
        }
        
        else
        {
            System.out.println("Welp, you didn't get the number. :(");
            System.out.println("The number was " + randNum + " by the way.");
            System.out.println(outputReaction(false));
        }
    }
    
    /**
     * removes a turn for each wrong guess, and also tells you if the number is higher or lower than your guess
     */
    public void incrementOrDecrement(int num)
    {
        if (num > randNum)
        {
            System.out.println("Incorrect.");
            System.out.println("The number you want is lower.");
            numOfTurns--;
        }
            
        else if (num < randNum)
        {
            System.out.println("Incorrect.");
            System.out.println("The number you want is higher.");
            numOfTurns--;
        }
    }
    
    /**
     * returns a reaction based on what outcome you got in the game<br>
     * basically a different response based on 1. whether you got the number, and 2. how many turns you had left
     */
    public String outputReaction(boolean wonOrNot)
    {
        switch (numOfTurns)
        {
            case 6:
                return "AND ON THE FIRST TRY!!! With 6 turns left! You are a genius!";
            case 5:
                return "Nice! You got it on the second try! You had 5 turns left!";
            case 4:
                return "Not bad! 3rd try! 4 turns left.";
            case 3:
                return "Alright, pretty good. 3 turns left.";
            case 2:
                return "Just in time. You were running out of turns! You had 2 turns left!";
            case 1:
                return "SO CLOSE. Just in the nick of time! Just 1 more try left!";
            case 0:
                if (wonOrNot)
                {
                    return "LAST TRY. And you got it. You thank the stars for your victory.";
                }
                
                else
                {
                    return "You have no more turns. You lost. So sad :(";
                }
                
        }
        return "sussy baka";
    }
    
    /**
     * executes the game
     */
    public void execute()
    {
        System.out.println("Try and guess my number. You have " + numOfTurns + " tries.");
        resetNums();
        checkIfCorrect(guessNum());
        playAgain("That was a fun game!");
    }
}
