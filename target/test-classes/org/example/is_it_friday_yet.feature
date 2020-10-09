Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario: Sunday isn't Friday
    Given today is Sunday
    When I ask whether it's Friday yet
    Then I should be told "Nope"

  Scenario: Friday is Friday
    Given today is Friday
    When I ask whether it's Friday yet
    Then I should be told "TGIF"

#    this runs for every example in the table below.
  Scenario Outline: Is it Friday
    Given today is "<day>"
    When I ask whether it's Friday yet
    Then I should be told "<expected>"

    Examples:
      | day    | expected |
      | Friday | TGIF     |
      | Sunday | Nope     |


