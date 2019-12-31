import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BuyGetPriceTest
{
    private final static double ORIGINAL_PRICE = 42.00;
    private BuyGetPrice buyGetPrice;

    @BeforeEach
    void setup()
    {
        this.buyGetPrice = new BuyGetPrice(ORIGINAL_PRICE, 1, 1, 100.0);
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
        Assertions.assertEquals(ORIGINAL_PRICE * 5, this.buyGetPrice.CalculatePromotionTotal(10));
    }
}