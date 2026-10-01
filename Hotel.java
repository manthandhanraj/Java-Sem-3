public class Hotel {

    String name;
    String room;
    float price;

    public Hotel(String name, String room, float price) {
        this.name = name;
        this.room = room;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getRoom() {
        return room;
    }

    public float getPrice() {
        return price;
    }
}