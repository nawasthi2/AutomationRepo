Feature: Crypto Currencies


@crypto_currency @REST
Scenario Outline: Convert the currencies from <FromCurrency> to <ToCurrency>
Given url <GetInfoUrl> 
Then Get the Ids of bitcoin (BTC), usd tether (USDT), and Ethereum (ETH) 
And convert <FromCurrency> to <ToCurrency>, using the <ConversionUrl> 

 Examples:
 | FromCurrency   | ToCurrency                | GetInfoUrl              | ConversionUrl                  | 
 | "BTC"          | "BOLI"                      | '/v1/cryptocurrency/map'| '/v1/tools/price-conversion' | 
 | "USDT"         | "BOLI"                      | '/v1/cryptocurrency/map'| '/v1/tools/price-conversion' | 
 | "ETH"          | "BOLI"                      | '/v1/cryptocurrency/map'| '/v1/tools/price-conversion' | 
 
 
 @crypto_currency @REST
Scenario: Retrieve Currency from cryptocurrency/info and validate response
Given A consumer hits '/v1/cryptocurrency/info?id=1027' call for '1027'
Then Confirms logo URL is 'https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png'
And  Confirms the technical_doc Url is 'https://github.com/ethereum/wiki/wiki/White-Paper'
And  Confirms the symbol of the currency is 'ETH'
And  Confirms the date_added of the currency is '2015-08-07T00:00:00.000Z'
And  Confirms the tag associated of the currency is 'mineable'


@crypto_currency @REST
Scenario Outline: Retrieve tag from /v1/cryptocurrency/info and validate
Given A consumer hits '/v1/cryptocurrency/info?id=' <currencyID>
And  Confirms the tag associated of the currency is 'mineable'

 Examples:
 | currencyID    |
 |        '1'      |
 |        '2'      |
 |        '3'      |
 |        '4'      |
 |        '5'      |
 |        '6'      |
 |        '7'      |
 |        '8'      |
 |        '9'      |
 |       '10'      |