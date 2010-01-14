Feature: Interaction with the homepage
  In order to donate
  user
  wants to be able to select causes

  Scenario: Donating without logging in
    Given there is 10 causes
    And I am not logged in
    And I am on the homepage
    Then I should see "10 causes"

#    When I click 2 causes
#    Then I should the 2 causes in the heart
#    When I click on "Pay"
#    Then I should see a modal box listing 6 organisations
#    When I click on "Finish"
#    Then I should be redirected to paypal