import java.util.Scanner;

/**
 * Matrix Encoder and Decoder for Linear Algebra Bonus <br>
 * All code by: Zakariya Hashmi
 */
public class MatrixEncoder extends MatrixSuperclass
{
    /** stores the String message that the user gives to encode **/
    String message;
    /** stores the positions of all the letters in message in matrix form **/
    int[][] matrixOfMessage;
    /** stores the size of the encoding matrix **/
    int sizeOfMatrix;
    /** stores the encoding matrix **/
    int[][] matrix;
    /** stores the final matrix after encoding **/
    int[][] finalCodedMatrix;
    double[][] invertedCodeMatrix;
    
    /**
     * Constructor of MatrixEncoder.<br>
     * Initializes both sizeOfMatrix and message.
     */
    public MatrixEncoder()
    {
        message = "";
        sizeOfMatrix = 0;
    }
    
    /**
     * modifier method of message instance variable
     */
    public void changeMessage(String newMessage)
    {
        message = newMessage;
    }
    
    /**
     * resets all instance variables to zero (or their equivalents of zero)
     */
    public void reset()
    {
        message = "";
        matrixOfMessage = new int[0][0];
        sizeOfMatrix = 0;
        matrix = new int[0][0];
        finalCodedMatrix = new int[0][0];
    }
    
    /**
     * asks for user input of the message they would like to send <br>
     * puts variable in message instance variable
     */
    public void getMessage()
    {
        System.out.println("What message would you like to send?");
        Scanner nut = new Scanner(System.in);
        
        String newMessage = nut.nextLine();
        changeMessage(newMessage);
    }
    
    /**
     * prints the encoding matrix's values in a grid form
     */
    public void printMatrix()
    {
         for (int i = 0; i < sizeOfMatrix; i++)
        {
            for (int k = 0; k < sizeOfMatrix; k++)
            {
                System.out.print(matrix[i][k] + " ");
            }
            System.out.println();
        }
    }
    
    /**
     * Does 4 things: <br>
     * It calls getMessage() to put the user's message in the message instance variable <br>
     * It calls askForSize() to ask for and verify the size of the encoding matrix <br>
     * It calls enterMatrixNum() to let the user enter all of their values for the encoding matrix <br>
     * NOTE: enterMatrixNum() also checks if the encoding values are inversible <br>
     * Finally, it prints out all of the data that has been entered (message, encoding matrix size, matrix itself)
     */
    public void askForEncodingMatrix()
    {
        getMessage();
        askForSize();
        enterMatrixNum();
        
        System.out.println("Message: " + message);
        System.out.println("Encoding Matrix Size: " + sizeOfMatrix);
        System.out.println("Encoding Matrix Numbers: ");
        printMatrix();
    }
    
    /**
     * this method asks for every element of the encoding matrix individually <br>
     * this method ONLY happens when the encoding matrix is initialized with it's verified size <br>
     * it puts all of the values in the matrix instance variable <br>
     * NOTE: THERE IS NO CHECKING IF INPUT IS CORRECT. USER MUST ENTER INTEGERS ONLY.
     */
    public void enterMatrixNum()
    {
        for (int i = 0; i < sizeOfMatrix; i++)
        {
            for (int k = 0; k < sizeOfMatrix; k++)
            {
                System.out.println("Enter the number for matrix element A" + (i+1) + (k+1) + ".");
                Scanner nut = new Scanner(System.in);
                int element = Integer.parseInt(nut.nextLine());
                matrix[i][k] = element;
            }
        }
        checkIfInvertable(matrix, sizeOfMatrix);
    }
    
    /**
     * this method checks if the encoding matrix is invertible <br>
     * it calls the determinant() method to find the determinant and check if it doesn't equal zero <br>
     * if the matrix is not invertible, enterMatrixNum90 is called again to reenter the numbers
     */
    public void checkIfInvertable(int[][] matrixElem, int matrixSize)
    {
        double invertable = Matrix.determinant(Matrix.changeIntToDoubleMatrix(matrixElem), matrixSize);
        if (invertable == 0.0)
        {
            System.out.println("The matrix is not invertable. Come up with another one.");
            enterMatrixNum();
        }
        
        else
        {
            System.out.println("Your matrix is invertable!!! You are ready to code your message.");
        }
    }
    
    /**
     * this method finds the numerical positions of every letter in message and puts it in an array <br>
     * there is an alphabet string that acts as a guide, and indexOf() is called to get the position <br>
     * each number position is put in a 1D integer array that is returned by the method
     */
    public int[] makeMessageArray()
    {
        String managableMessage = message.toLowerCase();
        String alphabet = " abcdefghijklmnopqrstuvwxyz";
        int[] posOfLetters = new int[message.length()];
        for (int i = 0; i < message.length(); i++)
        {
            int indexOfLetter = alphabet.indexOf(managableMessage.substring(i, i+1));
            posOfLetters[i] = indexOfLetter;
        }
        
        return posOfLetters;
    }
    
    /**
     * this method turns an array of letter indexes into a 2D array <br>
     * specifically, a 2D array that can be multiplied with the encoding matrix to get our final matrix <br>
     * it first calculates how many elements we need in our matrix, and from there gets the rows and columns <br>
     * after that, every letter index is placed in the matrixOfMessage instance variable (where we store the message matrix)
     */
    public void make2DLetterMatrix(int[] arrayOfNums)
    {
        int numOfElements = message.length() + (sizeOfMatrix - (message.length() % sizeOfMatrix));
        matrixOfMessage = new int[numOfElements/sizeOfMatrix][sizeOfMatrix];
        int countOfArray = 0;
        for (int i = 0; i < matrixOfMessage.length; i++)
        {
            for (int k = 0; k < matrixOfMessage[0].length; k++)
            {
                if (countOfArray >= arrayOfNums.length)
                {
                    continue;
                }
                
                else
                {
                    matrixOfMessage[i][k] = arrayOfNums[countOfArray];
                    countOfArray++;
                }
            }
        }
    }
    
    /**
     * this method executes make2DLetterMatrix() with the parameter given by makeMessageArray()
     */
    public void setTheLetterMatrix()
    {
        make2DLetterMatrix(makeMessageArray());
    }
    
    /**
     * this method multiplies the letter-index matrix and the encoding matrix to get the final encoded matrix <br>
     * the matrixes (along with row and column info) is put in the matrixMultiplication method to get a final result <br>
     * after that, the final result is put in the finalCodedMatrix instance variable
     * NOTE: THE TRY-CATCH IS REQUIRED.
     */
    public void multiplyLetterAndCode()
    {
        int letterMatRows = matrixOfMessage.length;
        int letterMatColum = matrixOfMessage[0].length;
        int codeMatRows = matrix.length;
        int codeMatColum = matrix[0].length;
        
        try
        {
            double[][] finalEncodement = Matrix.matrixMultiplication(changeIntToDoubleMatrix(matrixOfMessage), letterMatRows, letterMatColum, changeIntToDoubleMatrix(matrix), codeMatRows, codeMatColum);
            for (int i = 0; i < finalEncodement.length; i++)
            {
                for (int k = 0; k < finalEncodement[0].length; k++)
                {
                    finalEncodement[i][k] = (double)Math.round(finalEncodement[i][k]);
                }
            }
            finalCodedMatrix = Matrix.changeDoubleToIntMatrix(finalEncodement);
        }
        catch (java.lang.Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    /**
     * this method asks for what size of encoding matrix the user wants <br>
     * NOTE: ONLY ONE DIMENSION IS REQUIRED AS IT IS A SQUARE MATRIX <br>
     * after getting the size, it is checked to see if it is an allowed size <br>
     * after that, the size is assigned to the sizeOfMatrix instance variable
     */
    public int askForSize()
    {
        System.out.println("What size of encoding matrix do you want? (enter row or column number)");
        Scanner nut = new Scanner(System.in);
        
        String inputSize = nut.nextLine();
        int size = managableSize(inputSize);
        
        sizeOfMatrix = size;
        matrix = new int[size][size];
        return size;
    }
    
    /**
     * this method checks if the size inputed in askForSize() is an integer <br>
     * it tries and catches an error, and makes you re-input if the test fails
     */
    public int sizeIsInputedCorrect(String size)
    {
        int numSize = 0;
        try
        {
            numSize = Integer.parseInt(size);
        }
        
        catch (Exception e)
        {
            System.out.println("That's not a valid number. Try again.");
            numSize = askForSize();
        }
        
        return numSize;
    }
    
    /**
     * this method checks if the size inputed in askForSize() fits the size requirements <br>
     * the size only passed if its less than 10, and smaller than the length of the message <br>
     * it will also have to pass a test to see if there is one element for each letter in the message (length < size^2) <br>
     * if it fails any of these tests, the user has to re-enter the size
     */
    public int sizeError(int size)
    {
        int newSize = size;
        if (newSize > 10)
        {
            System.out.println("Choose a smaller matrix. This is too big for my computer lol.");
            newSize = askForSize();
        }
        
        if (newSize > message.length())
        {
            System.out.println("Your message is too small for this matrix. Choose a smaller one.");
            newSize = askForSize();
        }
        
        return newSize;
    }
    
    /**
     * executes both sizeIsInputedCorrect() and sizeError() to check if size is allowed
     */
    public int managableSize(String size)
    {
        int goodSize = sizeError(sizeIsInputedCorrect(size));
        return goodSize;
    }
    
    /**
     * this method does everything aforementioned to encode the message <br>
     * it asks for and initializes the encoding matrix in askForEncodingMatrix() <br>
     * it turns the message into a multipliable letter matrix in setTheLetterMatrix() <br>
     * it multiplies both matrices to get the final result matrix in multiplyLetterAndCode() <br>
     * finally, it prints out all the numbers in the final result, giving the user the code of their message
     */
    public void doEncoding()
    {
        reset();
        askForEncodingMatrix();
        setTheLetterMatrix();
        multiplyLetterAndCode();
        System.out.println("Your final code is here.");
        System.out.println();
        for (int i = 0; i < finalCodedMatrix.length; i++)
        {
            for (int k = 0; k < finalCodedMatrix[0].length; k++)
            {
                System.out.print(finalCodedMatrix[i][k] + " ");
            }
        }
        System.out.println();
        System.out.println("REMEMBER: You must write down or save both your encoding matrix and your code to decode the message.");
        System.out.println();
    }
    
    /**
     * the yes/no command to begin doEncoding()
     */
    public void promptToEncode()
    {
        System.out.println("Do you want to encode your message? y or n");
        Scanner nut = new Scanner(System.in);
        String response = nut.nextLine();
        
        if (response.equals("y"))
        {
            System.out.println("Ok, lets get started!");
            System.out.println();
            doEncoding();
        }
        
        else
        {
            System.out.println("Ok. See ya.");
        }
    }
    
    public int askForSizeEDITED()
    {
        System.out.println("What size of encoding matrix do you want? (enter row or column number)");
        Scanner nut = new Scanner(System.in);
        
        String inputSize = nut.nextLine();
        int size = sizeIsInputedCorrect(inputSize);
        
        if (size > 10)
        {
            System.out.println("I can't decode that. Use another matrix.");
            size = askForSizeEDITED();
        }
        
        sizeOfMatrix = size;
        matrix = new int[size][size];
        return size;
    }
    
    public void askForDecodingMatrix()
    {
        askForSizeEDITED();
        enterMatrixNum();
        
        System.out.println("Message: " + message);
        System.out.println("Encoding Matrix Size: " + sizeOfMatrix);
        System.out.println("Encoding Matrix Numbers: ");
        printMatrix();
    }
    
    public double[][] changeIntToDoubleMatrix(int[][] mat)
    {
        double[][] doubleMat = new double[mat.length][mat[0].length];
        for (int i = 0; i < doubleMat.length; i++)
        {
            for (int k = 0; k < doubleMat[0].length; k++)
            {
                doubleMat[i][k] = mat[i][k];
            }
        }
        return doubleMat;
    }
    
    public int[][] changeDoubleToIntMatrix(double[][] mat)
    {
        int[][] intMat = new int[mat.length][mat[0].length];
        for (int i = 0; i < intMat.length; i++)
        {
            for (int k = 0; k < intMat[0].length; k++)
            {
                intMat[i][k] = (int)mat[i][k];
            }
        }
        return intMat;
    }
    
    /**
     * this method changes the encoding matrix to its inverse to decode the message <br>
     * calls invert() method from Matrix class
     */
    public void changeEncodingToInverse()
    {
        invertedCodeMatrix = Matrix.invert(changeIntToDoubleMatrix(matrix));
    }
    
    public void getSizeOfFinalMatrix()
    {
        System.out.println("How many numbers were in your message code?");
        Scanner nut = new Scanner(System.in);
        
        String numberInCodeString = nut.nextLine();
        int numberInCode = sizeIsInputedCorrect(numberInCodeString);

        finalCodedMatrix = new int[numberInCode / sizeOfMatrix][sizeOfMatrix];
    }
    
    public void enterFinalMatrixElements()
    {
        int count = 1;
        for (int i = 0; i < finalCodedMatrix.length; i++)
        {
            for (int k = 0; k < finalCodedMatrix[0].length; k++)
            {
                System.out.println("Enter number " + (count) + " of your code.");
                Scanner nut = new Scanner(System.in);
                int element = Integer.parseInt(nut.nextLine());
                finalCodedMatrix[i][k] = element;
                count++;
            }
        }
    }
    
    public void askForFinalMatrix()
    {
        getSizeOfFinalMatrix();
        enterFinalMatrixElements();
    }
    
    public void decodeForLetterMatrix()
    {
        int finalMatRows = finalCodedMatrix.length;
        int finalMatColum = finalCodedMatrix[0].length;
        int invMatRows = matrix.length;
        int invMatColum = matrix[0].length;
        
        try
        {
            double[][] finalEncodement = Matrix.matrixMultiplication(changeIntToDoubleMatrix(finalCodedMatrix), finalMatRows, finalMatColum, invertedCodeMatrix, invMatRows, invMatColum);
            for (int i = 0; i < finalEncodement.length; i++)
            {
                for (int k = 0; k < finalEncodement[0].length; k++)
                {
                    finalEncodement[i][k] = (double)Math.round(finalEncodement[i][k]);
                }
            }
            matrixOfMessage = changeDoubleToIntMatrix(finalEncodement);
        }
        catch (java.lang.Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public int[] letterMatrixToArray()
    {
        int[] letterArray = new int[matrixOfMessage.length*matrixOfMessage[0].length];
        int countInArray = 0;
        for (int i = 0; i < matrixOfMessage.length; i++)
        {
            for (int k = 0; k < matrixOfMessage[0].length; k++)
            {
                letterArray[countInArray] = matrixOfMessage[i][k];
                countInArray++;
            }
        }
        return letterArray;
    }
    
    public void letterArrayToDecodedMessage(int[] arrayOfLetters)
    {
        String alphabet = " abcdefghijklmnopqrstuvwxyz";
        String decodedMessage  = "";
        
        for (int i = 0; i < arrayOfLetters.length; i++)
        {
            int letterElem = arrayOfLetters[i];
            String newLetter = alphabet.substring(letterElem, letterElem + 1);
            decodedMessage = decodedMessage + newLetter;
        }
        
        message = decodedMessage;
    }
    
    public void letterMatrixToMessage()
    {
        letterArrayToDecodedMessage(letterMatrixToArray());
    }
    
    public void doDecoding()
    {
        reset();
        askForDecodingMatrix();
        changeEncodingToInverse();
        askForFinalMatrix();
        decodeForLetterMatrix();
        letterMatrixToMessage();
        
        System.out.println("Here is your decoded message.");
        System.out.println();
        System.out.println("*** " + message + " ***");
    }
    
    public void chooseEncodeOrDecode()
    {
        System.out.println("Would you like to encode a message, or decode a message? e for encode, d for decode, x to exit");
        Scanner nut = new Scanner(System.in);
        String response = nut.nextLine();
        
        if (response.equals("e"))
        {
            System.out.println("Ok. Let's encode!!!");
            doEncoding();
        }
        
        else if (response.equals("d"))
        {
            System.out.println("Ok. Decoding time!!!");
            doDecoding();
        }
        
        else if (response.equals("x"))
        {
            System.out.println("Ok. See ya!.");
        }
        
        else
        {
            System.out.println("That's not a valid answer.");
            chooseEncodeOrDecode();
        }
    }
    
    /**
     * the yes/no command to restart doEncoding()
     */
    public void wannaCodeAgain()
    {
        System.out.println("Do you want to code or decode again? y for yes, n for no");
        Scanner nut = new Scanner(System.in);
        String response = nut.nextLine();
        
        if (response.equals("y"))
        {
            System.out.println("Ok, one more message!");
            System.out.println();
            chooseEncodeOrDecode();
            wannaCodeAgain();
        }
        
        else
        {
            System.out.println("Ok. See ya.");
        }
    }
    
    /**
     * executes all of the code
     */
    public void execute()
    {
        System.out.println("Welcome to the message encoder.");
        System.out.println("This program takes your message and a matrix, and encodes it.");
        System.out.println("It also can take your matrix and your code, and decode your message!");
        System.out.println();
        System.out.println("Rules:");
        System.out.println("\tOnly messages with lowercase letters and spaces can be encoded");
        System.out.println("\tThe message must be at least 2 characters short and at most 100 characters long.");
        System.out.println("\tThe encoding matrix must be 2x2 at least and 10x10 at most.");
        System.out.println();
        
        chooseEncodeOrDecode();
        wannaCodeAgain();
    }
    
    /**
     * main method
     */
    public static void main(String[] args)
    {
        MatrixEncoder matrixTool = new MatrixEncoder();
        matrixTool.execute();
    }
}
