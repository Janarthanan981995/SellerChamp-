package com.sellerchamp.factory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.sellerchamp.enums.GetTextStragey;

public final class TextFactory {
    private TextFactory(){}

    private static String value;
    public static String getTextFromElement(WebElement element, GetTextStragey gts){
        if(gts == GetTextStragey.TEXT){
            value = element.getText();
        }else if(gts == GetTextStragey.ATTRIBUTE){
            value = element.getAttribute("value");
        }else if (gts == GetTextStragey.SELECTED){
            Select sel = new Select(element);
            value = sel.getFirstSelectedOption().getText();
        }
        return value;
    }
}
