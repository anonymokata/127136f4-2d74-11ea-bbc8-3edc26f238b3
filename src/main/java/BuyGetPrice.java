public class BuyGetPrice implements Promotion{
    private double msrp;
    private int qtyRequired;
    private double qtyDiscount;
    private double percentOff;

    public BuyGetPrice(double msrp, int qtyRequired, double qtyDiscount, double percentOff)
    {

    }

    @Override
    public double CalculatePromotionTotal(int qtyBought)
    {
        return 0;
    }
}
