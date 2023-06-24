package com.sellerchamp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.sellerchamp.enums.GetTextStragey;
import com.sellerchamp.enums.WaitStrategy;

public final class Products extends BaseClass {

    private final By skuTextFirstRow = By.xpath("//table[@id='products-table']/tbody/tr[1]//input[@data-key='sku']");
    private final By searchBar = By.id("product_query");
    private final By productDownloadBtn = By.id("btn-download-products-toggle");
    private final By DownloadProductOption = By.xpath("//a[normalize-space()='Download Product Report']");

    private final By submitBtn = By.xpath("//input[@name='Submit']");
    private final By enableAltSKUArrow = By.xpath("//i[following-sibling::strong[text()='SKU:']]");
    private final By enableSKUArrow = By.xpath("//i[following-sibling::strong[text()='ALT SKU:']]");
    private final By altskuTextFirstRow = By.xpath("//table[@id='products-table']/tbody/tr[1]//input[@data-key='alt_sku']");

    private final By upcField = By.xpath("//input[@data-key='upc']/parent::div");
    private final String preGetField = "//*[@data-key='";
    private final String postGetField = "']/parent::div";
    private final String postGetFieldPrices = "']";


    private final String preTextField = "//strong[text()='";
    private final String postTextField = "']";


    public String getFieldDetails(String text) {
        String details = getText(By.xpath(preGetField + text + postGetField), WaitStrategy.VISIBLE);
        String value = "";
        if (details.contains(":")) {
            try {
                value = details.split(":")[1].trim();
            } catch (ArrayIndexOutOfBoundsException ignored) {}
        } else {
            value = details;
        }

        return value.trim();
    }

    public String getPrices(String text) {
        String value =  getAttribute(By.xpath(preGetField + text + postGetFieldPrices), WaitStrategy.VISIBLE);
        if(value.contains("$")){
            return value.split("\\$")[1].trim();
        }else{
            return value.trim();
        }
    }

    public String getFieldByLabel(String text) {
        WebElement element = getElement(
                By.xpath(preTextField + text.trim() + postTextField), WaitStrategy.VISIBLE);
        WebElement immediateElement = element.findElements(By.xpath(".//following-sibling::*")).get(0);
        String value = "";
        if (immediateElement.getTagName().equalsIgnoreCase("select")) {
            value =  getTextBy(GetTextStragey.SELECTED, immediateElement);
        } else if (immediateElement.getTagName().equalsIgnoreCase("a")) {
            value =  getTextBy(GetTextStragey.TEXT, immediateElement);
        } else if (immediateElement.getTagName().equalsIgnoreCase("input")) {
            value =  getTextBy(GetTextStragey.ATTRIBUTE, immediateElement);
        }


        if(!value.contains("$")){
            return value.trim();
        }else{
            return value.split("\\$")[1].trim();
        }

    }


    public String getSKU() {
        return getAttribute(skuTextFirstRow, WaitStrategy.VISIBLE).trim();
    }

    private void enableAltSKU() {
        click(enableAltSKUArrow, WaitStrategy.CLICKABLE);
    }

    public String getAltSKU() {
        enableAltSKU();
        return getAttribute(altskuTextFirstRow, WaitStrategy.VISIBLE).trim();
    }

    public String getEbayCondition(String text) {
        WebElement ele = getElement(By.xpath(preGetField + text + postGetField), WaitStrategy.VISIBLE);
        return getTextBy(GetTextStragey.SELECTED, ele).trim();

    }

    public Products searchProduct() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {}

        sendKeys(searchBar, Keys.chord(getSKU(), Keys.ENTER), WaitStrategy.PRESENCE);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {}

        return this;
    }

    public Products clickDownload() {
        click(productDownloadBtn, WaitStrategy.CLICKABLE);

        return this;
    }

    public Products clickProductOption() {
        click(DownloadProductOption, WaitStrategy.CLICKABLE);
        return this;
    }

    public void clickSubmit() {
        click(submitBtn, WaitStrategy.CLICKABLE);
    }

    private final By editbox = By.xpath("//td[contains(@id,'_action_btn')]//i[contains(@class,'icon-pencil')]");
    private final By formatPrice = By.xpath("//div[contains(@class,'product_listing_listing_format')]//input[@checked='checked']");

    
   private final By closeButton = By.xpath("//form[@id='edit_product_listing_6489e04b354ad37115639ea9']//button[@type='button'][normalize-space()='Close']");
  
   
    public String getFormDetailsFormatPrice(){
        clickEditButton();
        scrollToElement(By.xpath("//div[contains(@class,'selling-panel')]//h4"));
        click(By.xpath("//div[contains(@class,'selling-panel')]//h4"),WaitStrategy.CLICKABLE);
        scrollToElement(formatPrice);
        String value = getAttribute(formatPrice, WaitStrategy.VISIBLE);
        clickCloseButon();
        return value.trim();

     // private final By handlingTime = By.xpath("Handling time ");

        //  public String getFormDetailsHandlingTime() {
         //    clickEditButton();
           //  scrollToElement(By.xpath(Handling time)));
             //click(By.xpath(Handling time),WaitStrategy.CLICKABLE);
          //   scrollToElement(handlingTime);
            // String value =  getAttribute(handlingTime, WaitStrategy.VISIBLE);
           //  clickCloseButon();
            // return value.trim();
        // }

    }

    private void clickEditButton(){
        click(editbox,WaitStrategy.CLICKABLE);
        getElement(By.xpath("//span[@id='container']//h4[contains(text(),'Update Item')]"),WaitStrategy.VISIBLE);
    }

    private void clickCloseButon(){
        click(closeButton,WaitStrategy.CLICKABLE);
    }
}
