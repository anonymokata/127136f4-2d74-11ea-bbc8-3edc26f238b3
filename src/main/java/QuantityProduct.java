public class QuantityProduct implements Product
{
    private String name;
    private int qtyBought;
    private Promotion promo;

    public QuantityProduct(String name, Promotion promo)
    {

    }

    public String getName()
    {
        return "";
    }

    public double calculatePrice() {
        return -1.0;
    }

    public void addBoughtItem(int amount) {

    }

    public void removeBoughtItem(int quantity) {

    }
}
