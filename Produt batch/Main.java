public class Main {

    static int a = 1;

    void sms() {
        System.out.println(a);
        a++;
    }

    public static void main(String[] args) {

        Main u1 = new Main();
        Main u2 = new Main();
        Main u3 = new Main();

        u1.sms();
        u2.sms();
        u3.sms();
    }
}