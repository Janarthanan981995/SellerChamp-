package com.sellerchamp.qa.tests;

import com.sellerchamp.main.Framework;
import com.sellerchamp.pages.HomePage;
import com.sellerchamp.pages.LoginPage;
import com.sellerchamp.pages.Products;
import com.sellerchamp.utils.FileUtils;
import com.sellerchamp.utils.ReadFile;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FirstTest extends BaseTests {

    private FirstTest() {
    }

    private LoginPage lp;
    private HomePage hp;
    private Products pr;

    @Test
    public void login() throws Exception {
        lp = new LoginPage();
        hp = lp.enterEmail(ReadFile.getValue("email"))
                .enterPassword(ReadFile.getValue("password"))
                .clickSignIn();
    }

    @Test
    public void navigateToProducts() {
        //Sandbox eBay
        pr = hp.clickToSelectWebsiteDD()
                .clickToSelectWebSite("Sandbox eBay")
                .clickOnManageProducts();
    }

    private Map<String, String> map;

    @Test
    public void downloadCSV() throws IOException, InterruptedException {
        String filePath = Framework.getDownloadFilePath();
        File x = new File(filePath);
        if (x.exists()) {
            if (x.delete()) System.out.println("previous execution result of " + filePath + " is deleted");
        }
        pr.searchProduct().clickDownload().clickProductOption().clickSubmit();
        Thread.sleep(10_000);

        File f = new File(filePath);
        map = FileUtils.csvToMap(f);
        System.out.println(map);
        Map<String, String> uimap = uimap();
        Assert.assertTrue(areMapsEqual(map, uimap));


    }

    private boolean areMapsEqual(Map<String, String> first, Map<String, String> second) {
        if (first.size() != second.size()) return false;
        return first.entrySet().stream().allMatch(e -> e.getValue().equals(second.get(e.getKey())));
    }

    private Map<String, String> uimap;

    private Map<String, String> uimap() {
        uimap = new HashMap<>();
        
 
        uimap.put("MIN PRICE", pr.getPrices("min_price"));

        uimap.put("SKU", pr.getSKU());
        uimap.put("ALT SKU", pr.getAltSKU());
        uimap.put("ASIN", pr.getFieldDetails("asin"));
        uimap.put("UPC", pr.getFieldDetails("upc"));
        uimap.put("ITEM LOCATIONS", pr.getFieldDetails("item_locations"));
        uimap.put("QUANTITY AVAILABLE", pr.getPrices("quantity_available"));
        uimap.put("ITEM CONDITION", pr.getFieldByLabel("Item Condition:"));
        uimap.put("RETAIL PRICE", pr.getPrices("retail_price"));
        uimap.put("QUANTITY LISTED", pr.getPrices("quantity_available"));
        uimap.put("MAX PRICE", pr.getPrices("max_price"));
        uimap.put("COST PRICE", pr.getFieldDetails("cost_price"));
        uimap.put("NET PROFIT", pr.getFieldByLabel("Net Profit:"));
        uimap.put("EBAY ITEM CONDITION TEXT", pr.getFieldByLabel("eBay Condition:"));
        uimap.put("LISTING FORMAT", pr.getFormDetailsFormatPrice());
        //uimap.put("HANDLING TIME", pr.getFormDetailsHandlingTime());

        uimap.put("MARKETPLACE ACCOUNT", "Sandbox eBay ");
        uimap.put("MARKETPLACE", "Ebay");
        uimap.put("MARKETPLACE STATUS", "Active");
        uimap.put("MARKETPLACE ID", "110554175944");
        uimap.put("EBAY CONDITION ID", "1000");
        System.out.println("================== UI MAP=================");
        System.out.println(uimap);
        return uimap;
    }
}
