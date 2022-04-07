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

  Scenario: Make a purchase
  When I start a reserve
  And I choose "Philadelphia" as my departure
  And I choose "Berlin" as my destination
  And I click Find Flights
  Then Reserve Page should be opened
  And I click on the flight button number 3
  Then Purchase Page should be opened
  And I insert "Lucius Vinicius" as my name, "Rua Sao Sebastiao" as my address, "Aveiro" as my city, "Aveiro" as my state, 12321 as my zip code, "American Express" as my card type, 123321 as my credit card number, 12 as month, 2021 as year, "Lucius Vinicius" as my name on card
  And I click on remember me checkbox
  And I click on Purchase Flight
  Then Confirmation Page should be opened

