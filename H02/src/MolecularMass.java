import java.util.Scanner;
import java.util.regex.Pattern;

public class MolecularMass
{
    public static final Pattern UNSIGNED_DOUBLE =
        Pattern.compile("((\\d+\\.?\\d*)|(\\.\\d+))([Ee][-+]?\\d+)?.*?");
    public static final Pattern CHARACTER = Pattern.compile("\\S.*?");
    
    /**
     * converts an organic molecule to mathematical expression.
     * @param formula
     * @return
     */
    public static String expressionConversion(String formula)
    {
        Scanner sc = new Scanner(formula);
        String cur;
        char c;
        String str = "";
        int counter = 0;
        
        while (sc.hasNext())
        {
            if (sc.hasNext(UNSIGNED_DOUBLE))
            {
                cur = sc.findInLine(UNSIGNED_DOUBLE);
                str += "* " + cur + " ";
            }
            else if (sc.hasNext(CHARACTER))
            {
                cur = sc.findInLine(CHARACTER);
                c = cur.charAt(0);
                if (counter == 0)
                {
                    switch (c)
                    {
                        case 'H':
                            str += "1 ";
                            break;
                        case 'C':
                            str += "12 ";
                            break;
                        case 'O':
                            str += "16 ";
                            break;
                        case '(':
                            str += "(";
                            counter = -1;
                            break;
                        case ')':
                            str += ")";
                            break;
                        default:
                            break;
                    }
                }
                else
                {
                    switch (c)
                    {
                        case 'H':
                            str += "+ 1 ";
                            break;
                        case 'C':
                            str += "+ 12 ";
                            break;
                        case 'O':
                            str += "+ 16 ";
                            break;
                        case '(':
                            str += "+ (";
                            counter = -1;
                            break;
                        case ')':
                            str += ")";
                            break;
                        default:
                            break;
                    }
                }
                counter++;
            }
        }
        sc.close();
        return str;
    }

    /**
     * Takes an infix expression and converts it to postfix.
     * 
     * @param expression
     *            The infix expression.
     * @return the postfix expression.
     */
    public static String toPostfix(String expression)
    {
        Scanner sc = new Scanner(expression);
        String next;
        char symbol;
        String postfixExpression = "";
        IntStack stk = new IntStack();

        while (sc.hasNext())
        {
            if (sc.hasNext(UNSIGNED_DOUBLE))
            {
                next = sc.findInLine(UNSIGNED_DOUBLE);
                postfixExpression += next + " ";
            }
            else
            {
                next = sc.findInLine(CHARACTER);
                symbol = next.charAt(0);
                if (symbol == '(')
                {
                    stk.push(3);
                }
                else if (symbol == '+' || symbol == '*')
                {
                    while (!stk.isEmpty() && stk.peek() != 3 
                        && symbol == '+')
                    {
                        int num = stk.pop();
                        if (num == 1)
                        {
                            postfixExpression += "+ ";
                        }
                        else if (num == 2)
                        {
                            postfixExpression += "* ";
                        }
                    }
                    if (symbol == '+')
                    {
                        stk.push(1);
                    }
                    else if (symbol == '*')
                    {
                        stk.push(2);
                    }
                }
                else if (symbol == ')')
                {
                    while (!stk.isEmpty() && stk.peek() != 3)
                    {
                        int num = stk.pop();
                        if (num == 1)
                        {
                            postfixExpression += "+ ";
                        }
                        else if (num == 2)
                        {
                            postfixExpression += "* ";
                        }
                    }
                    stk.pop();
                }
                else
                {
                    throw new IllegalArgumentException();
                }
            }
        }
        while (!stk.isEmpty())
        {
            int num = stk.pop();
            if (num == 1)
            {
                postfixExpression += "+ ";
            }
            else if (num == 2)
            {
                postfixExpression += "* ";
            }
        }
        sc.close();
        return postfixExpression;
    }
    
    /**
     * Evaluates a postfix expression and returns the result.
     * 
     * @param postfixExpression
     *            The postfix expression.
     * @return The result of the expression.
     */
    public static int evaluate(String postfixExpression)
    {
        Scanner sc = new Scanner(postfixExpression);
        String next;
        char operator;
        int answer;
        IntStack stk = new IntStack();

        while (sc.hasNext())
        {
            if (sc.hasNext(UNSIGNED_DOUBLE))
            {
                next = sc.findInLine(UNSIGNED_DOUBLE);
                stk.push(Integer.parseInt(next));
            }
            else
            {
                next = sc.findInLine(CHARACTER);
                operator = next.charAt(0);
                int a = stk.pop();
                int b = stk.pop();
                if (operator == '+')
                {
                    stk.push(b + a);
                }
                else if (operator == '*')
                {
                    stk.push(b * a);
                }
            }
        }
        sc.close();
        answer = stk.peek();
        return answer;
    }

    public static int findMass(String formula)
    {
        String expression = expressionConversion(formula);
        expression = toPostfix(expression);
        return(evaluate(expression));
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Molecule: ");
        String formula = sc.nextLine();
        System.out.println("The Molecular Mass of " + formula + " is " + findMass(formula));
    }
}
