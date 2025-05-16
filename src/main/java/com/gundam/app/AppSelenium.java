package com.gundam.app;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class AppSelenium {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");

        // Set up WebDriver (Ensure ChromeDriver is in your PATH or specify its location)
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the USPS login page
            driver.get("https://cop-sit.usps.com/");

            // Click "Log In" button
            WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/app-splash-page/div/div/div[1]/div[1]/div[3]/div[3]/button"));
            loginButton.click();

            Thread.sleep(3000);
            
            // Click "Create New Account" link
            WebElement createAccountLink = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/main/div/div/div[1]/section/div/div/div/div[4]/div/div/div[3]/a"));
            createAccountLink.click();

            // Fill "Email Address" placeholder
            WebElement emailField = driver.findElement(By.xpath("//input[@placeholder='Email Address']"));
            emailField.click();
            emailField.sendKeys("chaosbusters@usps.gov");
            emailField.sendKeys(Keys.ENTER); // require to enable the "Submit" button

            // Assert that the headings and text are visible
            WebElement heading = driver.findElement(By.xpath("//h1[contains(text(),'Create Your USPS.com Business')]"));
            WebElement text = driver.findElement(By.xpath("//p[contains(text(),'With a business account, you')]"));

            if (heading.isDisplayed() && text.isDisplayed()) {
                System.out.println("Page assertions passed.");
            } else {
                System.err.println("Page assertions failed.");
            }

            // Pause for demonstration purposes - need a better handling
            Thread.sleep(20000); 

            String scriptPath = "src/main/resources/extractValidationLink.py";
            // Check if the Python script exists
            File scriptFile = new File(scriptPath);
            if (!scriptFile.exists()) {
                System.err.println("Python script not found at: " + scriptPath);
                return; // Exit the program if the file is missing
            }

            // Execute the Python script extractValidationLink.py and capture the output URL
            String url = executePythonScript(scriptPath);
            System.out.println("URL = " + url);
            if (url != null && !url.isEmpty()) {
                driver.navigate().to(url);

                // Pause for demonstration purposes
                Thread.sleep(10000);

            } else {
                System.out.println("Python script did not return a valid URL.");
            }
        } finally {
            // Close the browser
            driver.quit();
        }
    }

    // Method to execute a Python script and capture its output
    private static String executePythonScript(String scriptPath) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", scriptPath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return output.toString();
            } else {
                System.err.println("Python script execution failed with exit code: " + exitCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}