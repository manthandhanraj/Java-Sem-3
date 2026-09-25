import java.util.*;


public class VegetableSellingApp {

   
    static final int MAX_VEGETABLES = 20;
    static final double MAX_STOCK_QTY = 1000;
    static final double MAX_RATE = 10000;

    static final Scanner sc = new Scanner(System.in);
    static final Map<String, Vegetable> stock = new LinkedHashMap<>();

    enum Unit { KG, DOZEN }

    static class Vegetable {
        String id;
        String description;
        double quantity;
        double rate;
        Unit unit;

        Vegetable(String id, String description, double quantity, double rate, Unit unit) {
            this.id = id;
            this.description = description;
            this.quantity = quantity;
            this.rate = rate;
            this.unit = unit;
        }
    }

    static class BillItem {
        Vegetable veg;
        double qty;

        BillItem(Vegetable veg, double qty) {
            this.veg = veg;
            this.qty = qty;
        }

        double cost() {
            return round2(qty * veg.rate);
        }
    }

    
    public static void main(String[] args) {
        System.out.println("System selling application by Manthan");

        module1EnterAvailability();

        int customerNo = 1;
        while (true) {
            if (allSoldOut()) {
                System.out.println("\nAll vegetables are SOLD OUT. Shop closed.");
                break;
            }

            System.out.println("\n==================== CUSTOMER #" + customerNo + " ====================");
            displayStock("AVAILABLE VEGETABLES");

            List<BillItem> cart = module2CustomerPurchase();

            if (cart.isEmpty()) {
                System.out.println("\nNo items purchased by Customer #" + customerNo + ".");
            } else {
                module3GenerateBill(cart, customerNo);
            }

            System.out.println("\n---- Remaining items for next customer ----");
            displayStock("REMAINING STOCK");

            if (allSoldOut()) {
                System.out.println("\nAll vegetables are SOLD OUT. Shop closed.");
                break;
            }
            if (!readYesNo("\nIs there a next customer? (Y/N): ")) {
                System.out.println("\nThank you! Shop closed.");
                break;
            }
            customerNo++;
        }
        sc.close();
    }

    
    static void module1EnterAvailability() {
        System.out.println("\n---------- MODULE 1 : ENTER VEGETABLES AVAILABILITY ----------");
        int n = readInt("Enter number of vegetables (1-" + MAX_VEGETABLES + "): ", 1, MAX_VEGETABLES);

        for (int i = 1; i <= n; i++) {
            System.out.println("\n--- Vegetable " + i + " of " + n + " ---");

            String id = readNewVegetableId();
            Unit unit = readUnit();
            String desc = readVegetableName();
            double qty = readQuantity("Enter total quantity available for sale (in " + unitLabel(unit)
                    + ", max " + fmt(MAX_STOCK_QTY) + "): ", unit, MAX_STOCK_QTY);
            double rate = readDouble("Enter rate per " + unitLabel(unit) + " (Rs, 1-" + fmt(MAX_RATE) + "): ",
                    1, MAX_RATE);

            stock.put(id, new Vegetable(id, desc, qty, rate, unit));
            System.out.println("Vegetable " + id + " added successfully.");
        }
        System.out.println("\nAll vegetables entered by seller.");
    }

    
    static String readNewVegetableId() {
        while (true) {
            String id = readLine("Enter Vegetable Id (alphanumeric, 1-10 chars, e.g. V101): ").toUpperCase();
            if (!id.matches("[A-Z0-9]{1,10}")) {
                System.out.println("Invalid Id! Use only letters/digits, 1 to 10 characters.");
            } else if (stock.containsKey(id)) {
                System.out.println("Duplicate Id! Vegetable Id " + id + " already exists.");
            } else {
                return id;
            }
        }
    }

    
    static Unit readUnit() {
        while (true) {
            String u = readLine("Enter category / unit of rate (KG or DOZEN): ").toUpperCase();
            if (u.equals("KG")) return Unit.KG;
            if (u.equals("DOZEN")) return Unit.DOZEN;
            System.out.println("Invalid category! Enter only KG or DOZEN.");
        }
    }

    
    static String readVegetableName() {
        while (true) {
            String name = readLine("Enter Vegetable Description/Name (letters only, 2-30 chars): ")
                    .replaceAll("\\s+", " ");
            if (!name.matches("[A-Za-z][A-Za-z ]{1,29}")) {
                System.out.println("Invalid name! Only alphabets and spaces allowed (2-30 chars).");
                continue;
            }
            boolean duplicate = false;
            for (Vegetable v : stock.values()) {
                if (v.description.equalsIgnoreCase(name)) {
                    duplicate = true;
                    break;
                }
            }
            if (duplicate) {
                System.out.println("Vegetable '" + name + "' already exists. Enter a different vegetable.");
                continue;
            }
            return capitalize(name);
        }
    }

   
    static List<BillItem> module2CustomerPurchase() {
        System.out.println("\n---------- MODULE 2 : CUSTOMER PURCHASE ----------");
        Map<String, BillItem> cart = new LinkedHashMap<>();

        while (true) {
            if (allSoldOut()) {
                System.out.println("Stock finished. Moving to billing.");
                break;
            }
            String id = readLine("\nEnter Vegetable Id to buy (or DONE to finish shopping): ").toUpperCase();

            if (id.equals("DONE")) break;

            // Validation of Vegetable Id (customer side)
            if (!id.matches("[A-Z0-9]{1,10}")) {
                System.out.println("Invalid Id format!");
                continue;
            }
            Vegetable v = stock.get(id);
            if (v == null) {
                System.out.println("Vegetable Id " + id + " does not exist! Check the list above.");
                continue;
            }
            if (v.quantity <= 0) {
                System.out.println(v.description + " is OUT OF STOCK.");
                continue;
            }

            // Validation of customer purchase quantity
            double qty = readQuantity("Enter quantity of " + v.description + " (in " + unitLabel(v.unit)
                    + ", available " + fmt(v.quantity) + "): ", v.unit, v.quantity);

            v.quantity = round2(v.quantity - qty); // update remaining stock

            BillItem item = cart.get(id);
            if (item == null) {
                cart.put(id, new BillItem(v, qty));
            } else {
                item.qty = round2(item.qty + qty);
            }
            System.out.println("Added " + fmt(qty) + " " + unitLabel(v.unit) + " of " + v.description
                    + " to cart. Remaining stock: " + fmt(v.quantity));
        }
        return new ArrayList<>(cart.values());
    }

    
    static void module3GenerateBill(List<BillItem> cart, int customerNo) {
        System.out.println("\n---------- MODULE 3 : CUSTOMER BILL (Customer #" + customerNo + ") ----------");
        String line = "+------------+------------+----------------------+--------------+--------------+";
        System.out.println(line);
        System.out.printf("| %-10s | %-10s | %-20s | %-12s | %-12s |%n",
                "Veg Id", "Qty Bought", "Description", "Rate (Rs)", "Cost (Rs)");
        System.out.println(line);

        double total = 0;
        for (BillItem b : cart) {
            double cost = b.cost();
            total += cost;
            System.out.printf("| %-10s | %-10s | %-20s | %-12s | %12.2f |%n",
                    b.veg.id,
                    fmt(b.qty) + " " + shortUnit(b.veg.unit),
                    b.veg.description,
                    fmt(b.veg.rate) + "/" + shortUnit(b.veg.unit),
                    cost);
        }
        System.out.println(line);
        System.out.printf("| %-60s | %12.2f |%n", "TOTAL BILL CUSTOMER NEEDS TO PAY", round2(total));
        System.out.println(line);
    }

   
    static void displayStock(String title) {
        System.out.println("\n" + title);
        String line = "+------------+----------------------+----------------------+--------------+";
        System.out.println(line);
        System.out.printf("| %-10s | %-20s | %-20s | %-12s |%n",
                "Veg Id", "Qty Available", "Description", "Rate (Rs)");
        System.out.println(line);
        for (Vegetable v : stock.values()) {
            String qty = v.quantity <= 0 ? "OUT OF STOCK" : fmt(v.quantity) + " " + unitLabel(v.unit);
            System.out.printf("| %-10s | %-20s | %-20s | %-12s |%n",
                    v.id, qty, v.description, fmt(v.rate) + "/" + shortUnit(v.unit));
        }
        System.out.println(line);
    }

    
    static String readLine(String prompt) {
        System.out.print(prompt);
        if (!sc.hasNextLine()) {
            System.out.println("\nInput ended. Exiting.");
            System.exit(0);
        }
        return sc.nextLine().trim();
    }

    static int readInt(String prompt, int min, int max) {
        while (true) {
            String s = readLine(prompt);
            try {
                int val = Integer.parseInt(s);
                if (val < min || val > max) {
                    System.out.println("Value must be between " + min + " and " + max + ".");
                } else {
                    return val;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter a whole number.");
            }
        }
    }

    
    static double readDouble(String prompt, double min, double max) {
        while (true) {
            String s = readLine(prompt);
            try {
                double val = Double.parseDouble(s);
                if (Double.isNaN(val) || Double.isInfinite(val)) throw new NumberFormatException();
                if (val < min || val > max) {
                    System.out.println("Value must be between " + fmt(min) + " and " + fmt(max) + ".");
                } else if (!hasMax2Decimals(s)) {
                    System.out.println("Maximum 2 decimal places allowed.");
                } else {
                    return round2(val);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter a valid number.");
            }
        }
    }

    
    static double readQuantity(String prompt, Unit unit, double max) {
        while (true) {
            String s = readLine(prompt);
            try {
                double val = Double.parseDouble(s);
                if (Double.isNaN(val) || Double.isInfinite(val)) throw new NumberFormatException();
                if (val <= 0) {
                    System.out.println("Quantity must be greater than 0.");
                } else if (val > max) {
                    System.out.println("Quantity exceeds limit! Maximum allowed: " + fmt(max));
                } else if (unit == Unit.DOZEN && val != Math.floor(val)) {
                    System.out.println("Dozen items must be whole numbers (1, 2, 3 ...).");
                } else if (unit == Unit.KG && val < 0.25) {
                    System.out.println("Minimum quantity is 0.25 kg.");
                } else if (!hasMax2Decimals(s)) {
                    System.out.println("Maximum 2 decimal places allowed.");
                } else {
                    return round2(val);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter a valid number.");
            }
        }
    }

    static boolean readYesNo(String prompt) {
        while (true) {
            String s = readLine(prompt).toUpperCase();
            if (s.equals("Y") || s.equals("YES")) return true;
            if (s.equals("N") || s.equals("NO")) return false;
            System.out.println("Please enter Y or N.");
        }
    }

    
    static boolean allSoldOut() {
        for (Vegetable v : stock.values()) {
            if (v.quantity > 0) return false;
        }
        return true;
    }

    static boolean hasMax2Decimals(String s) {
        int dot = s.indexOf('.');
        return dot == -1 || s.length() - dot - 1 <= 2;
    }

    static double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    static String fmt(double v) {
        if (v == Math.floor(v)) return String.valueOf((long) v);
        return String.format("%.2f", v);
    }

    static String unitLabel(Unit u) {
        return u == Unit.KG ? "kg" : "dozen";
    }

    static String shortUnit(Unit u) {
        return u == Unit.KG ? "kg" : "dz";
    }

    static String capitalize(String s) {
        StringBuilder sb = new StringBuilder();
        for (String w : s.toLowerCase().split(" ")) {
            if (w.isEmpty()) continue;
            sb.append(Character.toUpperCase(w.charAt(0))).append(w.substring(1)).append(" ");
        }
        return sb.toString().trim();
    }
}