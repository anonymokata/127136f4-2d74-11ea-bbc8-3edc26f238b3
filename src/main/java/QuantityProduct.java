public class QuantityProduct implements Product
{
    private String name;
    private int qtyBought;
    private Promotion promo;

    public QuantityProduct(String name, Promotion promo)
    {
        this.name = name;
        this.qtyBought = 0;
        this.promo = promo;
    }

    public String getName()
    {
        return this.name;
    }

    public double calculatePrice() {
        return this.promo.CalculatePromotionTotal(this.qtyBought);
    }

    public void addBoughtItem(int amount)
    {
        this.qtyBought += amount;
    }

    public boolean removeBoughtItem(int quantity)
    {
        if (this.qtyBought - quantity >= 0)
        {
            this.qtyBought -= quantity;
            return true;
        }
        else
        {
            return false;
        }
    }
}
