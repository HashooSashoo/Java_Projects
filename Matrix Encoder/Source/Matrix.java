import java.util.Scanner;
/**
 * Library of Matrix functions used by my programs <br>
 * All code by: Zakariya Hashmi
 */
public class Matrix 
{
    /**
     * prints the encoding matrix's values in a grid form
     */
    public static void printMatrix(double[][] matrix)
    {
         for (int i = 0; i < matrix.length; i++)
        {
            for (int k = 0; k < matrix[0].length; k++)
            {
                System.out.print(matrix[i][k] + "  ");
            }
            System.out.println();
            System.out.println();
        }
    }
    
    /**
     * takes a matrix and copies all of its elements to another matrix <br>
     * returns that matrix
     */
    public static double[][] duplicateMatrix(double[][] matrix)
    {
        double[][] duplicate = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++)
        {
            for (int k = 0; k < matrix[0].length; k++)
            {
                duplicate[i][k] = matrix[i][k];
            }
        }
        return duplicate;
    }
    
    public static double[][] changeIntToDoubleMatrix(int[][] mat)
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
    
    public static int[][] changeDoubleToIntMatrix(double[][] mat)
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
     * adds two matrices of the same dimensions together
     */
    public static double[][] addMatrices(double[][] mat1, double[][] mat2)
    {
        double[][] resultMatrix = new double[mat1.length][mat1[0].length];
        for (int i = 0; i < mat1.length; i++)
        {
            for (int k = 0; k < mat1[0].length; k++)
            {
                resultMatrix[i][k] = mat1[i][k] + mat2[i][k];
            }
        }
        return resultMatrix;
    }
    
    /**
     * this is a method to get the determinant of a square matrix of any size
     */
    public static double determinant(double mat[][],int size)
    {
        double det = 0;
        if(size == 1)
        {
            det = mat[0][0];
        }
        else if (size == 2)
        {
            det = mat[0][0] * mat[1][1] - mat[1][0] * mat[0][1];
        }
        else
        {
            det = 0;
            for(int j1 = 0; j1 < size; j1++)
            {
                double[][] m = new double[size-1][];
                for(int k = 0; k < (size-1); k++)
                {
                    m[k] = new double[size-1];
                }
                for(int i = 1; i < size; i++)
                {
                    int j2 = 0;
                    for(int j = 0; j < size; j++)
                    {
                        if (j == j1)
                        {
                            continue;
                        }
                        m[i-1][j2] = mat[i][j];
                        j2++;
                    }
                }
                det += Math.pow(-1.0, 1.0 + j1 + 1.0) * mat[0][j1] * determinant(m,size-1);
            }
        }
        return det;
    }
    
    /**
     * just some code to round a decimal to any place
     */
    public static double roundNumber(double number, int places)
    {
        double scale = Math.pow(10, places);
        return Math.round(number*scale)/scale;
    }
    
    /**
     * this method multiplies two matrices of any size
     */
    public static double[][] matrixMultiplication(double[][] matrix1, int rows1, int cols1, double[][] matrix2, int rows2, int cols2) throws Exception
    {
        // Required condition for matrix multiplication
        if (cols1 != rows2)
        {
            throw new Exception("Invalid matrix given.");
        }
 
        // create a result matrix
        double resultMatrixD[][] = new double[rows1][cols2];
 
        // Core logic for 2 matrices multiplication
        for (int i = 0; i < resultMatrixD.length; i++)
        {
            for (int j = 0; j < resultMatrixD[i].length; j++)
            {
                for (int k = 0; k < cols1; k++)
                {
                    resultMatrixD[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        
        double[][] resultMatrix = new double[rows1][cols2];
        for (int i = 0; i < resultMatrix.length; i++)
        {
            for (int k = 0; k < resultMatrix[0].length; k++)
            {
                resultMatrix[i][k] = roundNumber(resultMatrixD[i][k], 4);
            }
        }
        return resultMatrix;
    }
    
    /**
     * this code inverts an invertable square matrix of any size <br>
     * It first transforms it to an upper triangle (row-echelon form), then it inverts it with row operations.
     */
    public static double[][] invert(double a[][]) 
    {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i)
        {
            b[i][i] = 1;
        }
 
        // Transform the matrix into an upper triangle
        gaussian(a, index);
 
        // Update the matrix b[i][j] with the ratios stored
        for (int i=0; i<n-1; ++i)
        {
            for (int j=i+1; j<n; ++j)
            {
                for (int k=0; k<n; ++k)
                {
                    b[index[j]][k] -= a[index[j]][i]*b[index[i]][k];
                }
            }
        }
 
        // Perform backward substitutions
        for (int i=0; i<n; ++i) 
        {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j) 
            {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k) 
                {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }
 
    // Method to carry out the partial-pivoting Gaussian
    // elimination.  Here index[] stores pivoting order.
    /**
     * turns a matrix into row-echelon form
     */
    public static void gaussian(double a[][], int index[]) 
    {
        int n = index.length;
        double c[] = new double[n];
 
        // Initialize the index
        for (int i=0; i<n; ++i)
        {
            index[i] = i;
        }
 
        // Find the rescaling factors, one from each row
        for (int i=0; i<n; ++i) 
        {
            double c1 = 0;
            for (int j=0; j<n; ++j) 
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1)
                {
                    c1 = c0;
                }
            }
            c[i] = c1;
        }
 
        // Search the pivoting element from each column
        int k = 0;
        for (int j=0; j<n-1; ++j) 
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i) 
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) 
                {
                    pi1 = pi0;
                    k = i;
                }
            }
 
            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i)     
            {
                double pj = a[index[i]][j]/a[index[j]][j];
 
                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;
 
                // Modify other elements accordingly
                for (int l=j+1; l<n; ++l)
                {
                    a[index[i]][l] -= pj*a[index[j]][l];
                }
            }
        }
    }
    
    /**
     * transposes a matrix
     */
    public static double[][] transpose(double[][] mat)
    {
        int transDim1 = mat[0].length;
        int transDim2 = mat.length;
        
        double[][] transMatrix = new double[transDim1][transDim2];
        for (int i = 0; i < mat.length; i++)
        {
            for (int k = 0; k < mat[0].length; k++)
            {
                transMatrix[k][i] = mat[i][k];
            }
        }
        return transMatrix;
    }
    
    public static double[][] ref(double[][] mat)
    {
        double[][] rref = Matrix.duplicateMatrix(mat);
    
        for (int p = 0; p < Math.min(rref.length, rref[0].length); ++p)
        {
            /* Make this pivot 1 */
            double pv = rref[p][p];
            if (pv != 0)
            {
                double pvInv = 1.0 / pv;
                for (int i = 0; i < rref[p].length; ++i)
                {
                    rref[p][i] *= pvInv;
                }
            }
    
            /* Make other rows zero */
            for (int r = 0; r < rref.length; ++r)
            {
                if (r != p)
                {
                    double f = rref[r][p];
                    for (int i = 0; i < rref[r].length; ++i)
                    {
                        rref[r][i] -= f * rref[p][i];
                    }
                }
            }
        }
        return roundMatrix(rref);
    }
    
    public static double[][] roundMatrix(double[][] mat)
    {
        double[][] dupMat = duplicateMatrix(mat);
        for (int i = 0; i < dupMat.length; i++)
        {
            for (int k = 0; k < dupMat[0].length; k++)
            {
                dupMat[i][k] = roundNumber(dupMat[i][k], 3);
            }
        }
        return dupMat;
    }
    
    /**
     * swaps two specified rows of a matrix
     */
    public static void swapRows(double [][] m, int row1, int row2)
    {
        double [] swap = new double[m[0].length];
    
        for(int c1 = 0; c1 < m[0].length; c1++)
        {
            swap[c1] = m[row1][c1];
        }
    
        for(int c1 = 0; c1 < m[0].length; c1++)
        {
            m[row1][c1] = m[row2][c1];
            m[row2][c1] = swap[c1];
        }
    }
    
    /**
     * multiplies a row of a matrix by a scalar
     */
    public static void multiplyRow(double [][] m, int row, double scalar)
    {
        for(int c1 = 0; c1 < m[0].length; c1++)
        {
            m[row][c1] *= scalar;
        }
    }
    
    /**
     * substracts a row by a scalar multiple of another row
     */
    public static void subtractRows(double [][] m, double scalar, int subtract_scalar_times_this_row, int from_this_row)
    {
        for(int c1 = 0; c1 < m[0].length; c1++)
        {
            m[from_this_row][c1] -= scalar * m[subtract_scalar_times_this_row][c1];
        }
    }
    
    public static double[][] reducedRefMYWAY(double[][] mat)
    {
        double[][] dupMat = duplicateMatrix(mat);
        int indexOfLeftmostNonzeroColumn = 0;
        return dupMat;
    }
    
    /**
     * finds the rank of a matrix <br>
     * first it reduces to reduces-row-echelon form courtesy of Matrix.rref() <br>
     * then, for every row of all zeros in the matrix, it subtracts 1 from the dimension <br>
     * the final result is the rank of the matrix
     */
    public static int rank(double[][] mat)
    {
        double[][] newMat = Matrix.ref(mat);
        int rank = newMat.length;
        for (int i = 0; i < newMat.length; i++)
        {
            boolean allZeros = true;
            for (int k = 0; k < newMat[0].length; k++)
            {
                if (newMat[i][k] != 0)
                {
                    allZeros = false;
                }
            }
            
            if (allZeros)
            {
                rank--;
            }
        }
        return rank;
    }
}