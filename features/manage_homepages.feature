Feature: Interaction with the homepage
  In order to donate
  user
  wants to be able to select causes
  
  Scenario: Donating without logging in
    Given I am not logged in
    And I land on the homepage
    When I click 2 causes 
    Then I should the 2 causes in the heart
    When I click on "Pay"
    Then I should see a modal box listing 6 organisations
    When I click on "Finish"
    Then I should be redirected to paypal

  @culerity
  Scenario: Delete homepage
    Given the following homepages:
      ||
      ||
      ||
      ||
      ||
    When I delete the 3rd homepage
    Then I should see the following homepages:
      ||
      ||
      ||
      ||
