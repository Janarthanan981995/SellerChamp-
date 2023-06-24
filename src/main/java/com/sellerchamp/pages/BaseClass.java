package com.sellerchamp.pages;

import com.sellerchamp.driver.DriverManager;
import com.sellerchamp.enums.GetTextStragey;
import com.sellerchamp.enums.WaitStrategy;
import com.sellerchamp.factory.ExplicitWait;
import com.sellerchamp.factory.TextFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class BaseClass {
    protected void sendKeys(By by, String text, WaitStrategy ws){
        ExplicitWait.performExplicitWait(by, ws).sendKeys(text);
    }

    protected void click(By by, WaitStrategy ws){
        ExplicitWait.performExplicitWait(by, ws).click();
    }

    protected String getAttribute(By by, WaitStrategy ws){
        return ExplicitWait.performExplicitWait(by, ws).getAttribute("value");
    }

    protected WebElement getElement(By by, WaitStrategy ws){
        return ExplicitWait.performExplicitWait(by, ws);

    }

    protected String getText(By by, WaitStrategy ws){
        return ExplicitWait.performExplicitWait(by, ws).getText();
    }

    protected String getTextBy(GetTextStragey gts, WebElement element){
        return TextFactory.getTextFromElement(element, gts);
    }

    protected void scrollToElement(By by){
        try{
            Thread.sleep(4000);
        }catch(Exception ignored){}
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        WebElement ele = DriverManager.getDriver().findElement(by);
        js.executeScript("arguments[0].scrollIntoView(true);", ele);
    }





}
