Feature: Home page


  @home_page @home_page_display @UI
  Scenario Outline: Verify that 100 rows are displayed
    Given A user navigates to HomePage and selects <showRows>
    Then <showRows> rows are displayed
    Examples:
       | showRows |
       | 100 |


 @home_page @home_page_display @UI
  Scenario Outline: Verify filtered records are returning correct results
    Given A user navigates to HomePage and click on filter button and add filter by <MarketCap> and <Price>
    Then results should be filtered as per the <MarketCap> and <Price> range provided.
    Examples:
      | MarketCap                     | Price       |
      | "1000000000:10000000000"      | "101:1000" |

