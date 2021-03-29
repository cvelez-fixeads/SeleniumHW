# Selenium Hello World
How to create Gradle project with Selenium and Cucumber

## Dependencies
- JAVA 8 
- Chrome Driver (check your chrome version and download from [here](https://chromedriver.chromium.org/downloads))

Downloaded file must be set here:
resources/chromedriver_standalone/

## Download Project
```
git clone git@github.com:cvelez-fixeads/SeleniumHW.git
```

## Run tests in IDEA
Just click right in testSelenium.feature file and RUN

## Run tests command line
```
./gradlew seleniumTest
```
Gradlew is an embedded gradle container set into project to make easier to operate.
If you have gradle installed locally you can just do
```
gradle seleniumTest
```

## Generate Report  command line
```
./gradlew seleniumTest
```
Report will be generated here:
/build/cucumber-report/report/cucumber-html-reports/overview-features.html