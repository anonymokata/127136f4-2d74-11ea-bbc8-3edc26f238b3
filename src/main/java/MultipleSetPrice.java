import java.math.BigDecimal;

public class MultipleSetPrice implements Promotion
{
    private BigDecimal msrp;
    private int qtyRequired;
    private BigDecimal dealGroupPrice;

    public MultipleSetPrice(BigDecimal msrp, int qtyRequired, BigDecimal dealGroupPrice)
    {
        this.msrp = msrp;
        this.qtyRequired = qtyRequired;
        this.dealGroupPrice = dealGroupPrice;
    }

    @Override
    public BigDecimal CalculatePromotionTotal(int qtyBought)
    {
        int dealGroups = qtyBought / this.qtyRequired;
        int remainder = qtyBought % this.qtyRequired;
        return this.dealGroupPrice.multiply(new BigDecimal(dealGroups)).add(this.msrp.multiply(new BigDecimal(remainder)));
    }
}
