import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.mockito.Mockito.*;

class RegisterTest
{
    private final static String PRODUCT_NAME = "test";
    private HashMap<String, Product> catalog;
    private Register register;

    @BeforeEach
    void setup()
    {
        this.catalog = new HashMap<>();
        this.register = new Register(this.catalog);
    }

    @Test
    void addCatalogProduct_addNewProduct_addsProduct() throws DuplicateCatalogProductException
    {
        Product product = mock(Product.class);
        when(product.getName()).thenReturn(PRODUCT_NAME);

        this.register.addCatalogProduct(product);
        Assertions.assertEquals(product, this.catalog.get(PRODUCT_NAME));

        verify(product).getName();
    }

    @Test
    void addCatalogProduct_addExistingProduct_throws() throws DuplicateCatalogProductException
    {
        Product product = mock(Product.class);
        when(product.getName()).thenReturn(PRODUCT_NAME);
        this.register.addCatalogProduct(product);
        Assertions.assertThrows(DuplicateCatalogProductException.class,
                () -> this.register.addCatalogProduct(product));
    }

    @Test
    void scanItem_scanNonexistingCatalogProduct_throws()
    {
        Product product = mock(Product.class);
        when(product.getName()).thenReturn(PRODUCT_NAME);

        Assertions.assertThrows(NonexistentProductException.class, () -> this.register.scanItem(PRODUCT_NAME));
    }

    @Test
    void scanItem_scanWeightedProduct_throws() throws DuplicateCatalogProductException
    {
        Product product = mock(WeightedProduct.class);
        when(product.getName()).thenReturn(PRODUCT_NAME);
        this.register.addCatalogProduct(product);

        Assertions.assertThrows(ProductTypeMismatch.class, () -> this.register.scanItem(PRODUCT_NAME));
    }

    @Test
    void scanItem_scanOneItem_calculatesTotal() throws DuplicateCatalogProductException, ProductTypeMismatch, NonexistentProductException
    {
        Product product = mock(Product.class);
        when(product.getName()).thenReturn(PRODUCT_NAME);
        when(product.calculatePrice()).thenReturn(new BigDecimal(0), new BigDecimal(1));
        this.register.addCatalogProduct(product);
        this.register.scanItem(product.getName());

        Assertions.assertEquals(new BigDecimal(1), this.register.getCurrentBill());

        verify(product).addBoughtItem(1);
        verify(product, times(2)).calculatePrice();

    }

    @Test
    void testScanItemWeight_scanQuantityProduct_throws() throws DuplicateCatalogProductException
    {
        Product product = mock(QuantityProduct.class);
        when(product.getName()).thenReturn(PRODUCT_NAME);
        this.register.addCatalogProduct(product);

        Assertions.assertThrows(ProductTypeMismatch.class, () -> this.register.scanItem(PRODUCT_NAME, 1));
    }

    @Test
    void testScanItemWeight_scanItem_calculatesTotal() throws DuplicateCatalogProductException, ProductTypeMismatch, NonexistentProductException
    {
        Product product = mock(Product.class);
        when(product.getName()).thenReturn(PRODUCT_NAME);
        when(product.calculatePrice()).thenReturn(new BigDecimal(0), new BigDecimal(42));
        this.register.addCatalogProduct(product);
        this.register.scanItem(product.getName(), 42);

        Assertions.assertEquals(new BigDecimal(42), this.register.getCurrentBill());

        verify(product).addBoughtItem(42);
        verify(product, times(2)).calculatePrice();
    }

    @Test
    void testScanItemWeight_scanItemMultipleTimes_calculatesTotal() throws DuplicateCatalogProductException, ProductTypeMismatch, NonexistentProductException
    {
        Product product = mock(Product.class);
        when(product.getName()).thenReturn(PRODUCT_NAME);
        when(product.calculatePrice()).thenReturn(new BigDecimal(0), new BigDecimal(42),
                new BigDecimal(0), new BigDecimal(84));
        this.register.addCatalogProduct(product);
        this.register.scanItem(product.getName(), 42);
        this.register.scanItem(product.getName(), 84);

        Assertions.assertEquals(new BigDecimal(126), this.register.getCurrentBill());

        verify(product).addBoughtItem(42);
        verify(product).addBoughtItem(84);
        verify(product, times(4)).calculatePrice();
    }

    @Test
    void removeScannedItem_removeOneWeightedItem_calculatesTotal() throws DuplicateCatalogProductException, ProductTypeMismatch, NonexistentProductException, TooFewItemsException
    {
        Product product = mock(Product.class);
        when(product.getName()).thenReturn(PRODUCT_NAME);
        when(product.calculatePrice()).thenReturn(new BigDecimal(0),
                new BigDecimal(42), new BigDecimal(42), new BigDecimal(0));
        when(product.removeBoughtItem(42)).thenReturn(true);
        this.register.addCatalogProduct(product);
        this.register.scanItem(product.getName(), 42);
        this.register.removeScannedItem(product.getName(), 42);

        Assertions.assertEquals(new BigDecimal(0), this.register.getCurrentBill());

        verify(product).removeBoughtItem(42);
        verify(product, times(4)).calculatePrice();
    }

    @Test
    void removeScannedItem_removeUnaddedProduct_returnsFalse()
    {
        Product product = mock(Product.class);
        when(product.getName()).thenReturn(PRODUCT_NAME);

        Assertions.assertThrows(NonexistentProductException.class, () -> this.register.removeScannedItem(product.getName(),
                1));
    }

    @Test
    void removeScannedItem_removeMoreThanInCart_throws() throws DuplicateCatalogProductException
    {
        Product product = mock(Product.class);
        when(product.getName()).thenReturn(PRODUCT_NAME);
        when(product.calculatePrice()).thenReturn(new BigDecimal(0));
        when(product.removeBoughtItem(5)).thenReturn(false);

        this.register.addCatalogProduct(product);

        Assertions.assertThrows(TooFewItemsException.class, () -> this.register.removeScannedItem(PRODUCT_NAME, 5));
    }
}