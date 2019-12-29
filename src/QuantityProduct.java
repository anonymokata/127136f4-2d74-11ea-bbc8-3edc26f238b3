public class QuantityProduct implements Product
{
    private String name;
    private int qtyBought;
    private Promotion promo;

    public QuantityProduct(String name, Promotion promo)
    {

    }

    @Override
    public double calculatePrice() {
        return -1.0;
    }

    public void buyItem(int quantity)
    {

    }

    public String getName()
    {
        return "";
    }

}
