import java.lang.Integer;
import java.util.Random;
public class Prog {

    public static void main(String [] args) {

        if (args.length != 2) {
            System.out.println("Please execute: java Prog <n> <p>");
            System.out.println("n is the input size, and p is the program number.");
            System.exit(0);
        }

        int n = Integer.parseInt(args[0]);
        int p = Integer.parseInt(args[1]);

        switch(p) {
            case 1: prog1(n);
            break;
            case 2: prog2(n);
            break;
            case 3: prog3(n);
            break;
            case 4: prog4(n);
            break;
            default: System.out.println("Invalid program number. Only 1-4.");
        }
    }

    private static void prog1(int n) {
        // TODO: Code to generate n keys that all get hashed to the same index using hash1.
        for (int i = 0; i < n; i++)
        {
            System.out.println(n * i);
        }
    }

    private static void prog2(int n) {
        // TODO: Code to generate n keys that all get hashed to the same index using hash2.
        for (int i = 0; i < n; i++)
        {
            System.out.println(i);
        }
    }

    private static void prog3(int n) {
        // TODO: Code to generate n keys that all get hashed to the same index using hash3.
        int numberOfOccurances = 0;
        int counter1 = 0;
        int counter2 = 0;
        int counter = 0;
        IntSet set1 = new IntSet();
        IntSet set2 = new IntSet();
        HashFunctions hash = new HashFunctions(n);

        while (numberOfOccurances != 100)
        {
            counter1++;
            numberOfOccurances = set1.insert(hash.hash3(counter1));
        }
        numberOfOccurances = 0;
        System.out.println(counter1);
        while (numberOfOccurances != 100)
        {
            if (hash.hash3(counter1) == hash.hash3(counter2));
            {
//                System.out.println(counter2);
                counter++;
            }
            numberOfOccurances = set2.insert(hash.hash3(counter2));
            counter2++;
        }
    }

    private static void prog4(int n) {
        // TODO: Code to generate n keys that all get hashed to the same index using hash4.
        int counter1 = 0;
        int counter2 = 0;
        Random rand = new Random();
        while (counter1 < n*n && counter2 < n)
        {
            rand.setSeed(counter1);
            int num = rand.nextInt(n);
            System.out.println(num);

            if (num == 1)
            {
                System.out.println(counter1);
                counter2++;
            }
            counter1++;
        }
    }
}
