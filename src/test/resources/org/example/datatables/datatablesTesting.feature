Feature: Basic tests on w3schools Html Table page
  Everybody wants to know when it's Friday

#  Initial setup for each test
  Background: This will load w3schools page only once before looping over the examples
    Given I'm on datatables zero configuration webpage

#    Search Something
  Scenario Outline: Search for things
    When I search "<name>"
    Then I should have "<nr_answers>" answers

    Examples:
      | name        | nr_answers |
      | Thor Walton | 1          |
      | Developer   | 8          |