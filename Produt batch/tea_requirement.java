import java.util.Scanner;

public class tea_requirement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        float req, cons_M, cons_S, cons_TL;
        float avl_M, avl_S, avl_TL;

        System.out.print("Enter number of tea required: ");
        req = sc.nextFloat();

        System.out.print("Enter milk required for one tea: ");
        cons_M = sc.nextFloat();

        System.out.print("Enter sugar required for one tea: ");
        cons_S = sc.nextFloat();

        System.out.print("Enter tea leaves required for one tea: ");
        cons_TL = sc.nextFloat();

        System.out.print("Enter available milk: ");
        avl_M = sc.nextFloat();

        System.out.print("Enter available sugar: ");
        avl_S = sc.nextFloat();

        System.out.print("Enter available tea leaves: ");
        avl_TL = sc.nextFloat();
        float req_OF_M = req * cons_M;
        float req_OF_S = req * cons_S;
        float req_OF_TL = req * cons_TL;

        if (req_OF_M <= avl_M && req_OF_S <= avl_S && req_OF_TL <= avl_TL) {
            System.out.println("ok");
            System.out.println("req for " + req + " tea");
        } else {
            System.out.println("invalid input");
        }

        sc.close();
    }
}