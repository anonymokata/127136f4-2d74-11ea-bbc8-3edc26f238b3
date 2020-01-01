import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class BuyGetPriceTest
{
    private final static BigDecimal ORIGINAL_PRICE = new BigDecimal(42);
    private BuyGetPrice buyGetPrice;

    @BeforeEach
    void setup()
    {
        this.buyGetPrice = new BuyGetPrice(ORIGINAL_PRICE, 2, 1, 100);
    }

    @Test
    void calculatePromotionTotal_BuyOneGetOneFree_HalfOffPrice()
    {
        Assertions.assertEquals(ORIGINAL_PRICE, this.buyGetPrice.CalculatePromotionTotal(2));
    }

    @Test
    void calculatePromotionTotal_BuyOne_StandardPrice()
    {
        Assertions.assertEquals(ORIGINAL_PRICE, this.buyGetPrice.CalculatePromotionTotal(1));
    }

    @Test
    void calculatePromotionTotal_BuyTenGetFiveFree_HalfOffPrice()
    {
        Assertions.assertEquals(ORIGINAL_PRICE.multiply(new BigDecimal(5)), this.buyGetPrice.CalculatePromotionTotal(10));
    }
}