import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class CheckoutCounter
{
    public static void main (String[] args)
    {
        Register register = new Register();
        try
        {
            buildCatalogForRegister(register);
        }
        catch (DuplicateCatalogProductException e)
        {
            System.out.println("Oops, Duplicate items were added to catalog! Please fix and start program again");
            System.exit(1);
        }

        Scanner scanner = new Scanner(System.in);
        boolean userDone = false;

        System.out.println("Welcome to checkout!");
        while(!userDone)
        {
            System.out.println("Please enter the item you would like to scan, type 'remove' to remove an item, or type 'checkout' to checkout: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("checkout"))
            {
                userDone = true;
            }
            else if (input.equals("remove"))
            {
                System.out.println("Type the item you would like to remove: ");
                String productRemove = scanner.nextLine().toLowerCase();
                System.out.println("Type the quantity or the weight of the product you want to remove");
                int quantityRemove = scanner.nextInt();
                scanner.nextLine(); //removes newline from stream after getting integer

                handleRemoval(register, productRemove, quantityRemove);
                System.out.println("Your current balance is " + displayMoney(register.getCurrentBill()));
            }
            else
            {
                handleScanInput(register, input);
                System.out.println("Your current balance is " + displayMoney(register.getCurrentBill()));
            }
        }

        System.out.println("Thank you for shopping with us today!");
        System.out.println("Your final balance is $" + displayMoney(register.getCurrentBill()));
        System.exit(0);
    }

    private static void buildCatalogForRegister (Register register) throws DuplicateCatalogProductException
    {
        Promotion bananaPricing = new StandardPrice(new BigDecimal("0.40"));
        Product banana = new WeightedProduct("banana", bananaPricing);
        register.addCatalogProduct(banana);

        Promotion cerealPricing = new BuyGetLimitPrice(new BigDecimal(4), 3, 1, 100, 4);
        Product cereal = new QuantityProduct("cereal", cerealPricing);
        register.addCatalogProduct(cereal);

        Promotion pearsPricing = new BuyGetPrice(new BigDecimal("2.50"), 2, 1, 50);
        Product pear = new WeightedProduct("pear", pearsPricing);
        register.addCatalogProduct(pear);

        Promotion chipsPricing = new MultipleSetPrice(new BigDecimal("3.50"), 3, new BigDecimal("6.00"));
        Product chips = new QuantityProduct("chips", chipsPricing);
        register.addCatalogProduct(chips);
    }

    private static void handleScanInput(Register register, String scanString)
    {
        String[] scanStringParts = scanString.split(" ");
        if (scanStringParts.length == 1)
        {
            try
            {
                register.scanItem(scanString);
            }
            catch (ProductTypeMismatch e)
            {
                System.out.println("The product " + scanString + " requires a weight. Please scan again and enter weight");
            }
            catch (NonexistentProductException e)
            {
                System.out.println("The product " +scanString + " does not exist in the catalog. Please try another.");
            }

        }
        else if (scanStringParts.length == 2)
        {
            String productName = scanStringParts[0];
            try
            {
                int weight = Integer.parseInt(scanStringParts[1]);

                try
                {
                    register.scanItem(productName, weight);
                }
                catch (ProductTypeMismatch e)
                {
                    System.out.println("The product " + productName + " does not require a weight. Please scan again without it.");
                }
                catch (NonexistentProductException e)
                {
                    System.out.println("The product " + productName + " does not exist in the catalog. Please try another.");
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Cannot convert " + scanStringParts[1] + " to weight for scan item");
            }
        }
        else
        {
            System.out.println("Invalid input for scanning. Please try again. Either enter '<Product>' or '<Product> <Weight>'");
        }
    }

    private static void handleRemoval(Register register, String productName, int removalQuantity)
    {
        try
        {
            register.removeScannedItem(productName, removalQuantity);
        }
        catch (NonexistentProductException e)
        {
            System.out.println("Oops, the product " + productName + " does not exist in our catalog. Please try again and type a valid product.");
        }
        catch (TooFewItemsException e)
        {
            System.out.println("Oops, there are either too few " + productName + " in your cart or you did not specify a weight entered in your cart");
        }
    }

    private static BigDecimal displayMoney(BigDecimal bigDecimal)
    {
        return bigDecimal.setScale(2, RoundingMode.HALF_EVEN);
    }
}
