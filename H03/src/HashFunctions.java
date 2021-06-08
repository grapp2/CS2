import java.util.Random;

public class HashFunctions {

	public static int C;		// The largest possible key that can be hashed.
	public static int size;	// The size of the hash table.
	public static Random rand;

	public HashFunctions(int s) {
		C = s * s;
		size = s;
		rand = new Random();
	}

	public static int hash1(int k) {
		return k % size;
	}

	public static int hash2(int k) {
		return (int) (k * ((double) size / C));
	}

	public static int hash3(int k) {
		return (int) (((2971L * k + 101923L) % 128189L)  % (long) size);
	}

	public static int hash4(int k) {
		rand.setSeed(k);
		return rand.nextInt(size);
	}

	public static void main(String[] args)
	{
	    HashFunctions a = new HashFunctions(5);
	    for (int i = 0; i < a.C; i++)
	    {
	        System.out.println(a.hash4(i));
	    }
	}
}
