import java.util.Scanner;

public class vegetable_selling {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = 3;
        int id[] = new int[n];
        String name[] = new String[n];
        int quantity[] = new int[n];
        float prize[] = new float[n];

        System.out.println("Enter your data (Seller)");

        for (int i = 0; i < n; i++) {

            id[i] = i + 1;

            System.out.print("Enter vegetable name : ");
            name[i] = scanner.next();

            while (true) {
                System.out.print("Enter quantity       : ");
                if (scanner.hasNextInt()) {
                    quantity[i] = scanner.nextInt();
                    if (quantity[i] > 0) break;
                    System.out.println("Quantity must be greater than 0!");
                } else {
                    System.out.println("Enter a number only!");
                    scanner.next();
                }
            }

            while (true) {
                System.out.print("Enter prize          : ");
                if (scanner.hasNextFloat()) {
                    prize[i] = scanner.nextFloat();
                    if (prize[i] > 0) break;
                    System.out.println("Price must be greater than 0!");
                } else {
                    System.out.println("Enter a number only!");
                    scanner.next();
                }
            }
        }

        String more;
        int customerNo = 1;

        do {
            boolean soldOut = true;
            for (int i = 0; i < n; i++) {
                if (quantity[i] > 0) soldOut = false;
            }
            if (soldOut) {
                System.out.println("\nAll vegetables sold out! Shop closed.");
                break;
            }

            System.out.println("\n===== CUSTOMER " + customerNo + " =====");

            showItems(id, name, quantity, prize, n);

            System.out.print("\nHow many items you want to buy : ");
            int cquan = readInt(scanner, 1, n);

            float bill = 0.0f;
            int billId[] = new int[n];
            int billQty[] = new int[n];
            float billCost[] = new float[n];
            int count = 0;

            for (int i = 0; i < cquan; i++) {

                int cid;
                while (true) {
                    System.out.print("\nEnter item id : ");
                    cid = readInt(scanner, 1, n);
                    if (quantity[cid - 1] == 0) {
                        System.out.println(name[cid - 1] + " is OUT OF STOCK! Enter another id.");
                    } else {
                        break;
                    }
                }

                int cquanBuy;
                while (true) {
                    System.out.print("Enter quantity (available " + quantity[cid - 1] + ") : ");
                    cquanBuy = readInt(scanner, 1, 100000);
                    if (cquanBuy > quantity[cid - 1]) {
                        System.out.println("Not enough stock! Maximum " + quantity[cid - 1] + " allowed.");
                    } else {
                        break;
                    }
                }

                float cost = cquanBuy * prize[cid - 1];
                bill = bill + cost;

                quantity[cid - 1] = quantity[cid - 1] - cquanBuy;

                billId[count] = cid;
                billQty[count] = cquanBuy;
                billCost[count] = cost;
                count++;
            }

            System.out.println("\n----------- BILL -----------");
            System.out.println("Id\tName\t\tQty\tRate\tCost");
            for (int i = 0; i < count; i++) {
                int idx = billId[i] - 1;
                System.out.println(billId[i] + "\t" + name[idx] + "\t\t" + billQty[i]
                        + "\t" + prize[idx] + "\t" + billCost[i]);
            }
            System.out.println("----------------------------");
            System.out.println("Final bill = " + bill);

            System.out.println("\nRemaining items for next customer:");
            showItems(id, name, quantity, prize, n);

            System.out.print("\nNext customer? (Y/N) : ");
            more = scanner.next();
            customerNo++;

        } while (more.equalsIgnoreCase("Y"));

        System.out.println("\nThank you! Shop closed.");
        scanner.close();
    }

    static void showItems(int id[], String name[], int quantity[], float prize[], int n) {
        System.out.println("Id\tName\t\tQuantity\tPrize");
        for (int i = 0; i < n; i++) {
            if (quantity[i] == 0) {
                System.out.println(id[i] + "\t" + name[i] + "\t\tSOLD OUT\t" + prize[i]);
            } else {
                System.out.println(id[i] + "\t" + name[i] + "\t\t" + quantity[i] + "\t\t" + prize[i]);
            }
        }
    }

    static int readInt(Scanner scanner, int min, int max) {
        while (true) {
            if (scanner.hasNextInt()) {
                int x = scanner.nextInt();
                if (x >= min && x <= max) return x;
                System.out.print("Enter between " + min + " and " + max + " : ");
            } else {
                System.out.print("Enter a number only : ");
                scanner.next();
            }
        }
    }
}