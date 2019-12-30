public class StandardPrice implements Promotion
{
    private double catalogPrice;

    public StandardPrice(double catalogPrice)
    {
        this.catalogPrice = catalogPrice;
    }

    @Override
    public double CalculatePromotionTotal(int qtyBought)
    {
        return -1.0;
    }
}
