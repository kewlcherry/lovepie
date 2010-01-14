Feature: Manage users
  In order to [goal]
  [stakeholder]
  wants [behaviour]

  Scenario: Register as new user
    Given I am on the homepage
    When I follow "Sign up!"
    Then I should be on the new user page
    When I fill in the following:
      | Login | lover |
      | email | lover@lovep.ie |
      | Password | secret |
      | Password confirmation | secret |

    And I press "Register"
    Then I should be on the homepage
    And I should see "Account registered!"
    And I should see "Logout"
    And I should see "lover"
    
#  @culerity
#  Scenario: Delete user
#    Given the following users:
#      ||
#      ||
#      ||
#      ||
#      ||
#    When I delete the 3rd user
#    Then I should see the following users:
#      ||
#      ||
#      ||
#      ||
