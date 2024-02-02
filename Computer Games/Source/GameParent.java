import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.util.Scanner;
/**
 * @author Zakariya Hashmi
 * @version 1.1
 */
public abstract class GameParent
{
    /**
     * this class will contain methods that most/all of my games will use
     */
    
    private String name;
    
    /**
     * just a default constructor, wont use at all lol
     */
    public GameParent()
    {
        name = "default"; //just an instance variable I added for the heck of it
    }

    
    /**
     * this method makes a sine wave sound<br>
     * you must specify both the frequency (in hertz) and the duration (in milliseconds)<br>
     * i got this from stackoverflow, so I don't know how the hell it works lol
     */
    public static void makeTheSound(int newFreq, int duration) throws LineUnavailableException
    {
        byte[] buf = new byte[2];
        int frequency = 44100; //44100 sample points per 1 second
        AudioFormat af = new AudioFormat((float) frequency, 16, 1, true, false);
        SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
        sdl.open();
        sdl.start();
        int durationMs = duration;
        int numberOfTimesFullSinFuncPerSec = newFreq; //the hertz of the tone
        for (int i = 0; i < durationMs * (float) 44100 / 1000; i++) //1000 ms in 1 second
        {
          float numberOfSamplesToRepresentFullSin= (float) frequency / numberOfTimesFullSinFuncPerSec;
          double angle = i / (numberOfSamplesToRepresentFullSin/ 2.0) * Math.PI;  // /divide with 2 since sin goes 0PI to 2PI
          short a = (short) (Math.sin(angle) * 32767);  //32767 - max value for sample to take (-32767 to 32767)
          buf[0] = (byte) (a & 0xFF); //write 8bits ________WWWWWWWW out of 16
          buf[1] = (byte) (a >> 8); //write 8bits WWWWWWWW________ out of 16
          sdl.write(buf, 0, 2);
        }
        sdl.drain();
        sdl.stop();
    }
    
    /**
     * asks if the player wants to play again. if yes, clears board and calls execute() method<br>
     * the default playAgain method, any modifications are made in the respective game
     */
    public void playAgain(String beforeAsking)
    {
        
        System.out.println(beforeAsking + " Do you want to play again?\ny for yes\nn for no");
        Scanner urMom = new Scanner(System.in);
        String response = urMom.nextLine();
        
        if (response.equals("y"))
        {
            System.out.println("Ok.");
            System.out.println();
            execute();
        }
        
        else
        {
            System.out.println("Ok, see ya!");
            System.out.println();
        }
    }
    
    /**
     * the parent method for all games that will execute<br>
     * this is so I can put all the game objects in an array
     */
    public void execute()
    {
       System.out.println("this is the default execute method");
    }
}
