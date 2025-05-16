## Disclaimer
This program is a sample automation script that utilizes Selenium WebDriver in Java, showcasing hybrid automation by integrating Java and Python. It is intended as a reference for educational and demonstration purposes. Please note that additional customization or resources may be required to ensure it functions correctly in specific environments. Use it at your own discretion.

## Prerequistes
1. ChromeDriver Setup: Ensure the ChromeDriver version is matching with your Chrome version.
2. Java Environment: Install Java Development Kit (JDK) version 17 or higher (as specified in the pom.xml).
3. Microsoft Outlook version 16: This provide COM object to extract email content.
4. Python and Dependencies: Install Python on your system to execute the extractValidationLink.py script.
5. Selenium and Other Dependencies: The required Selenium dependencies are managed via Maven. Run mvn clean install to download them.

## Build and Execution
1. Clone the project
   ```
   git clone https://github.com/kent-cheung-usps/selenium_sample.git
   ```
3. Build
   ```
   mvn clean install
   ```
5. Execute
   ```
   java -jar target/selenium-examples-1.0.0-jar-with-dependencies.jar
   ```
