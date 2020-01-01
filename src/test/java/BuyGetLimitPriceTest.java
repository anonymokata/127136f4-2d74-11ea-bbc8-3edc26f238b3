import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class BuyGetLimitPriceTest {

    private final static BigDecimal ORIGINAL_PRICE = new BigDecimal(42);
    private BuyGetLimitPrice buyGetLimitPrice;

    @BeforeEach
    void setup()
    {
        this.buyGetLimitPrice = new BuyGetLimitPrice(ORIGINAL_PRICE, 2, 1, 100, 4);
    }

    @Test
    void calculatePromotionTotal_BuyOneGetOneFree_HalfOffPrice()
    {
        Assertions.assertEquals(ORIGINAL_PRICE, this.buyGetLimitPrice.CalculatePromotionTotal(2));
    }

    @Test
    void calculatePromotionTotal_BuyOne_StandardPrice()
    {
        Assertions.assertEquals(ORIGINAL_PRICE, this.buyGetLimitPrice.CalculatePromotionTotal(1));
    }

    @Test
    void calculatePromotionTotal_BuyTenGetFiveFree_HalfOffPrice()
    {
        Assertions.assertEquals(ORIGINAL_PRICE .multiply(new BigDecimal(4)), this.buyGetLimitPrice.CalculatePromotionTotal(6));
    }
}