public abstract class Alcohol implements IAlcohol {

    protected String name;
    protected String types;
    protected int price;
    protected String notes;
    protected String country;

    public Alcohol(String name, String types, int price, String notes, String country) {
        this.name = name;
        this.types = types;
        this.price = price;
        this.notes = notes;
        this.country = country;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTypes() {
        return types;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getNotes() {
        return notes;
    }

    @Override
    public String getCountry() {
        return country;
    }
}
