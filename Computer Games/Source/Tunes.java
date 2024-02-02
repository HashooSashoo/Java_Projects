import java.util.Scanner;
import javax.sound.sampled.LineUnavailableException;
/**
 * @author Zakariya Hashmi
 * @version 1.4
 */
public class Tunes extends GameParent
{
    /**
     * this game selects a tune and plays it with the help fromthe makeTheSound() method in GameParent<br>
     * tuneCollec stores the string titles of all the tunes to display and help the person select it
     * there is no need for a constructor cus the tunes are preset
     * it also gets an ascii image from 
     */
    private String[] tuneCollec = {"Gigachad", "Amogus Sus", "Little Apple", "Breaking Bord", "Hollow Knight"};
    
    /**
     * local main method
     */
    public static void main(String[] args)
    {
        Tunes nut = new Tunes();
        nut.execute();
    }
    
    /**
     * a method to list all of the tunes in the class<br>
     * it gives the number to be put in the playTune() method to play the tune using the Scanner
     */
    public int displayTunes()
    {
        System.out.println();
        for (int i = 0; i < tuneCollec.length; i++)
        {
            System.out.println((i + 1) + ". " + tuneCollec[i]);
        }
        System.out.println();
        
        System.out.println("Which one do you want?");
        Scanner urMom = new Scanner(System.in);
        int resp = Integer.parseInt(urMom.nextLine());
        return resp;
    }
    
    /**
     * picks a tune method in the class using the index specified
     */
    public void playTune(int newTune) throws LineUnavailableException
    {
        switch (newTune)
        {
            case 1:
                gigachadTheme(450);
                break;
            case 2:
                amogusSusTheme(600);
                break;
            case 3:
                xiaoBingGuo(450);
                break;
            case 4:
                breakingBad(700);
                break;
            case 5:
                hollowKnight(800);
                break;
            default:
                System.out.println("We don't recognize that. Give another number.");
                playTune(displayTunes());
        }
    }
    
    /**
     * tune method number 1<br>
     * real chads play this
     */
    public void gigachadTheme(int duration) throws LineUnavailableException
    {
        ASCII.printGigachad();
        makeTheSound(494, duration);
        for (int i = 0; i < 1; i++)
        {
            for (int k = 0; k < 3; k++)
            {
                makeTheSound(494, duration);
                makeTheSound(494, duration);
                makeTheSound(494, duration*2/3);
                makeTheSound(587, duration*2/3);
                makeTheSound(523, duration*3/5);
            }
            makeTheSound(494, duration*2/5);
            makeTheSound(494, duration*2/5);
            makeTheSound(330, duration*2/5);
            makeTheSound(494, duration*2/5);
            makeTheSound(330, duration*1/5);
            makeTheSound(330, duration*1/5);
            makeTheSound(330, duration*2/5);
            makeTheSound(494, duration);
        }
    }
    
    /**
     * makes the among drip theme
     */
    public void amogusSusTheme(int duration) throws LineUnavailableException
    {
        ASCII.printAmogus();
        amogusDripRepeat(duration);
        makeTheSound(523, duration/2);
        makeTheSound(196, duration/2);
        makeTheSound(196, duration/2);
        makeTheSound(466, duration/4);
        makeTheSound(587, duration/4);
        makeTheSound(523, duration/2);
        makeTheSound(196, duration/2);
        makeTheSound(196, duration/2);
        amogusDripRepeat(duration);
        makeTheSound(740, duration/2);
        makeTheSound(185, duration/2);
        makeTheSound(185, duration/2);
        makeTheSound(185, duration/2);
        makeTheSound(740, duration/3);
        makeTheSound(698, duration/3);
        makeTheSound(622, duration/3);
        makeTheSound(740, duration/3);
        makeTheSound(698, duration/3);
        makeTheSound(622, duration/3);
        makeTheSound(523, duration);
    }
    
    /**
     * the repeat of the among drip theme<br>
     * used in amogusSusTheme() method
     */
    public void amogusDripRepeat(int duration) throws LineUnavailableException
    {
        makeTheSound(196, duration/2);
        makeTheSound(262, duration);
        makeTheSound(523, duration/2);
        makeTheSound(622, duration/2);
        makeTheSound(698, duration/2);
        makeTheSound(740, duration/2);
        makeTheSound(698, duration/2);
        makeTheSound(622, duration/2);
    }
    
    /**
     * XUEEEEEE HUA PIAAOOO PIAOOO<br>
     * BEI FUNG XIAOOO XIAOOOOOOOOOO<br>
     * TIAN DIIIIII YII PIAANNNN CAANG MAAAAAANG<br><br>
     * 
     * jk this actually plays "Little Apple (Xiao Pingguo Er)"
     */
    public void xiaoBingGuo(int duration) throws LineUnavailableException
    {
        ASCII.printApple();
        makeTheSound(587, duration);
        makeTheSound(466, duration);
        makeTheSound(523, duration);
        makeTheSound(392, duration);
        makeTheSound(587, duration/2);
        makeTheSound(523, duration/2);
        makeTheSound(466, duration/2);
        makeTheSound(523, duration/2);
        makeTheSound(392, duration*2);
        makeTheSound(587, duration);
        makeTheSound(466, duration);
        makeTheSound(523, duration);
        makeTheSound(523, duration);
        makeTheSound(698, duration/2);
        makeTheSound(587, duration/2);
        makeTheSound(440, duration);
        makeTheSound(466, duration);
        makeTheSound(466, duration/2);
        makeTheSound(440, duration/2);
        makeTheSound(392, duration);
        makeTheSound(440, duration/2);
        makeTheSound(466, duration/2);
        makeTheSound(523, duration);
        makeTheSound(349, duration);
        makeTheSound(784, duration/2);
        makeTheSound(698, duration/2);
        makeTheSound(587, duration);
        makeTheSound(587, duration*3/2);
        makeTheSound(523, duration/2);
        makeTheSound(466, duration);
        makeTheSound(523, duration/2);
        makeTheSound(587, duration/2);
        makeTheSound(523, duration/2);
        makeTheSound(587, duration/2);
        makeTheSound(523, duration/2);
        makeTheSound(698, duration*3/2);
        makeTheSound(698, duration/2);
        makeTheSound(698, duration/2);
        makeTheSound(698, duration/2);
        makeTheSound(698, duration/2);
        makeTheSound(698, duration);
    }
    
    /**
     * plays the breaking bad theme
     */
    public void breakingBad(int duration) throws LineUnavailableException
    {
        ASCII.printWallart();
        makeTheSound(220, duration/4);
        makeTheSound(294, duration*3/2);
        makeTheSound(294, duration/4);
        makeTheSound(294, duration/4);
        makeTheSound(330, duration/4);
        makeTheSound(349, duration*5/4);
        makeTheSound(330, duration/4);
        makeTheSound(311, duration/4);
        makeTheSound(294, duration);
        makeTheSound(294, duration/2);
        makeTheSound(330, duration*2);
        makeTheSound(220, duration/4);
        makeTheSound(220, duration/4);
        makeTheSound(262, duration);
        makeTheSound(262, duration/4);
        makeTheSound(294, duration/4);
        makeTheSound(294, duration*2);
    }
    
    public void hollowKnight(int duration) throws LineUnavailableException
    {
        ASCII.printTheKnight();
        hollowKnightRepeat(duration);
        makeTheSound(587, duration/2);
        makeTheSound(349, duration/2);
        makeTheSound(392, duration*2);
        makeTheSound(523, duration/2);
        makeTheSound(392, duration/2);
        makeTheSound(415, duration);
        makeTheSound(392, duration/2);
        makeTheSound(349, duration/2);
        makeTheSound(392, duration/2);
        makeTheSound(294, duration/2);
        makeTheSound(311, duration/2);
        makeTheSound(294, duration/2);
        makeTheSound(233, duration);
        hollowKnightRepeat(duration);
        makeTheSound(698, duration/2);
        makeTheSound(392, duration/2);
        makeTheSound(349, duration*2);
        makeTheSound(784, duration/2);
        makeTheSound(622, duration/2);
        makeTheSound(587, duration/2);
        makeTheSound(523, duration/2);
        makeTheSound(466, duration);
        makeTheSound(523, duration/2);
        makeTheSound(233, duration/2);
        makeTheSound(262, duration/2);
        makeTheSound(349, duration/2);
        makeTheSound(262, duration/2);
        makeTheSound(233, duration/2);
        makeTheSound(220, duration/4);
        makeTheSound(349, duration*11/4);
    }
    
    public void hollowKnightRepeat(int duration) throws LineUnavailableException
    {
        makeTheSound(523, duration/2);
        makeTheSound(392, duration/2);
        makeTheSound(523, duration);
        makeTheSound(587, duration/2);
        makeTheSound(622, duration/2);
    }
    
    public void clashRoyaleStart()
    {
        
    }
    
    /**
     * the whole tune game<br>
     * it has try/catch because i cant put throws LineUnavailableException in the method (cus it overrides the GameParent method)
     */
    public void execute()
    {
        System.out.println("Welcome! Here are the tunes that you can try...");
        try
        {
            playTune(displayTunes());
        }
        catch (javax.sound.sampled.LineUnavailableException lue)
        {
            lue.printStackTrace();
        }
        playAgain("That sounded cool.");
    }
}
