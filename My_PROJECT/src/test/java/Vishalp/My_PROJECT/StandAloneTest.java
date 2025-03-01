package Vishalp.My_PROJECT;

import java.sql.Time;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(5));

		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client");
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='67%'");
		
		Thread.sleep(2000);
//		
		driver.findElement(By.id("userEmail")).sendKeys("vishalpetare12@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Vishal@7719");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
		Thread.sleep(3000);

        driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod=products.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3"))
		.findFirst().orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		
	//	driver.findElement(By.xpath("(//div[@class='card-body']/button[2])[1]")).click();
		
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.xpath("(//*[text()=' Add To Cart'])[2]")).click();
			
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

			//Thread.sleep(5);
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		String productName="ZARA COAT 3";
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));

		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		driver.findElement(By.cssSelector(".user__address input")).sendKeys("India");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='user__address']//button)[2]")));
        driver.findElement(By.xpath("(//*[@class='user__address']//button)[2]")).click();
        
        driver.findElement(By.cssSelector(".actions a")).click();
	
	driver.close();;
	}
}
