Checkout Counter Kata by Tom Powers
===================================

Welcome to my grocery checkout counter! I hope you enjoy it :)

Organziation
------------
* CheckoutCounter - main class and driver of application
* Register - class which scans items, removes items, and keeps track of a user's total balance
* Products - items which the user can buy, specified in the driver class before transactions
* Promos - Pricing objects for products (e.g. standard pricing, BOGO pricing, group pricing)

Future Things
-------------
1. __Better allow users to enter items in the catalog instead of hardcoding a method in the driver class__
2. Input testing for main driver to better guarentee application works as expected (i.e integeration testing)
3. Hook-up Jenkins to run builds on merge request in github
4. Add loops in input/output to better handle error cases instead of redirect to main prompt

How to Build and Run
------------
1. ./gradlew build
2. ./gradle run
3. (Optional) ./gradlew test (this runs tests as ./gradlew does too)
4. Type any of the four already added products (banana, chips, cereal, pear). Both pear and banana will require a weight when scanned.

Adding your own items
---------------------
Look at the buildCatalogForRegister(...) method in CheckoutCounter. There are four examples which demonstrate each pricing model and use both weighted and non-weighted products (quantity products).

Misc.
-----
1. Headers were added for the pricing options (promos) as the details are more subtle
2. Feel free to reach out with any questions!