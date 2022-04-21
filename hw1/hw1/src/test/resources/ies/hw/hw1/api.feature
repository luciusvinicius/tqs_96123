Feature: API
    Background: The Website
        Given the website just opened
    
    Scenario Outline: Search with API
        When I choose for the <api> API
        And I choose the country <country>
        And I choose the date range between 12 and 18 of April
        Then new deaths on day <day> is <number_deaths>
        
        Examples:
            | api       | country       | day | number_deaths |
            | "Api 1"   | "Brazil"      | 12  | 104           |
            | "Api 1"   | "Argentina"   | 18  | 17            |
            | "Api 2"   | "Brazil"      | 14  | 139           |


