Feature: Users test scenarios

Scenario: Post 200
    Given the Method is "POST" for the endpoint "users"
    When the "POST200.txt" scenario file is loaded
    Then the "POST" request is sent and the response passes assertions

Scenario: Get 200
    Given the Method is "POST" for the endpoint "users"
    When the "POSTDefault.txt" scenario file is loaded
    And request is sent
    And the Method is "GET" for the endpoint "users"
    Then the "GET" request is sent and the response passes assertions
Scenario: Delete 200
    Given the Method is "POST" for the endpoint "users"
    When the "POSTDefault.txt" scenario file is loaded
    And request is sent
    And the Method is "DELETE" for the endpoint "users"
    Then the "DELETE" request is sent and the response passes assertions
    And a second DELETE request gets a "404" response code

Scenario: Put 200
    Given the Method is "POST" for the endpoint "users"
    When the "POSTDefault.txt" scenario file is loaded
    And request is sent
    And the Method is "PUT" for the endpoint "users"
    And the PUT file is set to "PUT200.txt"
    Then the "PUT" request is sent and the response passes assertions