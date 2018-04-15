@Int
Feature: check all the links in the header work fine

  @id_check_links @pass
  Scenario: click all the links in the header jump to the correct url and pages

    Given I should be on the mobile page
    Then I click alert button
  #  Then I should see a pop up window