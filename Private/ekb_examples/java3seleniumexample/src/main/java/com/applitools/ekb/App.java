package com.applitools.ekb;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Hello world!
 *
 */
public  class App
{
    static Eyes eyes;
    static WebDriver driver;
    //String website = "https://applitools.com/helloworld";
    static String website = "file:///C:/Users/User/Dropbox/EKB/eyes-knowledgebase-source/Private/EKB_Examples/helloworld2Example/Applitools.html";
    private static void initialSetup() {
        // Open a Chrome browser.
        driver = new ChromeDriver();
        // Initialize the eyes SDK and set your private API key.
        eyes = new Eyes();
        eyes.setApiKey(System.getenv("APPLITOOL_EXAMPLES_API_KEY"));
        //optional setups
    }

    private static TestResults test1(Eyes eyes, WebDriver driver, BatchInfo batchInfo) {
        TestResults result;
        // Start the test and set the browser's viewport size to 800x600.
        eyes.open(driver, "Hello World!", "Enter Name",
                new RectangleSize(800, 600));

        // Navigate the browser to the "hello world!" web-site.
        driver.get(website);

        // Enter Name
        driver.findElement(By.id("name")).sendKeys("John Smith");

        // Visual checkpoint #1.
        eyes.checkWindow("Enter Name");

        // Click the "Click me!" button.
        driver.findElement(By.id("button")).click();

        // Visual checkpoint #2.
        eyes.checkWindow("Click!");

        // End the test.
        result = eyes.close(false); //false means don't throw an exception
        return result;
    }
    private static TestResults test2(Eyes eyes, WebDriver driver, BatchInfo batchInfo) {
        TestResults result;

        // Start the test and set the browser's viewport size to 800x600.
        eyes.open(driver, "Hello World!", "No Enter Name",
                new RectangleSize(800, 600));

        // Navigate the browser to the "hello world!" web-site.
        driver.get("https://applitools.com/helloworld");

        // Visual checkpoint #1.
        eyes.checkWindow("No name entered");
        // Don't enter name

        // Click the "Click me!" button.
        driver.findElement(By.id("button")).click();

                // Visual checkpoint #2.
         eyes.checkWindow("Click!");

        // End the test.
        result = eyes.close(false); //false means don't throw an exception
        return result;
    }
    private void cleanupAborted(boolean aborted) {
        eyes.abortIfNotClosed();
    }
    public  static void main( String[] args )
    {
        try{
            TestResults result;
            initialSetup();
            BatchInfo   batchInfo = new BatchInfo("Test Hello World Batch");
            result = test1(eyes, driver, batchInfo);
           // result = test2(eyes, driver, batchInfo);
        } finally {

            // If the test was aborted before eyes.close was called, ends the test as aborted.
            if (eyes != null) {
                eyes.abortIfNotClosed();
            }
        }
        // Close the browser.
        driver.quit();
    }
}
