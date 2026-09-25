import java.util.Scanner;

class Student {
    String name;
    int age;

    void getData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter user name: ");
        name = sc.nextLine();
        System.out.print("Enter age: ");
        age = sc.nextInt();
    }

    void showData() {
        System.out.println("\n--- User Details ---");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.getData();
        s1.showData();
    }
}