import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class WeightedProduct implements Product
{
    private String name;
    private List<Integer> weights;
    Promotion promo;

    public WeightedProduct(String name, Promotion promo)
    {
        this.name = name.toLowerCase();
        this.weights = new ArrayList<>();
        this.promo = promo;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public BigDecimal calculatePrice()
    {
        int totalWeight = 0;
        for (Integer weight : weights)
        {
            totalWeight += weight;
        }
        return this.promo.CalculatePromotionTotal(totalWeight);
    }

    @Override
    public void addBoughtItem(int amount)
    {
        weights.add(amount);
    }

    @Override
    public boolean removeBoughtItem(int weight)
    {
        return weights.remove(Integer.valueOf(weight));
    }
}
