In order to donate
users
want to be able to login

Scenario: Logging into lovepie
  Given a user that is "carl"
  When I go to the homepage
  Then I should see "login screen"
  When I follow "login"
  Then I should see "Logged in!"
