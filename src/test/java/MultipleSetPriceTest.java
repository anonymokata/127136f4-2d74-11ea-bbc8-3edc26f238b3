import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MultipleSetPriceTest
{
    private final static double ORIGINAL_PRICE = 42.00;
    private final static double DEAL_PRICE = 100.00;
    private MultipleSetPrice multipleSetPrice;

    @BeforeEach
    void setup()
    {
        this.multipleSetPrice = new MultipleSetPrice(ORIGINAL_PRICE, 3, DEAL_PRICE);
    }

    @Test
    void calculatePromotionTotal_BuyTwo_StandardPrice()
    {
        Assertions.assertEquals(ORIGINAL_PRICE * 2, this.multipleSetPrice.CalculatePromotionTotal(2));
    }

    @Test
    void calculatePromotionTotal_BuyThree_DealPrice()
    {
        Assertions.assertEquals(DEAL_PRICE, this.multipleSetPrice.CalculatePromotionTotal(3));
    }

    @Test
    void calculatePromotionTotal_BuySix_DealPrice()
    {
        Assertions.assertEquals(DEAL_PRICE * 2, this.multipleSetPrice.CalculatePromotionTotal(6));
    }

    @Test
    void calculatePromotionTotal_BuyTen_DealPriceWithRemainder()
    {
        Assertions.assertEquals(DEAL_PRICE * 3 + ORIGINAL_PRICE, this.multipleSetPrice.CalculatePromotionTotal(10));
    }
}