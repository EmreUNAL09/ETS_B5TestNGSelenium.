package com.eurotech.test.day09_TypeOfWebElements2;

import com.eurotech.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SelectDropDownTest {

    //select class seleniumdan geliyor
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.getDriver("chrome");
        //driver.manage().window().maximize();
    }

    @Test
    public void test1() throws InterruptedException {
        driver.get("https://demoqa.com/select-menu");

        //1 locate your dropdown just like any other web  element with unique locator
        //which has "select" tag name
        WebElement colorDropDown = driver.findElement(By.cssSelector("#oldSelectMenu"));

        //2 create 'Select' object by passing that elements as a constructor
        Select color = new Select(colorDropDown);

        //3 getOptions() --> return all available options from the dropdown
        List<WebElement> colorList = color.getOptions();

        //4 print size of the option
        System.out.println("colorList.size() = " + colorList.size());
        //Verify that size is 11
        Assert.assertEquals(colorList.size(),11);

        //5 print option one by one
        for (WebElement element : colorList) {
            System.out.println("element.getText() = " + element.getText());
        }

        //6 verify that default color is 'Red' --> getFirstSelectedOption()
        String expectedColor = "Red";
        String actualColor = color.getFirstSelectedOption().getText();
        Assert.assertEquals(actualColor,expectedColor,"Verify that default color is Red");

        // *** How to select option from dropdown? ***

        //1.Select using visible text -> selectByVisibleText("")
        Thread.sleep(2000);
        color.selectByVisibleText("Yellow");
        expectedColor = "Yellow";
        actualColor = color.getFirstSelectedOption().getText();   //Yellow birinci sıraya geçtiği için bu şekilde kontrol edebiliriz.
        System.out.println("actualColor = " + actualColor);

        Assert.assertEquals(actualColor,expectedColor);

        //2.Select using INDEX
        Thread.sleep(2000);
        color.selectByIndex(0);
        expectedColor = "Red";
        actualColor = color.getFirstSelectedOption().getText();
        System.out.println("actualColor = " + actualColor);

        Assert.assertEquals(actualColor,expectedColor);

        //3.Select using Value
        Thread.sleep(3000);
        color.selectByValue("7");
        expectedColor = "Voilet";
        actualColor = color.getFirstSelectedOption().getText();
        System.out.println("actualColor = " + actualColor);

        Assert.assertEquals(actualColor,expectedColor);


        //How can we click each of element? -> click();
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}
