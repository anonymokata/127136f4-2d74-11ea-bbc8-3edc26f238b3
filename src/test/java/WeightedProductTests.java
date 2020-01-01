import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class WeightedProductTests
{
    private final static BigDecimal PRICE = new BigDecimal(42);
    private WeightedProduct weightedProduct;

    @BeforeEach
    void Setup()
    {
        Promotion promo = new StandardPrice(PRICE);
        this.weightedProduct = new WeightedProduct("TestProduct", promo);
    }

    @Test
    void calculatePrice_standardPriceSingle_correctPrice()
    {
        this.weightedProduct.addBoughtItem(18);
        Assertions.assertEquals(PRICE.multiply(new BigDecimal(18)), this.weightedProduct.calculatePrice());
    }

    @Test
    void calculatePrice_standardPriceMultiple_correctPrice()
    {
        this.weightedProduct.addBoughtItem(18);
        this.weightedProduct.addBoughtItem(12);
        Assertions.assertEquals(PRICE.multiply(new BigDecimal(30)), this.weightedProduct.calculatePrice());
    }

    @Test
    void removeBoughtItem_addAndRemove_correctWeights()
    {
        this.weightedProduct.addBoughtItem(12);
        this.weightedProduct.addBoughtItem(15);
        Assertions.assertTrue(this.weightedProduct.removeBoughtItem(15));
    }

    @Test
    void removeBoughtItem_removeNonexistingWeight_noWeights()
    {
        Assertions.assertFalse(this.weightedProduct.removeBoughtItem(15));
    }
}