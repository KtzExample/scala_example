package variance;

/**
 * Created by ktz on 16. 9. 3.
 */
public class JavaList {
    public static void main(String args[]) {
        String[] strings = { "abc" };
        Object[] objects = strings;
        objects[0] = 1;
    }
}