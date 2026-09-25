class Two {
    public void add(int a, int b) {
        System.out.println(a + b);
    }
}

public class exp1 {
    public static void main(String[] args) {
        Two abc = new Two();
        abc.add(12, 8);
    }
}