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
    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(60));
    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://jobs.dou.ua/salaries/' and contains(@style, 'color: rgb(255, 0, 0)')]")));

    String colorAfterHover = element.getCssValue("color");
    String expectedColor = "rgba(255, 0, 0)";
    Assert.assertEquals(colorAfterHover, expectedColor);
  }


  @AfterTest
    public void after() {
      webDriver.quit();
  }


}
