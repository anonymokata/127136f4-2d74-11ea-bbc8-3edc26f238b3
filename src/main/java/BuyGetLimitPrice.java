import java.math.BigDecimal;
import java.math.RoundingMode;

public class BuyGetLimitPrice implements Promotion
{
    private BigDecimal msrp;
    private int qtyRequired;
    private int qtyDiscount;
    private BigDecimal percentOff;
    private int dealLimitQty;

    /**
     *
     * @param msrp standard catalog price of product
     * @param qtyRequired amount required for deal (e.g. Buy One for BOGO 1/2 off)
     * @param qtyDiscount amount discounted (e.g Get One of BOGO 1/2 off)
     * @param percentOff amount discounted of the discountable product (e.g. 1/2 of BOGO 1/2 off)
     * @param dealQtyLimit number of items applicable under discount
     */
    public BuyGetLimitPrice(BigDecimal msrp, int qtyRequired, int qtyDiscount, int percentOff, int dealQtyLimit)
    {
        this.msrp = msrp;
        this.qtyRequired = qtyRequired;
        this.qtyDiscount = qtyDiscount;
        this.percentOff = new BigDecimal(percentOff).divide(new BigDecimal(100), RoundingMode.HALF_EVEN);
        this.dealLimitQty = dealQtyLimit;
    }

    @Override
    public BigDecimal CalculatePromotionTotal(int qtyBought) {
        int discountsAvailable;
        if (qtyBought >= this.dealLimitQty)
        {
            discountsAvailable = this.dealLimitQty / this.qtyRequired;
        }
        else
        {
            discountsAvailable = qtyBought / this.qtyRequired;
        }
        BigDecimal dealFactor = new BigDecimal(qtyBought).subtract(new BigDecimal(discountsAvailable).multiply(this.percentOff).multiply(new BigDecimal(this.qtyDiscount)));
        return dealFactor.multiply(this.msrp);
    }
}
