@petdoc
Feature: Login into petdoc

  Scenario: I should be able to login in petdoc with valid credentials
    Given Navigate to Petdoc
    When A User enters a user kike
    And A User enters a valid password secreto
    And A User clicks in entrar
    Then Application should show succes
