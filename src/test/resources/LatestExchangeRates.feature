Feature: Exchange Rates API latest rates
  As a user of the Exchange Rates API
  I want to verify the response codes for the latest rates

  Scenario: client makes successful API GET call
    When the client requests to the latest exchange rates endpoint
    Then the API should return a 200 response code

  Scenario: client makes call using symbol PLN and base EUR
    Given the client requests 'PLN' and base 'EUR'
    When the client requests to the latest exchange rates endpoint
    Then the API should return a 200 response code
    And the client revives response with success status base 'EUR' and rates for 'PLN'

  Scenario: Invalid API key
    Given an invalid API key
    When the client requests to the latest exchange rates endpoint
    Then the API should return a 401 response code

  Scenario: Forbidden access
    #my issue is how to get such a kind of apikey :(
    Given a valid API key with insufficient access rights
    When the client requests to the latest exchange rates endpoint
    Then the API should return a 403 response code

  Scenario: Resource not found
    Given a non-existent endpoint
    When the client requests to the latest exchange rates endpoint
    Then the API should return a 404 response code
#     yep, and this one is failing Expected :404 Actual   :400

  Scenario: Invalid request
    Given a request with invalid parameters
    When the client requests to the latest exchange rates endpoint
    Then the API should return a 400 response code