import java.util.ArrayList;
import java.util.List;

public class WeightedProduct implements Product
{
    private String name;
    private List<Integer> weights;
    Promotion promo;

    public WeightedProduct(String name, Promotion promo)
    {
        this.name = name;
        this.weights = new ArrayList<>();
        this.promo = promo;
    }

    public String getName() {
        return this.name;
    }

    public double calculatePrice()
    {
        int totalWeight = 0;
        for (Integer weight : weights)
        {
            totalWeight += weight;
        }
        return this.promo.CalculatePromotionTotal(totalWeight);
    }

    public void addBoughtItem(int amount)
    {
        weights.add(amount);
    }

    public boolean removeBoughtItem(int weight)
    {
        return weights.remove(Integer.valueOf(weight));
    }
}

