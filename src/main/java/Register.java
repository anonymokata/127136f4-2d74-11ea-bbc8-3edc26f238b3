import java.util.HashMap;

public class Register
{
    private HashMap<String, Product> catalog;
    private double currentBill;

    public Register()
    {
        this.catalog = new HashMap<>();
        this.currentBill = 0;
    }

    public Register(HashMap<String, Product> catalog)
    {
        this.catalog = catalog;
        this.currentBill = 0;
    }

    public double getCurrentBill()
    {
        return this.currentBill;
    }

    public boolean addCatalogProduct(Product product)
    {
        String productName = product.getName();
        if (!this.catalog.containsKey(productName))
        {
            this.catalog.put(productName, product);
            return true;
        }
        return false;
    }

    public boolean scanItem(String productName)
    {
        if (this.catalog.containsKey(productName))
        {
            Product product = this.catalog.get(productName);
            this.currentBill -= product.calculatePrice();
            product.addBoughtItem(1);
            this.currentBill += product.calculatePrice();
            return true;
        }
        return false;
    }

    public boolean scanItem(String productName, int weight)
    {
        if (this.catalog.containsKey(productName))
        {
            Product product = this.catalog.get(productName);
            this.currentBill -= product.calculatePrice();
            product.addBoughtItem(weight);
            this.currentBill += product.calculatePrice();
            return true;
        }
        return false;
    }

    public boolean removeScannedItem(String productName, int quantity)
    {
        if (this.catalog.containsKey(productName))
        {
            Product product = this.catalog.get(productName);
            this.currentBill -= product.calculatePrice();
            return product.removeBoughtItem(quantity);
        }
        return false;
    }
}
