import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Tian on 11/7/14.
 */
public class HorspoolStringMatching {
    //Creates a list of unique chars that appear in the text
    public static List charList(char[] t) {
        List<Character> charList = new ArrayList<Character>();
        for (char c : t) {
            if (!charList.contains(c)) {
                charList.add(c);
            }
        }
        return charList;
    }
    //Creates a String of spaces for shifting
    public static String spaces(int i){
        String spaces = "";
        for(int j = 0; j < i; j++)
        {
            spaces = " " + spaces;
        }
        return spaces;
    }
    //Creates a shift table of given chars
    public static Hashtable shiftTable(char[] p, char[] t) {
        Hashtable<Character, Integer> table = new Hashtable<Character, Integer>();
        List<Character> charList;

        charList = charList(t);

        int m = p.length;

        for (char c : charList) {
            table.put(c, m);
        }
        for (int j = 0; j <= m - 2; j++) {
            char nextChar = p[j];
            table.put(nextChar, m - 1 - j);
        }
        System.out.println(table);
        return table;
    }
    //given string text and pattern, it does comparisons to see if the pattern exist in the text
    public static int stringMatch(String text, String pattern) {
        char[] t = text.toLowerCase().toCharArray();
        char[] p = pattern.toLowerCase().toCharArray();
        int n = t.length;
        int m = p.length;
        int i = m - 1; // index the right end of pattern/text alignment
        int comp = 0; //number of comparisons done

        Hashtable<Character, Integer> table = shiftTable(p, t);

        //catches empty string error and pattern.length > text.length error
        if (text.isEmpty() || pattern.isEmpty() || m > n) {
            System.out.println("-------Warning------\nInputs are either empty or wrong lengths");
            return -1;
        }

        while (i <= n - 1) {
            System.out.println("Right Most Position = " + i + "\n" + text + "\n" + pattern);
            int k = 0; // k is how many chars have matched
            comp++;

            while (k <= m - 1 && p[m - 1 - k] == t[i - k]) {
                k += 1;
            }
            if (k == m) {
                System.out.println("Pattern Found!\nNumber of Comparisons: " + comp);
                return i - m + 1;
            } else {
                String spaces = spaces(table.get(t[i]));
                pattern = spaces + pattern;
                i = i + table.get(t[i]); // shift according to rightmost char
            }
        }
        System.out.println("No Match Found");
        return -1; // if get here, no matches anywhere
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter text: ");
        String text = in.nextLine(); //text

        System.out.println("Enter pattern: ");
        String pattern = in.nextLine(); //pattern

        stringMatch(text, pattern);
    }
}
