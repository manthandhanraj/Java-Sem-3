import java.util.*;
public class StockMaterial {
   // ==============================
   // Available Raw Materials
   // ==============================
   static float Milk = 20.0f;
   static float Sugar = 20.0f;
   float TeaLeaves = 20.0f;
   float CoffeePowder = 20.0f;
   // ==============================
   // Tea Raw Material Percentage
   // ==============================
   float Tea_RMP_milk = 0.5f;
   float Tea_RMP_Sugar = 0.3f;
   float Tea_RMP_TeaLeaves = 0.2f;
   // ==============================
   // Coffee Raw Material Percentage
   // ==============================
   float Coff_RMP_milk = 0.5f;
   float Coff_RMP_Sugar = 0.3f;
   float Coff_RMP_CoffeePowder = 0.2f;
   // ==============================
   // Create Product
   // ==============================
   public void createProduct(String pName) {
       Scanner s = new Scanner(System.in);
       // ====================================
       // CREATE TEA
       // ====================================
       if (pName.equalsIgnoreCase("tea")) {
           System.out.println("User selected Tea");
           System.out.println("Please enter the Qty of Tea:");
           float req_tea = s.nextFloat();
           // Calculate required raw materials
        
           float Req_milk =
                   req_tea * Tea_RMP_milk;
           float Req_Sugar =
                   req_tea * Tea_RMP_Sugar;
           float Req_TeaLeaves =
                   req_tea * Tea_RMP_TeaLeaves;
           // Check stock
           if (Req_milk <= Milk &&
               Req_Sugar <= Sugar &&
               Req_TeaLeaves <= TeaLeaves) {
               System.out.println();
               System.out.println(
                       "For creating Tea we require following raw materials"
               );
               System.out.println("Required Milk       : " + Req_milk);
               System.out.println("Required Sugar      : " + Req_Sugar);
               System.out.println("Required Tea Leaves : " + Req_TeaLeaves);
               // ============================
               // Deduct stock
               // ============================
               Milk = Milk - Req_milk;
               Sugar = Sugar - Req_Sugar;
               TeaLeaves = TeaLeaves - Req_TeaLeaves;
               System.out.println();
               System.out.println(
                       "Tea created successfully!"
               );
               // ============================
               // Remaining stock
               // ============================
               System.out.println();
               System.out.println(
                       "After creating Tea, available materials:"
               );
               System.out.println(
                       "Available Milk       : " + Milk
               );
               System.out.println(
                       "Available Sugar      : " + Sugar
               );
               System.out.println(
                       "Available Tea Leaves : " + TeaLeaves
               );
           } else {
               System.out.println(
                       "Data insufficient for creating Tea"
               );
           }
       // ====================================
       // CREATE COFFEE
       // ====================================
       } else if (pName.equalsIgnoreCase("coffee")) {
           System.out.println("User selected Coffee");
           System.out.println(
                   "Please enter the Qty of Coffee:"
           );
           float req_coffee = s.nextFloat();
           // Calculate required raw materials
           float Req_milk =
                   req_coffee * Coff_RMP_milk;
           float Req_Sugar =
                   req_coffee * Coff_RMP_Sugar;
           float Req_CoffeePowder =
                   req_coffee * Coff_RMP_CoffeePowder;
           // Check stock
           if (Req_milk <= Milk &&
               Req_Sugar <= Sugar &&
               Req_CoffeePowder <= CoffeePowder) {
               System.out.println();
               System.out.println(
                       "For creating Coffee we require following raw materials"
               );
               System.out.println(
                       "Required Milk          : " + Req_milk
               );
               System.out.println(
                       "Required Sugar         : " + Req_Sugar
               );
               System.out.println(
                       "Required Coffee Powder : "
                               + Req_CoffeePowder
               );
               // ============================
               // Deduct stock
               // ============================
               Milk = Milk - Req_milk;
               Sugar = Sugar - Req_Sugar;
               CoffeePowder =
                       CoffeePowder - Req_CoffeePowder;
               System.out.println();
               System.out.println(
                       "Coffee created successfully!"
               );
               // ============================
               // Remaining stock
               // ============================
               System.out.println();
               System.out.println(
                       "After creating Coffee, available materials:"
               );
               System.out.println(
                       "Available Milk : " + Milk
               );
               System.out.println(
                       "Available Sugar : " + Sugar
               );
               System.out.println(
                       "Available Coffee Powder : "
                               + CoffeePowder
               );
           } else {
               System.out.println(
                       "Data insufficient for creating Coffee"
               );
           }
       } else {
           System.out.println(
                   "Invalid name for creating product"
           );
       }
   }
   // ====================================
   // MAIN METHOD
   // ====================================
   public static void main(String[] args) {
       System.out.println(
               "Welcome to my Stock Materials Project"
       );
       Scanner s = new Scanner(System.in);
       // IMPORTANT:
       // Only ONE StockMaterial object
       StockMaterial stock = new StockMaterial();
       boolean yn = true;
       while (yn) {
           System.out.println();
           System.out.println(
                   "Please enter the product name"
           );
           System.out.println(
                   "tea / coffee"
           );
           String pName = s.next();
           // Same stock object is used
           // for both Tea and Coffee
           stock.createProduct(pName);
           System.out.println();
           System.out.println(
                   "Do you want to create more product?"
           );
           System.out.println(
                   "Press yes/no"
           );
           String y_n = s.next();
           if (y_n.equalsIgnoreCase("no")) {
               yn = false;
           }
       }
       System.out.println();
       System.out.println("Ok Bye");
       s.close();
   }
}
