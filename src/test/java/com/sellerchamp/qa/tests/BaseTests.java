package com.sellerchamp.qa.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.sellerchamp.driver.Driver;

public class BaseTests {

    protected BaseTests(){

    }

    @BeforeTest
    public void startUp() throws Exception {
        Driver. initDriver();

    }

    @AfterTest
    public void tearDown(){
        Driver.quitDriver();

    }
}
