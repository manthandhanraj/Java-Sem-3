import java.util.ArrayList;
import java.util.List;

public class HotelInput {

    public static void main(String[] args) {

        List<Hotel> hotels = new ArrayList<Hotel>();

        Hotel h1 = new Hotel("Crowne", "L1", 1234);
        Hotel h2 = new Hotel("Sarovar", "L1", 2345);

        hotels.add(h1);
        hotels.add(h2);

        for(Hotel x : hotels) {
            System.out.println(x.getName() + " " + x.getPrice());
        }
    }
}