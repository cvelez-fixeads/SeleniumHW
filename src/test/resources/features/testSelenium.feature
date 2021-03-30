@automation:done @petdoc:facturas
Feature: Test input text in Seleniumeasy

  Scenario: Site accepts input text
    Given Navigate to Page Seleniumeasy
    When A User enters a valid input text popo656
    And A User clicks on Show Message button
    Then Application shows message popo656
