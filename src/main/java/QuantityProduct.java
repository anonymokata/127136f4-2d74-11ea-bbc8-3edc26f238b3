import java.math.BigDecimal;

public class QuantityProduct implements Product
{
    private String name;
    private int qtyBought;
    private Promotion promo;

    public QuantityProduct(String name, Promotion promo)
    {
        this.name = name.toLowerCase();
        this.qtyBought = 0;
        this.promo = promo;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public BigDecimal calculatePrice()
    {
        return this.promo.CalculatePromotionTotal(this.qtyBought);
    }

    @Override
    public void addBoughtItem(int amount)
    {
        this.qtyBought += amount;
    }

    @Override
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
