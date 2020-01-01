import java.math.BigDecimal;

public interface Product
{
    String getName();
    BigDecimal calculatePrice();
    void addBoughtItem(int amount);
    boolean removeBoughtItem(int quantity);
}
