public class StandardPrice implements Promotion
{
    private int catalogPrice;

    public StandardPrice(int catalogPrice)
    {
        this.catalogPrice = catalogPrice;
    }

    @Override
    public double CalculatePromotionTotal(int qtyBought) {
        return -1.0;
    }
}
