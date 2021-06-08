import java.io.File;
import java.util.Scanner;
public class Closest
{
    private static final int b = 1000;
    private Node[][] grid;


    public Closest()
    {
        grid = new Node[b][b];
    }

    public void findClosestInSet(String file)
    {
        File f = new File(file);
        double minDistance = 100;
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext())
            {
                double x = Double.parseDouble(sc.next());
                double y = Double.parseDouble(sc.next());
                if (grid[hash(x)][hash(y)] != null)
                {
                    Node temp = new Node(x, y, null);
                    temp.setNext(grid[hash(x)][hash(y)]);
                    grid[hash(x)][hash(y)] = temp;
                }
                else
                {
                    grid[hash(x)][hash(y)] = new Node(x, y, null);
                }
            }
            for (int i = 0; i < b; i++)
            {
                for (int j = 0; j < b; j++)
                {
                    Node cur = grid[i][j];
                    while (cur != null)
                    {
                        double x = grid[i][j].getX();
                        double y = grid[i][j].getY();
                        double distance = comparePoints(x,y);
                        if (distance < minDistance)
                        {
                            minDistance = distance;
                        }
                        cur = cur.getNext();
                    }
                }
            }
            sc.close();
            System.out.println(minDistance);
        }
        catch (Exception e) {
            System.out.println("Cannot open file " + f.getAbsolutePath());
            System.out.println(e);
        }
    }

    public double comparePoints(double x1, double y1)
    {
        double minDistance = 100;

        if (hash(x1) != 0 && hash(x1) != b-1 && hash(y1) != 0 && hash(y1) != b-1)
        {
            for (int i = -1; i <= 1; i++)
            {
                for (int j = -1; j <= 1; j++)
                {
                    Node cur = grid[hash(x1) + i] [hash(y1) + j];
                    while (cur != null)
                    {
                        double x2 = cur.getX();
                        double y2 = cur.getY();
                        double distance = 100;
                        if (x2 != x1 && y2 != y1)
                        {
                            distance = Math.sqrt(Math.pow(x1 - x2,2) +
                                Math.pow(y1 - y2, 2));
                        }
                        if (distance < minDistance)
                        {
                            minDistance = distance;
                        }
                        cur = cur.getNext();
                    }
                }
            }
        }
        else if (hash(x1) == 0 && hash(y1) == 0)
        {
            for (int i = 0; i <= 1; i++)
            {
                for (int j = 0; j <= 1; j++)
                {
                    Node cur = grid[hash(x1) + i] [hash(y1) + j];
                    while (cur != null)
                    {
                        double x2 = cur.getX();
                        double y2 = cur.getY();

                        double distance = 100;
                        if (x2 != x1 && y2 != y1)
                        {
                            distance = Math.sqrt(Math.pow(x1 - x2,2) +
                                Math.pow(y1 - y2, 2));
                        }
                        if (distance < minDistance)
                        {
                            minDistance = distance;
                        }
                        cur = cur.getNext();
                    }
                }
            }

        }
        else if (hash(x1) == b-1 && hash(y1) == b-1)
        {

            for (int i = -1; i <= 0; i++)
            {
                for (int j = -1; j <= 0; j++)
                {
                    Node cur = grid[hash(x1) + i] [hash(y1) + j];
                    while (cur != null)
                    {
                        double x2 = cur.getX();
                        double y2 = cur.getY();

                        double distance = 100;
                        if (x2 != x1 && y2 != y1)
                        {
                            distance = Math.sqrt(Math.pow(x1 - x2,2) +
                                Math.pow(y1 - y2, 2));
                        }
                        if (distance < minDistance)
                        {
                            minDistance = distance;
                        }
                        cur = cur.getNext();
                    }
                }
            }

        }
        else if (hash(x1) == 0 && hash(y1) == b-1)
        {

            for (int i = 0; i <= 1; i++)
            {
                for (int j = -1; j <= 0; j++)
                {
                    Node cur = grid[hash(x1) + i] [hash(y1) + j];
                    while (cur != null)
                    {
                        double x2 = cur.getX();
                        double y2 = cur.getY();

                        double distance = 100;
                        if (x2 != x1 && y2 != y1)
                        {
                            distance = Math.sqrt(Math.pow(x1 - x2,2) +
                                Math.pow(y1 - y2, 2));
                        }
                        if (distance < minDistance)
                        {
                            minDistance = distance;
                        }
                        cur = cur.getNext();
                    }
                }
            }

        }
        else if (hash(x1) == b-1 && hash(y1) == 0)
        {

            for (int i = -1; i <= 0; i++)
            {
                for (int j = 0; j <= 1; j++)
                {
                    Node cur = grid[hash(x1) + i] [hash(y1) + j];
                    while (cur != null)
                    {
                        double x2 = cur.getX();
                        double y2 = cur.getY();

                        double distance = 100;
                        if (x2 != x1 && y2 != y1)
                        {
                            distance = Math.sqrt(Math.pow(x1 - x2,2) +
                                Math.pow(y1 - y2, 2));
                        }
                        if (distance < minDistance)
                        {
                            minDistance = distance;
                        }
                        cur = cur.getNext();
                    }
                }
            }

        }
        else if (hash(y1) == 0 && hash(x1) != 0 && hash(x1) != b-1)
        {

            for (int i = -1; i <= 1; i++)
            {
                for (int j = 0; j <= 1; j++)
                {
                    Node cur = grid[hash(x1) + i] [hash(y1) + j];
                    while (cur != null)
                    {
                        double x2 = cur.getX();
                        double y2 = cur.getY();

                        double distance = 100;
                        if (x2 != x1 && y2 != y1)
                        {
                            distance = Math.sqrt(Math.pow(x1 - x2,2) +
                                Math.pow(y1 - y2, 2));
                        }
                        if (distance < minDistance)
                        {
                            minDistance = distance;
                        }
                        cur = cur.getNext();
                    }
                }
            }
        }
        else if (hash(y1) == b-1 && hash(x1) != 0 && hash(x1) != b-1)
        {

            for (int i = -1; i <= 1; i++)
            {
                for (int j = -1; j <= 0; j++)
                {
                    Node cur = grid[hash(x1) + i] [hash(y1) + j];
                    while (cur != null)
                    {
                        double x2 = cur.getX();
                        double y2 = cur.getY();

                        double distance = 100;
                        if (x2 != x1 && y2 != y1)
                        {
                            distance = Math.sqrt(Math.pow(x1 - x2,2) +
                                Math.pow(y1 - y2, 2));
                        }
                        if (distance < minDistance)
                        {
                            minDistance = distance;
                        }
                        cur = cur.getNext();
                    }
                }
            }
        }
        else if (hash(x1) == 0 && hash(y1) != 0 && hash(y1) != b-1)
        {
            for (int i = 0; i <= 1; i++)
            {
                for (int j = -1; j <= 1; j++)
                {
                    Node cur = grid[hash(x1) + i] [hash(y1) + j];
                    while (cur != null)
                    {
                        double x2 = cur.getX();
                        double y2 = cur.getY();

                        double distance = 100;
                        if (x2 != x1 && y2 != y1)
                        {
                            distance = Math.sqrt(Math.pow(x1 - x2,2) +
                                Math.pow(y1 - y2, 2));
                        }
                        if (distance < minDistance)
                        {
                            minDistance = distance;
                        }
                        cur = cur.getNext();
                    }
                }
            }
        }
        else if (hash(x1) == b-1 && hash(y1) != 0 && hash(y1) != b-1)
        {
            for (int i = -1; i <= 0; i++)
            {
                for (int j = -1; j <= 1; j++)
                {
                    Node cur = grid[hash(x1) + i] [hash(y1) + j];
                    while (cur != null)
                    {
                        double x2 = cur.getX();
                        double y2 = cur.getY();

                        double distance = 100;
                        if (x2 != x1 && y2 != y1)
                        {
                            distance = Math.sqrt(Math.pow(x1 - x2,2) +
                                Math.pow(y1 - y2, 2));
                        }
                        if (distance < minDistance)
                        {
                            minDistance = distance;
                        }
                        cur = cur.getNext();
                    }
                }
            }
        }
        return minDistance;
    }

    public int hash(double num)
    {
        int index = (int) ((num) * b);
        return index;
    }

    public static void main(String[] args)
    {
        Closest a = new Closest();
        a.findClosestInSet("points.txt");
    }
}
