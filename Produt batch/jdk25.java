public class jdk25 {

    public static void sms(int a, int b) {
        System.out.println(a + b);
    }

    public static void main(String[] args) {
        int c = 2;

        for (int a = 1; a <= 5; a++) {
            for (int b = 1; b <= a; b++) {
                if (a == c && b == c) {
                    sms(a, c);
                }
                System.out.print(b);
            }
            System.out.println();
        }
    }
}