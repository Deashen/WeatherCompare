package WeatherCompare;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AcuWeatherClass
{
    WebDriver driver;

    public AcuWeatherClass(WebDriver driver)
    {
        this.driver = driver;
        String baseURL = "https://www.accuweather.com/en/za/east-london/304830/daily-weather-forecast/304830";
        driver.get(baseURL);
    }

    public String[] getLow()
    {
        driver.manage().timeouts().implicitlyWait(120,TimeUnit.SECONDS);
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id='panel-main']/div[2]/div/div/div[2]/ul/li"));

        String[] daysLow = new String[4];

        for(int i =0; i<4; i++){
            daysLow[i] = rows.get(i+1).findElement(By.className("small-temp")).getAttribute("innerHTML");
            daysLow[i] = daysLow[i].substring(1,daysLow[i].length()-1);
        }
        return daysLow;
    }

    public String[] getHigh()
    {
        driver.manage().timeouts().implicitlyWait(120,TimeUnit.SECONDS);
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id='panel-main']/div[2]/div/div/div[2]/ul/li"));

        String[] dayshigh = new String[4];

        String temp;
        for(int i =0; i<4; i++){
            dayshigh[i] = rows.get(i+1).findElement(By.className("large-temp")).getAttribute("innerHTML");
            dayshigh[i] = dayshigh[i].substring(0,dayshigh[i].length()-1);
        }
        return dayshigh;
    }

}
