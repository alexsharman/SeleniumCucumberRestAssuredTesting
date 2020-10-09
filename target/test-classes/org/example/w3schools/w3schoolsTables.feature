Feature: Basic tests on w3schools Html Table page
  Everybody wants to know when it's Friday

#  Initial setup for each test
  Background: This will load w3schools page only once before looping over the examples
    Given I'm on w3schools HTML tables page

#    Testing results in a table:
  Scenario Outline: Checking table values on w3schools.com
    When I check the "<row>" row
    Then It should contain "<name>"
    When I close browser

    Examples:
      | row | name                |
      | 1   | Alfreds Futterkiste |
      | 4   | Island Trading      |

#    Managing child tabs
  Scenario: On w3schools.com click on try it yourself
    When I click on "#main > div:nth-child(7) > a"
    Then It should open new page with url: "https://www.w3schools.com/html/tryit.asp?filename=tryhtml_table_intro"
    Then Page contains back button
    When I close child Tab
    Then I have only one tab open
    When I close browser


