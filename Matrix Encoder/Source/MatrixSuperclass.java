import java.util.Scanner;
/**
 * This is a superclass for all the execute functions of the Matrix programs <br>
 * All code by: Zakariya Hashmi
 */

public abstract class MatrixSuperclass
{
    private String name;
    
    public MatrixSuperclass()
    {
        name = "default";
    }
    
    /**
     * the method shared with all other programs
     */
    public void execute() throws java.lang.Exception
    {
        System.out.println("This method does nothing.");
    }
    
    public boolean stopOrNo()
    {
        Scanner nut = new Scanner(System.in);
        System.out.println("Do you want to do another problem? type y or n");
        
        String response = nut.nextLine();
        
        if (response.equals("y"))
        {
            System.out.println("Ok. Let's do this!.");
            System.out.println();
            return false;
        }
        
        else
        {
            System.out.println("Ok. See you later!");
            return true;
        }
    }
}
