class Two {
    public void subtract(int a, int b) {
        System.out.println(a - b);
    }
}

public class exp2 {
    public static void main(String[] args) {
        Two abc = new Two();
        abc.subtract(20, 8);
    }
}
