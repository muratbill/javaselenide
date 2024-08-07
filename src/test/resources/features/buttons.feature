Feature: Buttons

  Scenario: Dynamic Click Button Click Me
    Given I open the buttons page
    When I click the dynamic button
    Then I should see the dynamic click message