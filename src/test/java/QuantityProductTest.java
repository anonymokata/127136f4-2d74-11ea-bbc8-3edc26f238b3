import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class QuantityProductTest {
    private final static BigDecimal PRICE = new BigDecimal(42);

    private Promotion promo;
    private QuantityProduct quantityProduct;

    @BeforeEach
    void Setup()
    {
        this.promo = new StandardPrice(PRICE);
        this.quantityProduct = new QuantityProduct("TestProduct", this.promo);
    }

    @Test
    void calculatePrice_standardPrice_correctPrice()
    {
        this.quantityProduct.addBoughtItem(2);
        Assertions.assertEquals(new BigDecimal(84), this.quantityProduct.calculatePrice());
    }
}