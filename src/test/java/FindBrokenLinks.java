

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;


import io.github.bonigarcia.wdm.WebDriverManager;

public class FindBrokenLinks {


	@Test
	public void findBrokenLinks() {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.wikipedia.org/");

		List<WebElement> links = driver.findElements(By.tagName("a"));
		List<String> urls = new ArrayList<String>();

		for(WebElement ele: links) {

			if(ele.getAttribute("href")!=null) {
				urls.add(ele.getAttribute("href"));
			}

		}
		
		System.out.println("the number of links on the page are: "+urls.size());

		int count=0;
		for(String url: urls) {
			int responseCode =given().when().get(url).andReturn().statusCode();
				if(responseCode!=200) {
					System.out.println("The broken link is "+url);
				}else {
					count++;
					System.out.println("the working link is"+url);
				}
		}
		
		System.out.println("the number of links on the page are: "+urls.size());
		System.out.println("the num of working links on the page are "+count);
	
	}
}
