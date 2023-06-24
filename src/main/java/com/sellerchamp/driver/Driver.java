package com.sellerchamp.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.sellerchamp.utils.ReadFile;

import java.time.Duration;
import java.util.Objects;

public final class Driver {


    private Driver(){}
    public static void initDriver() throws Exception {
        if(Objects.isNull(DriverManager.getDriver())){
            ChromeOptions co = new ChromeOptions();
            co.addArguments("--ignore-ssl-errors=yes");
            co.addArguments("--ignore-certificate-errors");

            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver(co);
            DriverManager.setDriver(driver);
            DriverManager.getDriver().get(ReadFile.getValue("url"));
            DriverManager.getDriver().manage().window().maximize();
            DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }

    }

    public static void quitDriver(){
        if(Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unload();

        }

    }
}
