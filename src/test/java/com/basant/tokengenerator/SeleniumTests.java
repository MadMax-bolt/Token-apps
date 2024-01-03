package com.basant.tokengenerator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTests {
    private WebDriver driver;
    private String baseUrl;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run headless (without GUI)
        driver = new ChromeDriver(options);

        baseUrl = "http://localhost:8181"; // Your application base URL
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSwaggerEndpoint() {
        driver.get(baseUrl + "/swagger-ui.html");
        String pageContent = driver.getPageSource();
        System.out.println(pageContent + "basant");

        // Example assertion: Check if certain content is present in the response page
        assertTrue(pageContent.contains("OpenAPI definition")); // Modify with your expected content
    }

}
