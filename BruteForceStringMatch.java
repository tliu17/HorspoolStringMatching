import java.util.Scanner;
/**
 * Created by Tian on 10/1/14.
 */
public class BruteForceStringMatch {
    public static void main(String []args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter input text: ");
        String text = in.nextLine(); //text

        System.out.println("Enter pattern string: ");
        String pattern = in.nextLine(); //pattern

        StringMatch(text, pattern);
    }

    public static int StringMatch(String text, String pattern) {
        if (text.isEmpty() || pattern.isEmpty()) {
            System.out.println("Warning!!!Empty input detected!!!!");
            return -1;
        }
        //input strings to lower case then to charArray
        char[] t = text.toLowerCase().toCharArray();
        char[] p = pattern.toLowerCase().toCharArray();

        //original string output
        String m = "^   Match!";
        String nm = "^   No Match!";

        int comp = 0;
        for (int i = 0; i < t.length - p.length; i++) {
            System.out.println("Pos = " + i + "\n" + text + "\n" + pattern);
            //shifts string pattern to the right by " "
            pattern = " " + pattern;

            int j = 0;

            //make copies of the string outputs
            String m2 = m;
            String nm2 = nm;

            //checks all characters in pattern to see if they match with the given text
            while (j < p.length) {
                comp++;
                if (p[j] == t[j + i]) {
                    System.out.println(m2);
                    //shifts the copy string output to the right by " " for every matched character
                    m2 = " " + m2;
                    nm2 = " " + nm2;
                } else {
                    System.out.println(nm2);
                    //shifts the original string output by to the right by " " for every unmatched character
                    m = " " + m;
                    nm = " " + nm;
                    //stop checking when unmatched character is found
                    break;
                }
                j++;
            }
            if (j == p.length) {
                System.out.println("Number of Comparisons: " + comp);
                return i;
            }
        }
        return -1;
    }
}
