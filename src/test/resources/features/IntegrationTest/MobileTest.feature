@Int
Feature: check all the links in the header work fine

  @id_check_links @pass
  Scenario: click all the links in the header jump to the correct url and pages

    Given I should be on the launch page
    When I click 'get started' button
    Then I should be on the registration page
    When I click the email image
    Then I should see the email input field show up