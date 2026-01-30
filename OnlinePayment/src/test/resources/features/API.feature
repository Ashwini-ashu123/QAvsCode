Feature: Automation excercise for API testing

@APITesting
Scenario: Verify the user is able to get all the products information
    When user sends GET request to get all products
    Then response status code should be 200
    And products list should not be empty

@BookingAPITesting
Scenario: Create booking and verify booking details
    Given user has booking details
    When user creates a booking
    Then booking details should match the created data