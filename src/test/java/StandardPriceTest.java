import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class StandardPriceTest {
    private final static BigDecimal PRICE = new BigDecimal(42);

    @Test
    void calculatePromotionTotal()
    {
        StandardPrice standardPrice = new StandardPrice(PRICE);
        Assertions.assertEquals(new BigDecimal(84), standardPrice.CalculatePromotionTotal(2));
    }
}