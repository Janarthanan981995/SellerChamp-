package com.sellerchamp.pages;

import com.sellerchamp.driver.DriverManager;
import com.sellerchamp.enums.WaitStrategy;

import org.openqa.selenium.By;

public final class HomePage extends BaseClass {

    private final By websiteDD = By.xpath("//li[@id='navitems-settings'][1]/a");
    private final By productsForm = By.xpath("//*[contains(text(),'Manage Products')]");//ancestor::a");


    public HomePage clickToSelectWebsiteDD() {
        click(websiteDD, WaitStrategy.CLICKABLE );
        return this;
    }


    public HomePage clickToSelectWebSite(String website) {
        String postSelectWebsite = "')]";
        String preSelectWebSite = "//li[@id='navitems-settings']//li//a[contains(.,'";
        click(By.xpath(preSelectWebSite + website + postSelectWebsite),WaitStrategy.CLICKABLE);
        return this;
    }

    public Products clickOnManageProducts() {
        click(productsForm, WaitStrategy.PRESENCE);
        return new Products();
    }


}
