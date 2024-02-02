import java.util.Scanner;
/**
 * Linear Algebra Test 2 - Option 2 : Part 1 <br>
 * All code by: Zakariya Hashmi
 */
public class BasesAndCoordinates extends MatrixSuperclass
{
    private int dimension; /** Stores the dimension of the vector space (to properly enter the basis sets) **/
    private double[][] firstBases; /** Stores the first set of specified bases from the user **/
    private double[][] secondBases; /** Stores the second set of specified bases from the user **/
    private double[][] transFrom1To2; /** Stores the transition matrix from bases set 1 to bases set 2 **/
    private double[][] transFrom2To1; /** Stores the transition matrix from bases set 2 to bases set 1 **/
    private double[][] specifiedVector; /** Stores a specified vector from the user **/
    
    /**
     * accepts a response from the user to enter the dimension of the vector space
     */
    public String giveDimension()
    {
        System.out.println("Give me the dimension of the vectors (a number between 1 and 10 (inclusive))");
        Scanner nut = new Scanner(System.in);
        String response = nut.nextLine();
        return response;
    }
    
    /**
     * checks to see if the user's response from giveDimension() is an actual number <br>
     * makes the user enter number again if the response is not a number
     */
    public int verifyDimension(String p_dimension)
    {
        int numSize = 0;
        try
        {
            numSize = Integer.parseInt(p_dimension);
        }
        
        catch (Exception e)
        {
            System.out.println("That's not a valid number. Try again.");
            numSize = verifyDimension(giveDimension());
        }
        
        return numSize;
    }
    
    /**
     * checks if the response (confirmed to be a number) is in between 1 and 10 <br>
     * if not in between 1 and 10, the user is forces to enter another number
     */
    public int confirmRangeOfDimension(int p_dimension)
    {
        if (p_dimension <= 10 && p_dimension >= 1)
        {
            return p_dimension;
        }
        
        else
        {
            System.out.println("That's not in the range. Pick another number.");
            confirmRangeOfDimension(verifyDimension(giveDimension()));
        }
        return 0;
    }
    
    /**
     * this method assigns a user-specified dimension to the dimension variable <br>
     * it asks the user to enter a dimension, and checks if it's allowed to be a dimension
     */
    public void assignDimension()
    {
        dimension = confirmRangeOfDimension(verifyDimension(giveDimension()));
    }
    
    /**
     * this method creates two sets of bases vectors that the user enters <br>
     * the user puts elements of the vectors in, which are then checked for linear independence <br>
     * the linear independence check happens in the checkLinearIndependence() method <br>
     * after that, the bases sets are assigned to their respective variables
     */
    public void getVectors()
    {
        System.out.println("Now you need to define your vectors.");
        firstBases = new double[dimension][dimension];
        secondBases = new double[dimension][dimension];
        assignVectorsToMatrix();
    }
    
    /**
     * this method makes the user fill in a set of n vectors in R^n to make a set of bases
     */
    public double[][] fillVectorsInBases(double[][] bases, int rows, int columns)
    {
        Scanner nut = new Scanner(System.in);
        for (int i = 0; i < columns; i++)
        {
            for (int k = 0; k < rows; k++)
            {
                System.out.println("Enter Vector " + (i+1) + "'s Element " + (k+1) + ".");
                double newElement = Double.parseDouble(nut.nextLine());
                bases[k][i] = newElement;
            }
        }
        return bases;
    }
    
    /**
     * this method checks to see if an entered set of basis vectors is linearly independent <br>
     * it checks the determinant of the square matrix they constitute to see if they have linear independence or not <br>
     * if the determinant is zero, they are linearly dependent and the user has to enter new vectors <br>
     * if not, the program moves on
     */
    public double[][] checkLinearIndependence(double[][] bases)
    {
        double[][] substitute = bases;
        double determinant = Matrix.determinant(substitute, substitute.length);
        if (determinant == 0)
        {
            System.out.println("That's not linearly dependent. Pick a new set of vectors.");
            substitute = fillVectorsInBases(bases, dimension, dimension);
            substitute = checkLinearIndependence(substitute);
        }
        return substitute;
    }
    
    /**
     * this method gives tells the user to enter two sets of bases and checks the linear independence of both of them <br>
     * it then assigns both sets to the instance variables firstBases and secondBases
     */
    public void assignVectorsToMatrix()
    {
        System.out.println("Now, you will will in " + dimension + " basis vectors twice (two sets of vectors).");
        System.out.println("You will start with the first set of bases. We'll call this set B");
        double[][] finalMatrix1 = checkLinearIndependence(fillVectorsInBases(firstBases, dimension, dimension));
        System.out.println("Congratulations, now you will fill in the second set of bases. We'll call this set B'");
        double[][] finalMatrix2 = checkLinearIndependence(fillVectorsInBases(secondBases, dimension, dimension));
        
        firstBases = finalMatrix1;
        secondBases = finalMatrix2;
        
        System.out.println("Both basis sets you inputed are linearly independent. Congratulations.");
    }
    
    /**
     * this method accepts a positive integer from the user and makes it the dimension of the vector <br>
     * it uses the assignDimension() method to get the job done <br>
     * this method also uses assignVectorsToMatrix() to get vectors from the user and put them in two sets of bases <br>
     * the checkLinearIndependence() method checks for linear independence of the vectors by calculating the determinant
     */
    public void problem1And2()
    {
        assignDimension();
        getVectors();
    }
    
    /**
     * this method calculates the transition matrices from B to B' and vice versa <br>
     * it first inverses both bases sets, then does multiplication to get the matrices <br>
     * (B')^-1 * B is transition from B to B', and (B)^-1 * B' is transition from B' to B <br>
     * both transition matrices are outputed with Matrix.printMatrix()
     */
    public void problem3() throws java.lang.Exception
    {
        double[][] fbase = Matrix.duplicateMatrix(firstBases);
        double[][] sbase = Matrix.duplicateMatrix(secondBases);
        
        double[][] inverseOfBases1 = Matrix.invert(fbase);
        double[][] inverseOfBases2 = Matrix.invert(sbase);
        
        transFrom1To2 = Matrix.matrixMultiplication(inverseOfBases2, dimension, dimension, firstBases, dimension, dimension);
        transFrom2To1 = Matrix.matrixMultiplication(inverseOfBases1, dimension, dimension, secondBases, dimension, dimension);
        
        System.out.println("Here are the transition matrices from one set of bases to the other.");
        System.out.println();
        System.out.println("From B to B'");
        Matrix.printMatrix(transFrom1To2);
        System.out.println("From B' to B");
        Matrix.printMatrix(transFrom2To1);
    }
    
    /**
     * this method gets a result vector from the user and puts it in the specifiedVector instance variable
     */
    public void getResultVector()
    {
        double[][] resultVector = new double[dimension][1];
        System.out.println("Let's do something fun. Put in a vector in R^" + dimension + ".");
        
        double[][] finalResult = fillVectorsInBases(resultVector, dimension, 1);
        specifiedVector = finalResult;
    }
    
    /**
     * this method takes the response of the user and checks if the response is valid <br>
     * it takes an input from the user, checks if it is a valid number, then sees if the number is a valid response
     */
    public int basesResponse()
    {
        int response = confirmRangeOfResponse(verifyResponse(decideIfBOrBPrime()));
        return response;
    }
    
    /**
     * this method tells the user to input a number to decide what set of bases they want to use <br>
     * they must enter 1 for basis set B, and 2 for basis set B' <br>
     * the result is returned as a string value
     */
    public String decideIfBOrBPrime()
    {
        Scanner nut = new Scanner(System.in);
        System.out.println("Now, you will get the coords for your inputed vector in respect to your specified set of bases.");
        System.out.println("But... which set of bases do you want? Type 1 for B, type 2 for B'");
        String response = nut.nextLine();
        return response;
    }
    
    /**
     * this method takes the string value from decideIfBOrBPrime() and checks to see if its a valid number <br>
     * if it is not a valid number, the method forces the user to enter the response again
     */
    public int verifyResponse(String response)
    {
        int numSize = 0;
        try
        {
            numSize = Integer.parseInt(response);
        }
        
        catch (Exception e)
        {
            System.out.println("That's not a valid response. Let's try this again.");
            System.out.println();
            numSize = verifyResponse(decideIfBOrBPrime());
        }
        return numSize;
    }
    
    /**
     * this method confirms that the integer in verifyResponse() is 1 or 2 <br>
     * if not 1 or 2, the method makes the user enter the response again
     */
    public int confirmRangeOfResponse(int response)
    {
        if (response == 1 || response == 2)
        {
            return response;
        }
        
        else
        {
            System.out.println("That's not a valid response. Let's try this again.");
            confirmRangeOfResponse(verifyResponse(decideIfBOrBPrime()));
        }
        return 0;
    }
    
    /**
     * this method uses the vector in specifiedVector to create a set of coordinates in both basis sets <br>
     * the method first gets what basis set the user wants in basesResponse() <br>
     * it then multiplies the specified basis set by the vector to get the coordinates of that vector in respect to the bases <br>
     * then it uses a transition matrix to change the coordinates of one set to the coords of another <br>
     * this happens whether B or B' is picked, and if the response is not 1 or 2, the user must put in the response again
     */
    public void printCoordsOfResult() throws java.lang.Exception
    {
        int response = basesResponse();
        if (response == 1)
        {
            double[][] fbase = Matrix.duplicateMatrix(firstBases);
            double[][] sbase = Matrix.duplicateMatrix(secondBases);
            double[][] inverseOfBases1 = Matrix.invert(fbase);
            double[][] inverseOfBases2 = Matrix.invert(sbase);
            System.out.println("Ok. Here are the coordinates of your vector in Basis B");
            System.out.println();
            double[][] vectorInB = Matrix.matrixMultiplication(inverseOfBases1, dimension, dimension, specifiedVector, dimension, 1);
            Matrix.printMatrix(vectorInB);
            System.out.println();
            System.out.println("And here is those coordinates in B'!");
            double[][] vectorInBPrime = Matrix.matrixMultiplication(transFrom1To2, dimension, dimension, vectorInB, dimension, 1);
            Matrix.printMatrix(vectorInBPrime);
            System.out.println();
        }
        
        else if (response == 2)
        {
            double[][] fbase = Matrix.duplicateMatrix(firstBases);
            double[][] sbase = Matrix.duplicateMatrix(secondBases);
            double[][] inverseOfBases1 = Matrix.invert(fbase);
            double[][] inverseOfBases2 = Matrix.invert(sbase);
            System.out.println("Ok. Here are the coordinates of your vector in Basis B'");
            System.out.println();
            double[][] vectorInBPrime = Matrix.matrixMultiplication(inverseOfBases2, dimension, dimension, specifiedVector, dimension, 1);
            Matrix.printMatrix(vectorInBPrime);
            System.out.println();
            System.out.println("And here is those coordinates in B!");
            double[][] vectorInB = Matrix.matrixMultiplication(transFrom2To1, dimension, dimension, vectorInBPrime, dimension, 1);
            Matrix.printMatrix(vectorInB);
            System.out.println();
        }
        
        else
        {
            System.out.println("That's not a valid response. Pick another one.");
            printCoordsOfResult();
        }
    }
    
    /**
     * this method gets a result vector from the user using getResultVector() <br>
     * it also outputs the vector's coordinates in both basis sets given by the user
     */
    public void problem4() throws java.lang.Exception
    {
        getResultVector();
        printCoordsOfResult();
    }
    
    public void manualMode() throws java.lang.Exception
    {
        Scanner nut = new Scanner(System.in);
        boolean manualDone = false;
        
        while (!manualDone)
        {
            System.out.println("Which problem do you want to do? Enter the number.");
            System.out.println("----------------------------------------------");
            System.out.println("Problem 1 and 2: Linearly Independent Bases");
            System.out.println("Problem 3: Output the transition matrices.");
            System.out.println("Problem 4: Vector coords in both bases.");
            System.out.println();
            
            int resp = nut.nextInt();
            if (resp == 1 || resp == 2)
            {
                problem1And2();
                manualDone = stopOrNo();
            }
            
            else if (resp == 3)
            {
                problem3();
                manualDone = stopOrNo();
            }
            
            else if (resp == 4)
            {
                problem4();
                manualDone = stopOrNo();
            }
            
            else
            {
                System.out.println("That's not a valid answer. Respond again.");                
            }
        }
    }
    
    public void automaticMode() throws java.lang.Exception
    {
        System.out.println("Welcome to the Basis Code (Automatic Mode).");
        System.out.println("Just follow the instructions and you'll get through!");
        System.out.println("Now, let's get started.");
        problem1And2();
        problem3();
        problem4();
    }
    
    /**
     * this method executes problems 1 through 4 on the test
     */
    public void execute() throws java.lang.Exception
    {
        Scanner nut = new Scanner(System.in);
        System.out.println("Welcome to the Basis Code.");
        System.out.println("Just follow the instructions and you'll get through!");
        System.out.println("Now, let's get started.");
        System.out.println("Do you want to do automatic mode, or manual mode? Type a or m");
        
        String response = nut.nextLine();
        
        if (response.equals("a"))
        {
            automaticMode();
        }
        
        else if (response.equals("m"))
        {
            manualMode();
        }
        
        else
        {
            System.out.println("That's not a right answer. Let's start again.");
        }
    }
    
    /**
     * main method
     */
    public static void main(String[] args)
    {
        BasesAndCoordinates mat = new BasesAndCoordinates();
        try
        {
            mat.execute();
        }
        catch (java.lang.Exception e)
        {
            e.printStackTrace();
        }
    }
}


