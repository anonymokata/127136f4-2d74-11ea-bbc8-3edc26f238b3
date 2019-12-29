import java.util.List;

public class WeightedProduct implements Product
{
    private String name;
    private List<Integer> weights;
    Promotion promo;
    public WeightedProduct(String name)
    {
        this.name = name;
    }

    @Override
    public double calculatePrice()
    {
        return -1.0;
    }

    @Override
    public void buyItem(int amount)
    {

    }
}

