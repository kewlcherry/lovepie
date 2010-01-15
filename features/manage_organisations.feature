Feature: View organisations
  In order to view organisations
  non registered users
  wants to see all active organisations

  Scenario: View all organisation
    Given the following active organisation names: "o1 o4"
    And the following inactive organisation names: "o24 o3"
    And I am on the organisation page
    Then I should see "o1"
    And I should see "o4"
    And I should not see "o24"
    And I should not see "o3"

  Scenario: View all organisation ordered by number of causes
    Given the following active organisation names: "o1 o2 o3 o4"
    And organisation "o1" has been tagged 4 times
    And organisation "o2" has been tagged 10 times
    And organisation "o3" has been tagged 2 times
    Then I should see "o2" first
    And I should see "o3" last
