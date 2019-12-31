public class BuyGetLimitPrice implements Promotion
{
    private double msrp;
    private int qtyRequired;
    private int qtyDiscount;
    private double percentOff;
    private int dealLimitQty;

    public BuyGetLimitPrice(double msrp, int qtyRequired, int qtyDiscount, double percentOff, int dealQtyLimit)
    {

    }

    @Override
    public double CalculatePromotionTotal(int qtyBought) {
        return 0;
    }
}
