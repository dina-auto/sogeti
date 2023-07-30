# Sogeti Automation 
This repository contains a Java-based automation project using Selenium, Cucumber, JUnit, Apache POI, RestAssured and Maven. The project is designed to perform end-to-end automated testing of a web application and API automation.

## Prerequisites
Before you get started, make sure you have the following installed on your machine:

* Java Development Kit (JDK) - Version 8 or higher
* Apache Maven - Version 3.6.x or higher
* An Integrated Development Environment (IDE) such as Eclipse or IntelliJ IDEA

## Getting Started

To get started with the project, follow these steps:
   - 1.	Clone the repository to your local machine:
   - 2.	Open the project in Eclipse IDE.
   - 3.	Use JDK instead of the JRE as Project execution environment. This way, you will have all the necessary tools to compile and run the project
   - 4.	Ensure that the required dependencies are resolved by running Maven update project
   - 5.  Open the TestRunner.java class located at src/test/java/runner/TestRunner.java 
   - 6.	Right-click on the class and select "Run as" > "JUnit Test".
     7.	Right click on pom.xml file and Run as Maven clean install (It will run the suite and creates the jar)
The automation suite will now run, and you can view the test execution results in the IDE's JUnit runner.

## Reporting
Test execution reports are generated automatically using Cucumber's built-in reporting feature. After running the tests, you can find the HTML reports in the target/cucumber-reports directory.
