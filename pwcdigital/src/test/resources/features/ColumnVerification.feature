Feature: Validate PwCDigital

  @Sanity
  Scenario Outline: Column verification of Homepage
    Given I navigate to the PwC Digital Pulse website
    When I am viewing the "<Page-name>" page
    Then I am presented with "<ColNum>" columns of articles
    And The Left "<LCol>" column is displaying "<LeftColumnArticles>" articles
    And the Middle "<MCol>" column is displaying "<MiddleColumnArticles>" articles
    And The Right "<RCol>" is displaying "<RightColumnArticles>" articles
    Examples:
    | Page-name | ColNum | LCol| MCol | RCol |LeftColumnArticles | MiddleColumnArticles | RightColumnArticles |
    | Home      | 3      | 2      | 1         | 3        | 2     | 1        | 4                   |

    @Regression
    Scenario Outline: Contact Us verification
      Given I navigate to the PwC Digital Pulse website
      And I am viewing the "<Page-name>" page
      When I click on the Subscribe navigation link
      Then I am taken to the Subscribe page
      And I am presented with the below fields
        | Field | Required | Type |
        | First name  |true |text  |
        |Last name    |true | text |
        |Organisation |true | text |
        |Job title    |true | text |
        |Business email address| true | email |
        |State                 | true  | dropdown |
        |Country               |true   | dropdown |
      And I will need to complete Google reCAPTCHA before I can complete my request

      Examples:
        | Page-name |
        | Home      |

    @Regression
    Scenario Outline: Search Result verification
      Given I navigate to the PwC Digital Pulse website
      When I click on the Magnifying glass icon to perform a search
      And I enter the text "<TextToEnter>"
      And I submit the search
      Then I am taken to the search results page
      And I am presented with at least "<MinNoResults>" search result
      Examples:
        | TextToEnter | MinNoResults |
        | Single page applications      | 1 |