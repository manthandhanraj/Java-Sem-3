import java.util.Scanner;

public class option2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Vehicle Type:");
        System.out.println("1. 2 Honda");
        System.out.println("2. 4 TVS");
        System.out.print("Enter your choice (1 or 2): ");

        int choice = scanner.nextInt();

        switch(choice) {
            case 1:
                System.out.println("You selected:  Honda");
                break;
            case 2:
                System.out.println("You selected:  TVS");
                break;
            default:
                System.out.println("Invalid choice! Please select 1 or 2.");
        }
    }
}