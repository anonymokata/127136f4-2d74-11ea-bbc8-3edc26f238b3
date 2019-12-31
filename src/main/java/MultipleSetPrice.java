public class MultipleSetPrice implements Promotion
{
    private int msrp;
    private int qtyRequired;
    private double dealGroupPrice;

    public MultipleSetPrice(int msrp, int qtyRequired, double dealGroupPrice)
    {

    }

    @Override
    public double CalculatePromotionTotal(int qtyBought)
    {
        return 0;
    }
}
