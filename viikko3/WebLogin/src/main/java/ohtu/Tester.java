package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:4567");
        
        System.out.println(driver.getPageSource());
        
        sleep(2);
        WebElement element = driver.findElement(By.linkText("register new user"));
//        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        
        System.out.println(driver.getPageSource());
        
        sleep(2);
        
        Random r = new Random();
        element = driver.findElement(By.name("username"));
//        element.sendKeys("pekka");
        element.sendKeys("Jukka"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("moimoimoi123");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("moimoimoi123");
        element = driver.findElement(By.name("signup"));

        System.out.println(driver.getPageSource());
        
        sleep(2);
        
        element.submit();
        
        System.out.println(driver.getPageSource());
        
        sleep(2);
        
        
        element = driver.findElement(
                By.linkText("continue to application mainpage"));
        
        element.click();
        
        System.out.println(driver.getPageSource());
        
        sleep(2);

        element = driver.findElement(By.linkText("logout"));
        
        element.click();
        
        System.out.println(driver.getPageSource());

        
        sleep(3);
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
