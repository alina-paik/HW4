package org.example.hw4;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;


public class CheckColor {
  private WebDriver webDriver;

  @BeforeTest
    public void before() {
      webDriver = new ChromeDriver();
      webDriver.manage().window().maximize();
      webDriver.get("https://dou.ua/");
  }

  @Test
  public void testColorChange() {
      WebElement salary = webDriver.findElement(By.xpath("//a[@href='https://jobs.dou.ua/salaries/']"));

      Actions actions = new Actions(webDriver);
      actions.moveToElement(salary).perform();

      WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
      WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://jobs.dou.ua/salaries/']")));

      String colorAfterHover = element.getCssValue("color");
      String[] rgbValues = colorAfterHover.replace("rgba(", "").replace(")", "").split(",");

      int red = Integer.parseInt(rgbValues[0].trim());
      int green = Integer.parseInt(rgbValues[1].trim());
      int blue = Integer.parseInt(rgbValues[2].trim());

      Assert.assertEquals(red, 255, "Red color component is not as expected.");
      Assert.assertEquals(green, 0, "Green color component is not as expected.");
      Assert.assertEquals(blue, 0, "Blue color component is not as expected.");
  }


  @AfterTest
    public void after() {
      webDriver.quit();
  }


}
