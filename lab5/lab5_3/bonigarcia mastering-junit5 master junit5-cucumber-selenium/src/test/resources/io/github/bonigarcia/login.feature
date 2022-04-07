# Feature: Login in practice site

#   Scenario: Successful login
#     When I navigate to "https://bonigarcia.dev/selenium-webdriver-java/login-form.html"
#     And I login with the username "user" and password "user"
#     And I click Submit
#     Then I should be see the message "Login successful"

#   Scenario: Failure login
#     When I navigate to "https://bonigarcia.dev/selenium-webdriver-java/login-form.html"
#     And I login with the username "bad-user" and password "bad-password"
#     And I click Submit
#     Then I should be see the message "Invalid credentials"

Feature: Made a purchase

  Scenario: Verify reserves
  When I start a reserve
  And I choose "Philadelphia" as my departure
  And I choose "Berlin" as my destination
  And I click Find Flights
  Then Reserve Page should be opened