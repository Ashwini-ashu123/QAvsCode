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




