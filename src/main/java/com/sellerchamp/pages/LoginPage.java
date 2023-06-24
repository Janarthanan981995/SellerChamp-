package com.sellerchamp.pages;

import com.sellerchamp.driver.DriverManager;
import com.sellerchamp.enums.WaitStrategy;

import org.openqa.selenium.By;

public final class LoginPage extends BaseClass {

    private final By emailInput = By.xpath("//input[@id='user_email']");
    private final By passwordInput = By.cssSelector("#user_password");
    private final By signInBtn = By.cssSelector("input[value='Sign in']");


    public LoginPage enterEmail(String email){
        sendKeys(emailInput, email, WaitStrategy.PRESENCE);
        return this;
    }

    public LoginPage enterPassword(String pwd){
        sendKeys(passwordInput, pwd, WaitStrategy.PRESENCE);
        return this;
    }

    public HomePage clickSignIn(){
        click(signInBtn, WaitStrategy.CLICKABLE);
        return new HomePage();
    }
}
