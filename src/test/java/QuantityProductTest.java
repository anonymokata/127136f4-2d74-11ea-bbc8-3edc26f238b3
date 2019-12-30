import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuantityProductTest {
    private final static int PRICE = 42;

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
        Assertions.assertEquals(84, this.quantityProduct.calculatePrice());
    }
}