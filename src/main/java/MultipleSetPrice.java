public class MultipleSetPrice implements Promotion
{
    private double msrp;
    private int qtyRequired;
    private double dealGroupPrice;

    public MultipleSetPrice(double msrp, int qtyRequired, double dealGroupPrice)
    {

    }

    @Override
    public double CalculatePromotionTotal(int qtyBought)
    {
        return 0;
    }
}
