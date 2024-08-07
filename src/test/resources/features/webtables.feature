Feature: Web Tables

  Scenario: Add and edit a row in the web table
    Given I open the web tables page
    When I add a new row with the below data
      | firstName | lastName | email          | age | salary | department |
      | Can       | Bar      | cb@example.com | 23  | 12330  | Sales      |
    Then I should see the added row in the table
    When I edit the last row with the below data
      | firstName | lastName | email          | age | salary | department  |
      | Karl      | Marx     | km@example.com | 30  | 17400  | Engineering |
    Then I should see the edited row in the table
