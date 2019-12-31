public class BuyGetPrice implements Promotion{
    private double msrp;
    private int qtyRequired;
    private double qtyDiscount;
    private double percentOff;

    public BuyGetPrice(double msrp, int qtyRequired, double qtyDiscount, double percentOff)
    {
        this.msrp = msrp;
        this.qtyRequired = qtyRequired;
        this.qtyDiscount = qtyDiscount;
        this.percentOff = percentOff;
    }

    @Override
    public double CalculatePromotionTotal(int qtyBought)
    {
        int discountsAvailable = qtyBought / this.qtyRequired;
        return (qtyBought  - discountsAvailable * this.percentOff) * msrp;
    }
}
