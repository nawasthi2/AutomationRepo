Automated test example in Java with Cucumber, Selenium WebDriver and rest Assured

This project is an example of UI and REST automated functional test for coinmarketcap UI and REST using Selenium, REST Assured and Cucumber.

Test scenarios are described in the feature files located here .src\test\resources\com\coinmarketcap\test\sample.

## Installation ##

You need to have [Java 11 ] installed along with [maven](https://maven.apache.org/download.cgi).

Import project and a maven project, do maven update the project to install all dependencies, run 

```console
$ mvn clean install
```

## Running tests for UI ##

```console
$ mvn test -Dmode=UI -Dbrowser=chrome -Dcucumber.options="--tags @UI"
```

## Running tests for REST ##

```console
$ mvn test -Dmode=REST -Dcucumber.options="--tags @REST"
```

## Results ##

You can get the results after the run in target\automation-results-html location with the file name index.html