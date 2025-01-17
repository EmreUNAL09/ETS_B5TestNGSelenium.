package com.eurotech.test.day12_ActionJS;

import com.eurotech.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ActionTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.getDriver("chrome");

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //WebDriverWait wait = new WebDriverWait(driver, 15);

    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        //driver.close();
    }

    @Test
    public void hoverOver() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/hovers");

        WebElement img2 = driver.findElement(By.xpath("(//img)[3]"));

        //Action  --> class that contains all the user interactions
        Actions actions = new Actions(driver);
        Thread.sleep(2000);


        //Move your mouse to the web element (hover over)
        //Perform the action, complete the action
        actions.moveToElement(img2).perform();


        WebElement view_profile2 = driver.findElement(By.linkText("View profile"));

        System.out.println("view_profile2.getText() = " + view_profile2.getText());

        Assert.assertTrue(view_profile2.isDisplayed(),"Verify that element is DISPLAYED");

    }

    @Test
    public void dragAndDrop() {
        driver.get("https://demoqa.com/droppable");

        WebElement source = driver.findElement(By.cssSelector("#draggable"));

        WebElement target = driver.findElement(By.xpath("(//div[@id='droppable'])[1]"));


        Actions actions = new Actions(driver);

        actions.dragAndDrop(source,target).perform(); //drag and drop için 1. metod


        WebElement verifyMessage = driver.findElement(By.xpath("//p[text()='Dropped!']"));

        System.out.println("verifyMessage.getText() = " + verifyMessage.getText());

        //first way
        Assert.assertEquals(verifyMessage.getText(),"Dropped!","Ver,fy that element has dropped");

        //second way
        Assert.assertTrue(verifyMessage.isDisplayed());

    }
    @Test
    public void dragAndDrop2(){
        driver.get("https://demoqa.com/droppable");

        WebElement source = driver.findElement(By.cssSelector("#draggable"));

        WebElement target = driver.findElement(By.xpath("(//div[@id='droppable'])[1]"));


        Actions actions = new Actions(driver);

        actions.moveToElement(source).clickAndHold().moveToElement(target).pause(3000).release().perform();  //drag and drop için 2. metod


        WebElement verifyMessage = driver.findElement(By.xpath("//p[text()='Dropped!']"));

        System.out.println("verifyMessage.getText() = " + verifyMessage.getText());


        //second way
        Assert.assertTrue(verifyMessage.isDisplayed());

    }

}
