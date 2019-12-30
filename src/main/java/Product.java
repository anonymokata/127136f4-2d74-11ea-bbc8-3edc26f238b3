public interface Product
{
    String getName();
    double calculatePrice();
    void addBoughtItem(int amount);
    boolean removeBoughtItem(int quantity);
}
