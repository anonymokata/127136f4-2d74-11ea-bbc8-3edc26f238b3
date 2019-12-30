import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StandardPriceTest {
    private final static double PRICE = 42.0;

    @Test
    void calculatePromotionTotal()
    {
        StandardPrice standardPrice = new StandardPrice(PRICE);
        Assertions.assertEquals(PRICE * 2, standardPrice.CalculatePromotionTotal(2));
    }
}