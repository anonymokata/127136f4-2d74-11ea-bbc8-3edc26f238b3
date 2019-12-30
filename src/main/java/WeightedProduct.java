import java.util.List;

public class WeightedProduct implements Product
{
    private String name;
    private List<Integer> weights;
    Promotion promo;

    public WeightedProduct(String name, Promotion promo)
    {

    }

    public String getName() {
        return "";
    }

    public double calculatePrice()
    {
        return -1.0;
    }

    public void addBoughtItem(int amount)
    {

    }

    public void removeBoughtItem(int weight)
    {

    }
}

