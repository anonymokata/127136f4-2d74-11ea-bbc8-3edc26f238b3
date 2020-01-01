import java.math.BigDecimal;

public class StandardPrice implements Promotion
{
    private BigDecimal msrp;

    public StandardPrice(BigDecimal catalogPrice)
    {
        this.msrp = catalogPrice;
    }

    @Override
    public BigDecimal CalculatePromotionTotal(int qtyBought)
    {
        return this.msrp.multiply(new BigDecimal(qtyBought));
    }
}
