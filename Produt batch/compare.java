public class compare {
    public static void main(String[] args) {

        String s1 = "Apple";
        String s2 = "Banana";

        int result = s1.compareTo(s2);

        if (result == 0) {
            System.out.println("Strings are equal");
        } else if (result < 0) {
            System.out.println("s1 comes before s2");
        } else {
            System.out.println("s1 comes after s2");
        }
    }
}