public interface Product
{
    String getName();
    double calculatePrice();
    void addBoughtItem(int amount);
    void removeBoughtItem(int quantity);
}
