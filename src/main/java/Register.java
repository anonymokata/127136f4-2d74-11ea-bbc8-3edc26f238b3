import java.math.BigDecimal;
import java.util.HashMap;

public class Register
{
    private HashMap<String, Product> catalog;
    private BigDecimal currentBill;

    public Register()
    {
        this.catalog = new HashMap<>();
        this.currentBill = new BigDecimal(0);
    }

    public Register(HashMap<String, Product> catalog)
    {
        this.catalog = catalog;
        this.currentBill = new BigDecimal(0);
    }

    public BigDecimal getCurrentBill()
    {
        return this.currentBill;
    }

    public void addCatalogProduct(Product product) throws DuplicateCatalogProductException
    {
        String productName = product.getName();
        if (!this.catalog.containsKey(productName))
        {
            this.catalog.put(productName, product);
        }
        else
        {
            throw new DuplicateCatalogProductException();
        }

    }

    public void scanItem(String productName) throws NonexistentProductException, ProductTypeMismatch
    {
        if (this.catalog.containsKey(productName))
        {
            Product product = this.catalog.get(productName);
            if (product instanceof WeightedProduct)
            {
                throw new ProductTypeMismatch();
            }
            this.currentBill = this.currentBill.subtract(product.calculatePrice());
            product.addBoughtItem(1);
            this.currentBill = this.currentBill.add(product.calculatePrice());
        }
        else
        {
            throw new NonexistentProductException();
        }
    }

    public void scanItem(String productName, int weight) throws NonexistentProductException, ProductTypeMismatch
    {
        if (this.catalog.containsKey(productName))
        {
            Product product = this.catalog.get(productName);
            if (product instanceof QuantityProduct)
            {
                throw new ProductTypeMismatch();
            }
            this.currentBill = this.currentBill.subtract(product.calculatePrice());
            product.addBoughtItem(weight);
            this.currentBill = this.currentBill.add(product.calculatePrice());
        }
        else
        {
            throw new NonexistentProductException();
        }
    }

    public void removeScannedItem(String productName, int quantity) throws NonexistentProductException, TooFewItemsException
    {
        if (this.catalog.containsKey(productName))
        {
            Product product = this.catalog.get(productName);
            this.currentBill = this.currentBill.subtract(product.calculatePrice());
            boolean canRemove = product.removeBoughtItem(quantity);
            if (!canRemove)
            {
                throw new TooFewItemsException();
            }
            this.currentBill = this.currentBill.add(product.calculatePrice());
        }
        else
        {
            throw new NonexistentProductException();
        }
    }
}
