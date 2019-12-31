public class BuyGetLimitPrice implements Promotion
{
    private double msrp;
    private int qtyRequired;
    private int qtyDiscount;
    private double percentOff;
    private int dealLimitQty;

    public BuyGetLimitPrice(double msrp, int qtyRequired, int qtyDiscount, double percentOff, int dealQtyLimit)
    {
        this.msrp = msrp;
        this.qtyRequired = qtyRequired;
        this.qtyDiscount = qtyDiscount;
        this.percentOff = percentOff;
        this.dealLimitQty = dealQtyLimit;
    }

    @Override
    public double CalculatePromotionTotal(int qtyBought) {
        int discountsAvailable;
        if (qtyBought >= this.dealLimitQty)
        {
            discountsAvailable = this.dealLimitQty / this.qtyRequired;
        }
        else
        {
            discountsAvailable = qtyBought / this.qtyRequired;
        }
        return (qtyBought  - discountsAvailable * this.percentOff) * msrp;
    }
}
