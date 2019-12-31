import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class RegisterTest
{
    private final static String PRODUCT_NAME = "TestName";
    private Register register;

    @BeforeEach
    void setup()
    {
        this.register = new Register();
    }

    @Test
    void addCatalogProduct_addNewProduct_returnsTrue()
    {
        Product product = mock(Product.class);
        when(product.getName()).thenReturn(PRODUCT_NAME);

        Assertions.assertTrue(this.register.addCatalogProduct(product));

        verify(product).getName();
    }

    @Test
    void addCatalogProduct_addExistingProduct_returnsFalse()
    {
        Product product = mock(Product.class);
        when(product.getName()).thenReturn(PRODUCT_NAME);
        this.register.addCatalogProduct(product);

        Assertions.assertFalse(this.register.addCatalogProduct(product));
    }

    @Test
    void scanItem_scanNonexistingCatalogProduct_returnsFalse()
    {
        Product product = mock(Product.class);
        when(product.getName()).thenReturn(PRODUCT_NAME);

        Assertions.assertFalse(this.register.scanItem(product.getName()));
    }

    @Test
    void scanItem_scanOneItem_returnsTrue()
    {
        Product product = mock(Product.class);
        when(product.getName()).thenReturn(PRODUCT_NAME);
        this.register.addCatalogProduct(product);

        Assertions.assertTrue(this.register.scanItem(product.getName()));

        verify(product).addBoughtItem(1);
        verify(product, times(2)).calculatePrice();

    }

    @Test
    void testScanItemWeight_scanItem_returnsTrue()
    {
        Product product = mock(Product.class);
        when(product.getName()).thenReturn(PRODUCT_NAME);
        this.register.addCatalogProduct(product);

        Assertions.assertTrue(this.register.scanItem(product.getName(), 42));

        verify(product).addBoughtItem(42);
        verify(product, times(2)).calculatePrice();
    }

    @Test
    void testScanItemWeight_scanItemMultipleTimes_returnsTrue()
    {
        Product product = mock(Product.class);
        when(product.getName()).thenReturn(PRODUCT_NAME);
        this.register.addCatalogProduct(product);

        Assertions.assertTrue(this.register.scanItem(product.getName(), 42));
        Assertions.assertTrue(this.register.scanItem(product.getName(), 84));

        verify(product).addBoughtItem(42);
        verify(product).addBoughtItem(84);
        verify(product, times(4)).calculatePrice();
    }

    @Test
    void removeScannedItem_removeOneWeightedItem_returnsTrue()
    {
        Product product = mock(Product.class);
        when(product.getName()).thenReturn(PRODUCT_NAME);
        when(product.removeBoughtItem(42)).thenReturn(true);
        this.register.addCatalogProduct(product);
        this.register.scanItem(product.getName(), 42);

        Assertions.assertTrue(this.register.removeScannedItem(product.getName(), 42));

        verify(product).removeBoughtItem(42);
        verify(product, times(3)).calculatePrice();
    }

    @Test
    void removeScannedItem_removeUnaddedProduct_returnsFalse()
    {
        Product product = mock(Product.class);
        when(product.getName()).thenReturn(PRODUCT_NAME);

        Assertions.assertFalse(this.register.removeScannedItem(product.getName(), 1));
    }
}