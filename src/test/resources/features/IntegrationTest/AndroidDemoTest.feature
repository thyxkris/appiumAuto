@android
Feature: android

  @id_check_links @pass
  Scenario: android click all the links in the header jump to the correct url and pages

    Given I should be on the VOA Login page
    When I click login button
    Then I should be on the Sample List page
    When I click drag and drop button
    Then I should be on the Drag Drop page
    Then I drag the button 'Drag me!' to 'Drop here'
    Then I should see the text  "Circle dropped"