import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class WordLadder {
    private static String start;
    private static String end;
    private static StringMap T;			// This map stores the dictionary of words.
    private static StringMap R;			// This map keeps track of all the words that are visited during breadth-first-search.
    // The key field is the word that is visited, and its value field can hold the predecessor pointer.
    private static Queue Q;					// A queue to perform the breadth-first-search.

    public static void main(String [] args) throws IOException {
        // Loading the dictionary of words into the StringMap T.
        T = new StringMap();
        File file = new File("dictionary4");
        Scanner f = new Scanner(file);
        while (f.hasNext()) {
            String word = f.nextLine();
            T.insert(word, "");
        }
        f.close();

        Scanner kb = new Scanner(System.in);
        System.out.print("Enter the start word: ");
        start = kb.nextLine();
        System.out.print("Enter the end word: ");
        end = kb.nextLine();
        boolean solution = false;
        StringBuffer cur = new StringBuffer(start);
        int distance = 0;
        String preCur = "";
        
        while (!solution)
        {
            for (int i = 0; i < 4; i++)
            {
                if (cur.charAt(i) != end.charAt(i))
                {
                    preCur = cur.toString();
                    cur.setCharAt(i, end.charAt(i));
                    if (T.find(cur.toString()) != null)
                    {
                        R.insert(cur.toString(), preCur);
                    }
                    else
                    {
                        cur.setCharAt(i, start.charAt(i));
                    }
                }
            }
        }
    }
}
