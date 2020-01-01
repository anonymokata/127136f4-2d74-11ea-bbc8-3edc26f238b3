import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class MultipleSetPriceTest
{
    private final static BigDecimal ORIGINAL_PRICE = new BigDecimal(42);
    private final static BigDecimal DEAL_PRICE = new BigDecimal(100);
    private MultipleSetPrice multipleSetPrice;

    @BeforeEach
    void setup()
    {
        this.multipleSetPrice = new MultipleSetPrice(ORIGINAL_PRICE, 3, DEAL_PRICE);
    }

    @Test
    void calculatePromotionTotal_BuyTwo_StandardPrice()
    {
        Assertions.assertEquals(ORIGINAL_PRICE.multiply(new BigDecimal(2)), this.multipleSetPrice.CalculatePromotionTotal(2));
    }

    @Test
    void calculatePromotionTotal_BuyThree_DealPrice()
    {
        Assertions.assertEquals(DEAL_PRICE, this.multipleSetPrice.CalculatePromotionTotal(3));
    }

    @Test
    void calculatePromotionTotal_BuySix_DealPrice()
    {
        Assertions.assertEquals(DEAL_PRICE.multiply(new BigDecimal(2)), this.multipleSetPrice.CalculatePromotionTotal(6));
    }

    @Test
    void calculatePromotionTotal_BuyTen_DealPriceWithRemainder()
    {
        Assertions.assertEquals(DEAL_PRICE.multiply(new BigDecimal(3)).add(ORIGINAL_PRICE), this.multipleSetPrice.CalculatePromotionTotal(10));
    }
}