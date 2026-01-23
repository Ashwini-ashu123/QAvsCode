Feature: Payment Demo Process

Scenario: Login to the e-commerce application
Given Navigate to the Url for e-commerce application
Then Click on the sign in button and fill the username and password
And verify the user is logged in successfully

@HomePage
Scenario: Verify the home page is having the products
Given Navigate to the Url for e-commerce application
Then Click on the sign in button and fill the username and password
And verify the user is logged in successfully
Then verify the home page is having the 'CATEGORY' 'BRANDS' and 'FEATURES ITEMS' on the home page
And verify the user is able to see the products on the home page

@KidsDressPurchase
Scenario: Login - Kids dress purchase and payment process - logout
Given Navigate to the Url for e-commerce application
Then Click on the sign in button and fill the username and password
And verify the user is logged in successfully
Then verify the home page is having the 'CATEGORY' 'BRANDS' and 'FEATURES ITEMS' on the home page
And verify the user is able to see the products on the home page
Then click on the "Kids" section from the category and select the "Tops & Shirts" option
Then verify the user is on the "Kids - Tops & Shirts Products" page
And Select the product that range from 250 to 500 and add to cart
Then verify the products is added to the "Shopping Cart" and proceed to checkout
And verify the user is in "Checkout" page and proceed to payment process
Then verify the user is in "Payment" page and complete the payment process
And verify the order is placed successfully and download the invoice
Then Logout from the application

@MenDressPurchase
Scenario: Login - Men dress purchase and payment process - logout
Given Navigate to the Url for e-commerce application
Then Click on the sign in button and fill the username and password
And verify the user is logged in successfully
Then verify the home page is having the 'CATEGORY' 'BRANDS' and 'FEATURES ITEMS' on the home page
And verify the user is able to see the products on the home page
Then click on the "Men" section from the category and select the "Tshirts " option
Then verify the user is on the "Men - Tshirts Products" page
And Select the product that range from 250 to 500 and add to cart
Then verify the products is added to the "Shopping Cart" and proceed to checkout
And verify the user is in "Checkout" page and proceed to payment process
Then verify the user is in "Payment" page and complete the payment process
And verify the order is placed successfully and download the invoice
Then Logout from the application

@WomenenDressPurchase
Scenario: Login - Women dress purchase and payment process - logout
Given Navigate to the Url for e-commerce application
Then Click on the sign in button and fill the username and password
And verify the user is logged in successfully
Then verify the home page is having the 'CATEGORY' 'BRANDS' and 'FEATURES ITEMS' on the home page
And verify the user is able to see the products on the home page
Then click on the "Women" section from the category and select the "Tops" option
Then verify the user is on the "Women - Tops Products" page
And Select the product that range from 250 to 500 and add to cart
Then verify the products is added to the "Shopping Cart" and proceed to checkout
And verify the user is in "Checkout" page and proceed to payment process
Then verify the user is in "Payment" page and complete the payment process
And verify the order is placed successfully and download the invoice
Then Logout from the application








