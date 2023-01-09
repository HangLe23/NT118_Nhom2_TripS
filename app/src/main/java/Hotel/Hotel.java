package Hotel;

public class Hotel {
    private int image;
    private String name;
    private String address;

    public Hotel(int image, String name, String address) {
        this.image = image;
        this.name = name;
        this.address = address;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
