import java.util.Scanner;
/**
 * @author Zakariya Hashmi
 * @version 1.0
 */
public class EightBall extends GameParent
{
    /**
     * this game is an eight ball, it gives a random response to any question you ask it<br>
     * the only instance variable is a set of prewritten responses.<br>
     * due to this, there is no need for a constructor. just methods.
     */
    private String[] responses = {"Yes.", "Of course. Obviously.", "This is true. Anyone that says otherwise is a dumbdumb.", "Yep.", "Maybe, but probably yes.",
                                 "Ima go with yes. Feeling good about this.", "Nah.", "No. Why would you think that.", "Of course not.", "Nope. Not possible.", "If thats true, Im a piece of turkey.",
                                 "Maybe, but probably no.", "Error. Could Not Decipher.", "No. Im not answering that.", "Dont feel like it now. Ask another question or talk tomorrow.",
                                 "I know this is very unrelated to your question, but I can OutPizza the Hut."};
    
    /**
     * local main method
     */
    public static void main(String[] args)
    {
        EightBall nut = new EightBall();
        nut.execute();
    }
    
    /**
     * the scanner method to ask the question from the user
     */
    public void scanner()
    {
        Scanner urMom = new Scanner(System.in);
        System.out.println("Ask me your question.");
        String question = urMom.nextLine();
    }
    
    /**
     * prints a random response from the responses array right after the scanner() prompt
     */
    public void pickResponse()
    {
        System.out.println("Analyzing...");
        System.out.println();
        System.out.println(responses[(int)(Math.random()*responses.length)]);
        System.out.println();
    }
    
    /**
     * the whole eight ball game
     */
    public void execute()
    {
        System.out.println("I will answer all of your questions, habibi.");
        scanner();
        pickResponse();
        playAgain("Habibi, your question is answered.");
    }
}
