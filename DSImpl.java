import java.util.ArrayList;
import java.util.List;

public class DSImpl {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<Integer>();

        list.add(23);
        list.add(12);
        list.add(13);

        for(Integer abc : list) {
            System.out.println(abc + 1);
        }

        List<String> names = new ArrayList<String>();

        names.add("Manthan");
        names.add("Viraj");
        names.add("Atherv");

        for(String name : names) {
            System.out.println(name);
        }
    }
}