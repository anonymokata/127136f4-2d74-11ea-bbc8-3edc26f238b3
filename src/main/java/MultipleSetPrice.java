public class MultipleSetPrice implements Promotion
{
    private double msrp;
    private int qtyRequired;
    private double dealGroupPrice;

    public MultipleSetPrice(double msrp, int qtyRequired, double dealGroupPrice)
    {
        this.msrp = msrp;
        this.qtyRequired = qtyRequired;
        this.dealGroupPrice = dealGroupPrice;
    }

    @Override
    public double CalculatePromotionTotal(int qtyBought)
    {
        int dealGroups = qtyBought / this.qtyRequired;
        int remainder = qtyBought % this.qtyRequired;
        return dealGroups * this.dealGroupPrice + remainder * this.msrp;
    }
}
