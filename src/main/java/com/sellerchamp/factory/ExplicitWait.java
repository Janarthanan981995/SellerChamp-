package com.sellerchamp.factory;

import com.sellerchamp.driver.DriverManager;
import com.sellerchamp.enums.WaitStrategy;
import com.sellerchamp.main.Framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class ExplicitWait {

    private ExplicitWait(){}
    private static final WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),
            Duration.ofSeconds(Framework.getWaitTime()));


    public static WebElement performExplicitWait(By by, WaitStrategy waitStrategy){
        WebElement element = null;
        if(waitStrategy==WaitStrategy.CLICKABLE){
            element = wait.until(ExpectedConditions.elementToBeClickable(by));
        }else if(waitStrategy == WaitStrategy.PRESENCE){
            element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        }else if(waitStrategy == WaitStrategy.VISIBLE){
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }else if(waitStrategy == WaitStrategy.NONE){
            element = DriverManager.getDriver().findElement(by);
        }
        return element;

    }
}
