import java.util.Scanner;
public class NQueens
{
    public int[][] board;
    public int N;
    public int numSolutions;

    public NQueens(int n)
    {
        board = new int[n][n];
        N = n;
        numSolutions = 0;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                board[i][j] = 0;
            }
        }
    }

    /* 
     * checks to see if a queen placed at placementRow, placementCol is valid.
     */
    public boolean isValid(int board[][], int row, int col) 
    { 
        for (int i = 0; i < col; i++) 
        {
            if (board[row][i] == 1)
            {
                return false; 
            }
        }
        
        for (int i=row, j=col; i>=0 && j>=0; i--, j--) 
        {
            if (board[i][j] == 1) 
            {
                return false; 
            }
        }
        
        for (int i=row, j=col; j>=0 && i<N; i++, j--) 
        {
            if (board[i][j] == 1)
            {
                return false;
            }
        }
        return true; 
    } 


    public int checkRow(int[][] board, int col) 
    {
        if (col == N)
        {
            numSolutions++;
            return numSolutions;
        }
        for (int i = 0; i < N; i++)
        {
            if (isValid(board, i, col))
            {
                board[i][col] = 1;
            }
            checkRow(board, col + 1);
            board[i][col] = 0;
        }
        return numSolutions;
    } 


    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of queens: ");
        int n = sc.nextInt();
        NQueens a = new NQueens(n);
        System.out.printf("The number of valid arrangements is %d\n", a.checkRow(a.board, 0));
    }
}
