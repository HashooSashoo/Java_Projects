import java.util.Scanner;
/**
 * Linear Algebra Test 2 - Option 2 : Part 2 <br>
 * All code by: Zakariya Hashmi
 */
public class RankAndSpaces extends MatrixSuperclass
{
    private int dim1; /** Stores the row number of a matrix **/
    private int dim2; /** Stores the column number of a matrix **/
    private int randNum1; /** Stores a random number given by the user **/
    private int randNum2; /** Stores another random number given by the user **/
    private double[][] matrix; /** Stores a matrix with dimensions dim1 and dim2 **/
    
    /**
     * tells the user to input a dimension of the matrix between 1 and 15 <br>
     * the response is returned as a string value
     */
    public String giveDimension()
    {
        System.out.println("Give me a number between 1 and 15 (inclusive)");
        Scanner nut = new Scanner(System.in);
        String response = nut.nextLine();
        return response;
    }
    
    /**
     * this method checks if the dimension entered is an actual number <br>
     * if not an actual number, the method makes the user enter the dimension again
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
     * this method checks if the number in verifyDimension() is between 1 and 15 <br>
     * if it is not in the range, the method makes the user enter the dimension again
     */
    public int confirmRangeOfDimension(int p_dimension)
    {
        if (p_dimension <= 15 && p_dimension >= 1)
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
     * this method takes 2 dimensions from the user and confirms that they are valid <br>
     * it then puts those dimensions in dim1 and dim2 respectively
     */
    public void assignDimension()
    {
        System.out.println("Let's assign the number of rows on your matrix.");
        dim1 = confirmRangeOfDimension(verifyDimension(giveDimension()));
        System.out.println("Great job! Now let's assign the number of columns.");
        dim2 = confirmRangeOfDimension(verifyDimension(giveDimension()));
    }
    
    /**
     * this method asks for a random number from the user <br>
     * it returns the number as a string value
     */
    public String getRandomNumbers()
    {
        System.out.println("Give me a random integer number");
        Scanner nut = new Scanner(System.in);
        String response = nut.nextLine();
        return response;
    }
    
    /**
     * this method verifies that the number is an actual number <br>
     * if not a number, the method makes the user enter another number
     */
    public int verifyNumber(String p_dimension)
    {
        int numSize = 0;
        try
        {
            numSize = Integer.parseInt(p_dimension);
        }
        
        catch (Exception e)
        {
            System.out.println("That's not a valid number. Try again.");
            numSize = verifyDimension(getRandomNumbers());
        }
        
        return numSize;
    }
    
    /**
     * this method checks if of the 2 numbers entered, the first one is smaller than the second one <br>
     * it asks for and verifies two numbers, and if the second one is bigger, then both are assigned to randNum1 and randNum2 respectively <br>
     * if the first one is bigger, however, the user must pick two different numbers and start over
     */
    public void confirmNumbers()
    {
        System.out.println("Now you must give me 2 random numbers. The second one must be bigger than the first one.");
        int number = 0;
        number = verifyNumber(getRandomNumbers());
        System.out.println("Now give the second random number. THIS MUST BE BIGGER THAN THE FIRST ONE.");
        int number2 = 0;
        number2 = verifyNumber(getRandomNumbers());
        if (number > number2)
        {
            System.out.println("You didn't follow my instructions, idiot. Try again.");
            confirmNumbers();
            return;
        }
        
        else
        {
            randNum1 = number;
            randNum2 = number2;
        }
    }
    
    /**
     * this method makes a random integer in between randNum1 and randNum2 (inclusive)
     */
    public int randNumWithRange()
    {
        return (int)(Math.random()*(randNum2-randNum1+1)) + randNum1;
    }
    
    /**
     * this method makes a matrix with the dimensions dim1 and dim2 and a double[][] 2d array <br>
     * it then fills the matrix with random integers between randNum1 and randNum2 using randNumWithRange() <br>
     */
    public void makeMatrix()
    {
        matrix = new double[dim1][dim2];
        for (int i = 0; i < matrix.length; i++)
        {
            for (int k = 0; k < matrix[0].length; k++)
            {
                int random = randNumWithRange();
                matrix[i][k] = random;
            }
        }
    }
    
    /**
     * this method gets the specified dimensions of the matrix from the user, which are stores in variables dim1 and dim2 <br>
     * it also gets two random numbers from the user and stores them in variables randNum1 and randNum2
     */
    public void getDimensionAndNumbers()
    {
        assignDimension();
        confirmNumbers();
    }
    
    /**
     * this method uses the rankOfMatrix method in the Matrix library to get the rank of a matrix <br>
     * the rankOfMatrix method works by turning the matrix to reduced row echelon form <br>
     * it then subtracts one from the dimension for every time there is a row of all zeros
     */
    public int outputRank(double[][] mat)
    {
        double[][] matrixo = Matrix.duplicateMatrix(mat);
        int rank = Matrix.rank(matrixo);
        return rank;
    }
    
    /**
     * this method does many things... <br>
     * it gets the dimensions of the variable matrix and numbers of the user with getDimensionAndNumbers() <br>
     * it makes a matrix with the given dimensions with random numbers between the user given numbers <br>
     * it prints the matrix out and gives the rank using Matrix.printMatrix() and outputRank() respectively
     */
    public void problem5()
    {
        getDimensionAndNumbers();
        makeMatrix();
        System.out.println("Using random numbers from " + randNum1 + " to " + randNum2 + ", I have made a matrix for you.");
        Matrix.printMatrix(matrix);
        System.out.println("The rank of this matrix is " + outputRank(matrix) + ".");
    }
    
    /**
     * this method takes a row vector and a column vector from the matrix and mulitplies both together <br>
     * it uses Matrix.matrixMultiplication() to get a new result matrix <br>
     * the rank is then outputed with outputRank()
     */
    public void problem6() throws java.lang.Exception
    {
        System.out.println("Now, I will take a row vector and a column vector from the generated matrix, and make a new matrix!");
        System.out.println("I'll multiply the vectors together to make a matrix.");
        double[][] vectorInRM = new double[dim1][1];
        for (int i = 0; i < dim1; i++)
        {
            for (int k = 0; k < 1; k++)
            {
                vectorInRM[i][k] = randNumWithRange();
            }
        }
        System.out.println("This is vector X.");
        Matrix.printMatrix(vectorInRM);
        
        double[][] vectorInRNTrans = new double[1][dim2];
        for (int i = 0; i < 1; i++)
        {
            for (int k = 0; k < dim2; k++)
            {
                vectorInRNTrans[i][k] = randNumWithRange();
            }
        }
        System.out.println("This is vector Y.");
        Matrix.printMatrix(Matrix.transpose(vectorInRNTrans));
        
        double[][] newMat = Matrix.matrixMultiplication(vectorInRM, dim1, 1, vectorInRNTrans, 1, dim2);
        Matrix.printMatrix(newMat);
        System.out.println("And in RREF form.");
        Matrix.printMatrix(Matrix.ref(newMat));
        System.out.println("The rank of the matrix should be one, but let's see from the output below...");
        System.out.println("The rank of this matrix is: " + outputRank(newMat));
    }
    
    /**
     * this method takes a response for what specified rank the user wants their matrix to be <br>
     * it returns the value as a string
     */
    public String getResponseForRankedMat()
    {
        System.out.println("Now, we will give you the ability to make a matrix with ANY rank you want.");
        assignDimension();
        System.out.println("Now, tell me the rank you want for your matrix.");
        System.out.println("Pick a number between 1 and " + Math.min(dim1, dim2) + ".");
        Scanner nut = new Scanner(System.in);
        String response = nut.nextLine();
        return response;
    }
    
    /**
     * this method verifies that the number from getResponseForRankedMat() exists <br>
     * if the response is not a real number, the user must input their response again
     */
    public int verifyNumberExists(String p_dimension)
    {
        int numSize = 0;
        try
        {
            numSize = Integer.parseInt(p_dimension);
        }
        
        catch (Exception e)
        {
            System.out.println("That's not a valid number. Try again.");
            numSize = verifyNumberExists(getResponseForRankedMat());
        }
        return numSize;
    }
    
    /**
     * this method checks to see if the specified rank is in between 1 and the dimension of the matrix <br>
     * if it doesn't follow this rule, the user needs to input another rank
     */
    public int confirmNumberOfRank(int number)
    {
        if (number >= 1 && number <= Math.min(dim1, dim2))
        {
            return number;
        }
        
        else
        {
            System.out.println("That's not valid. Pick a number that we can use.");
            confirmNumberOfRank(verifyNumberExists(getResponseForRankedMat()));
        }
        return 0;
    }
    
    /**
     * this method generates dim1 vectors in R^dim1 <br>
     * it creates a dim1 by dim1 matrix with dim1 vectors <br>
     * the vectors are checked to be lineatly independent by calculating the determinant <br>
     * if the determinant is not zero, they are linearly independent, and the matrix is returned
     */
    public double[][] getMatrixWithLinearlyIndependentVectors(int rank)
    {
        int maxRank = Math.max(dim1, dim2);
        boolean goodMat = false;
        double[][] squareMat = new double[maxRank][maxRank];
        while (!goodMat)
        {
            for (int i = 0; i < maxRank; i++)
            {
                for (int k = 0; k < maxRank; k++)
                {
                    squareMat[i][k] = (int)(Math.random()*50);
                }
            }
            
            if (Matrix.determinant(squareMat, maxRank) != 0)
            {
                break;
            }
        }
        return squareMat;
    }
    
    /**
     * this method takes the square matrix outputed in the above method <br>
     * it shortens it so that there is only rank number of vectors in the matrix <br>
     * that matrix is returned
     */
    public double[][] getMatrixWithKVectors(double[][] squareMatrix, int rank)
    {
        double[][] kmatrix = new double[squareMatrix.length][rank];
        for (int i = 0; i < rank; i++)
        {
            for (int k = 0; k < squareMatrix.length; k++)
            {
                kmatrix[k][i] = squareMatrix[k][i];
            }
        }
        return kmatrix;
    }
    
    /**
     * this method multiplies every vector in the ranked matrix from getMatrixWithKVectors() <br>
     * it uses Matrix.matrixMultiplication() to multiply them, and each result is added with Matrix.addMatrices() <br>
     * the matrix with the sum of all of the transverses is returned
     * NOTE: CHANGE SO IT MIN OF DIMENSIONS
     */
    public double[][] getSumOfTransverses(double[][] vecMatrix) throws java.lang.Exception
    {
        int dim = Math.min(vecMatrix.length, vecMatrix[0].length);
        double[][] finalMatrix = new double[0][0];
        if (dim == vecMatrix.length)
        {
            finalMatrix = new double[vecMatrix.length][vecMatrix.length];
            for (int i = 0; i < vecMatrix[0].length; i++)
            {
                double[][] vector = new double[vecMatrix.length][1];
                double[][] vectorT = new double[1][vecMatrix.length];
                for (int k = 0; k < vecMatrix.length; k++)
                {
                    vector[k][0] = vecMatrix[k][i];
                    vectorT[0][k] = vecMatrix[k][i];
                }
                double[][] multMats = Matrix.matrixMultiplication(vector, vecMatrix.length, 1, vectorT, 1, vecMatrix.length);
                finalMatrix = Matrix.addMatrices(finalMatrix, multMats);
            }
        }
        
        else if (dim == vecMatrix[0].length)
        {
            finalMatrix = new double[vecMatrix[0].length][vecMatrix[0].length];
            for (int i = 0; i < vecMatrix.length; i++)
            {
                double[][] vector = new double[vecMatrix.length][1];
                double[][] vectorT = new double[1][vecMatrix.length];
                for (int k = 0; k < vecMatrix[0].length; k++)
                {
                    vector[k][0] = vecMatrix[i][k];
                    vectorT[0][k] = vecMatrix[i][k];
                }
                double[][] multMats = Matrix.matrixMultiplication(vector, vecMatrix.length, 1, vectorT, 1, vecMatrix.length);
                finalMatrix = Matrix.addMatrices(finalMatrix, multMats);
            }
        }
        return finalMatrix;
    }
    
    /**
     * this method takes the user given rank, and creates a matrix with dim1 vectors in R^dim1 <br>
     * the resulting matrix is limited to only have rank number of vectors <br>
     * the vectors are then multiplied to their transposes and added together to create a matrix with the user-specified rank <br>
     * that matrix is returned
     */
    public double[][] giveASpecifiedRankMatrix() throws java.lang.Exception
    {
        int rank = confirmNumberOfRank(verifyNumberExists(getResponseForRankedMat()));
        double[][] squareMatrix = getMatrixWithLinearlyIndependentVectors(rank);
        Matrix.printMatrix(squareMatrix);
        double[][] rankedMatrix = getMatrixWithKVectors(squareMatrix, rank);
        Matrix.printMatrix(rankedMatrix);
        double[][] finalMatrix = getSumOfTransverses(rankedMatrix);
        Matrix.printMatrix(finalMatrix);
        return finalMatrix;
    }
    
    /**
     * this formats the matrix from getSumOfTransverses into a dim1 by dim2 matrix <br>
     * the method usually chops off the matrix to make it fit into the dimensions given by the user <br>
     * that matrix is then returned
     */
    public double[][] putMatrixInDimMatrix(double[][] matrixo)
    {
        double[][] newMatrix = new double[dim1][dim2];
        for (int i = 0; i < newMatrix.length; i++)
        {
            for (int k = 0; k < newMatrix[0].length; k++)
            {
                newMatrix[i][k] = 0;
            }
        }
        
        if (dim1*dim2 >= matrixo.length * matrixo[0].length)
        {
            for (int i = 0; i < matrixo.length; i++)
            {
                for (int k = 0; k < matrixo[0].length; k++)
                {
                    newMatrix[i][k] = matrixo[i][k];
                }
            }
        }
        
        else
        {
            for (int i = 0; i < newMatrix.length; i++)
            {
                for (int k = 0; k < newMatrix[0].length; k++)
                {
                    newMatrix[i][k] = matrixo[i][k];
                }
            }
        }
        return newMatrix;
    }
    
    /**
     * this method takes the user given rank, and creates a matrix with dim1 vectors in R^dim1 <br>
     * the resulting matrix is limited to only have rank number of vectors <br>
     * the vectors are then multiplied to their transposes and added together to create a matrix with the user-specified rank <br>
     * that matrix is then formatted to meet the dimensions of the user, and the matrix is printed
     */
    public void problem7() throws java.lang.Exception
    {
        int rank = confirmNumberOfRank(verifyNumberExists(getResponseForRankedMat()));
        double[][] squareMatrix = getMatrixWithLinearlyIndependentVectors(rank);
        double[][] rankedMatrix = getMatrixWithKVectors(squareMatrix, rank);
        double[][] finalMatrix = getSumOfTransverses(rankedMatrix);
        double[][] finalFinalMatrix = putMatrixInDimMatrix(finalMatrix);
        System.out.println("Ok. Here is your " + dim1 + " by " + dim2 + " with rank " + rank + ".");
        Matrix.printMatrix(finalFinalMatrix);
    }
    
    /**
     * this method makes the user input a matrix all by themself <br>
     * the user must first give the dimensions, and then enter in all of the elements of the matrix <br>
     * that matrix is stored in the matrix instance variable
     */
    public double[][] askUserForMatrix()
    {
        Scanner nut = new Scanner(System.in);
        System.out.println("Now, you are going to give me a matrix, and I will make a set of basis vectors for the column space of that matrix.");
        assignDimension();
        matrix = new double[dim1][dim2];
        System.out.println("Now input each element 1 by 1 (hope you didn't pick a huge matrix!)");
        for (int i = 0; i < matrix.length; i++)
        {
            for (int k = 0; k < matrix[0].length; k++)
            {
                System.out.println("Enter element A" + (i+1) + "" + (k+1) + ".");
                matrix[i][k] = nut.nextInt();
            }
        }
        double[][] newMat = Matrix.duplicateMatrix(matrix);
        return newMat;
    }
    
    /**
     * this method finds the bases for a matrix given by the user <br>
     * it first transposes the matrix with Matrix.transpose(), then turns it into reduced row echelon form with Matrix.rref() <br>
     * that matrix is then printed out
     */
    public void findBasesForMatrix()
    {
        System.out.println("This is the matrix you inputed.");
        Matrix.printMatrix(matrix);
        double[][] basesForColumnSpace = Matrix.ref(matrix);
        System.out.println("Here are the bases for your matrix.");
        Matrix.printMatrix(basesForColumnSpace);
    }
    
    public int[] getIndexesOfIndepvectors(double[][] rrefMat)
    {
        int[] indexOfPivs = new int[Matrix.rank(rrefMat)];
        int pivIndexCount = 0;
        for (int i = 0; i < rrefMat[0].length; i++)
        {
            boolean isPivotCol = true;
            boolean pivotUsed = false;
            for (int k = 0; k < rrefMat.length; k++)
            {
                double elem = rrefMat[k][i];
                if (elem > 1 || elem < 0)
                {
                    isPivotCol = false;
                }
                
                else if (elem > 0 && pivotUsed == true)
                {
                    isPivotCol = false;
                }
                
                if (elem == 1)
                {
                    pivotUsed = true;
                }
            }
            
            if (isPivotCol)
            {
                indexOfPivs[pivIndexCount] = i;
                pivIndexCount++;
            }
        }
        return indexOfPivs;
    }
    
    public void getCorrespondingVectors(int[] indexes, double[][] inputedMatrix)
    {
        System.out.println();
        System.out.println("And here are the vectors in your matrix that are linearly independent!");
        double[][] allIndepVecs = new double[matrix.length][indexes.length];
        
        for (int i = 0; i < indexes.length; i++)
        {
            for (int k = 0; k < matrix.length; k++)
            {
                allIndepVecs[k][i] = inputedMatrix[k][indexes[i]];
            }
        }
        
        Matrix.printMatrix(allIndepVecs);
    }
    
    public void isolateVectors(double[][] origMatrix)
    {
        int[] indexes = getIndexesOfIndepvectors(Matrix.ref(matrix));
        getCorrespondingVectors(indexes, origMatrix);
    }
    
    /**
     * this method asks the user to input a matrix with dimensions of their choice <br>
     * it then calculates the bases of the column space of the matrix
     */
    public void problem8()
    {
        double[][] origMatrix = askUserForMatrix();
        findBasesForMatrix();
        isolateVectors(origMatrix);
    }
    
    /**
     * this method executes all of the problems in part 2 of the test
     */
    public void execute() throws java.lang.Exception
    {
        problem5();
        problem6();
        problem7();
        problem8();
    }
    
    /**
     * main method
     */
    public static void main(String[] args)
    {
        RankAndSpaces rands = new RankAndSpaces();
        try
        {
            rands.execute();
        }
        catch (java.lang.Exception e)
        {
            e.printStackTrace();
        }
    }
}
