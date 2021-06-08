import java.util.Scanner;
public class Jugs
{
    int A;              
    int B;              
    int C;              
    
    boolean[][] visited;   
    int[][] lastA ;
    int[][] lastB;
    String[][] lastString;  
    
    public Jugs(int a, int b, int c)
    {
        A = a;
        B = b;
        C = c;
        visited = new boolean[1001][1001];
        lastA = new int[1001][1001];
        lastB = new int[1001][1001];
        lastString = new String[1001][1001];
    }
    
    void print_sol(int a, int b)
    {
        if (a + b != 0) 
            print_sol(lastA[a][b], lastB[a][b]);
        System.out.print(lastString[a][b] + " [" + a + ", " + b + "]\n"); 
    }

    // Perform a depth first search from state (a, b) arriving from state (lasta, lastb) with the action given in text.
    void visit(int a, int b, int lasta, int lastb, String text)
    {

        if (visited[a][b])
        {
            return;            
        }
        
        visited[a][b] = true;         
        lastA[a][b] = lasta;             
        lastB[a][b] = lastb;
        lastString[a][b] = text;           
        
        visit(A, b, a, b, "Fill Jug 1");          
        visit(a, B, a, b, "Fill Jug 2");
        visit(0, b, a, b, "Empty Jug 1");
        visit(a, 0, a, b, "Empty Jug 2");
        int min = min(a, B-b);
        visit(a - min, b + min, a, b, "Pour Jug 1 -> Jug 2");
        min = min(b, A-a);
        visit(a + min, b - min, a, b, "Pour Jug 2 -> Jug 1");
    }
    
    public int min(int a, int b)
    {
        if (a < b)
        {
            return a;
        }
        else
        {
            return b;
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter A: ");
        int A = sc.nextInt();
        System.out.print("Enter B: ");
        int B = sc.nextInt();
        System.out.print("Enter C: ");
        int C = sc.nextInt();
        Jugs jugs = new Jugs(A,B,C);

        jugs.visit(0, 0, 0, 0, "Starting point");
        int numSolutions = 0;
        for (int i = 0; i <= C; i++)
        {
            if (jugs.visited[i][C - i] && numSolutions == 0) 
            {
                System.out.println("Yay! Found a solution.");
                jugs.print_sol(i, C - i);
                numSolutions++;
            }
        }
        if (numSolutions == 0)
        {
            System.out.println("Impossible!");
        }
    }
}
