package WeatherCompare;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Weather24
{
    WebDriver driver;

    public Weather24(WebDriver driver)
    {
        this.driver = driver;
        String baseURL = "http://weather.24.com/sa/east-london";
        driver.get(baseURL);
    }

    public String[] getDetailsLow() throws InterruptedException
    {
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

        String[] daysLow = new String[4];
        String temp;
        int pos;
        int c = 3;
        for (int x = 0;x<4;x++)
        {
            temp = driver.findElement(By.xpath("//*[@id='div7DayForecast']/div/div/div["+c+"]")).getText();
            pos = temp.indexOf('C');
            daysLow[x] =temp.substring(pos-3,pos-1);
            c++;
        }
        return daysLow;
    }


    public String[] getDetailsHigh() throws InterruptedException
    {
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        String[] daysHigh = new String[4];
        String temp;
        int pos2;
        int c = 3;
        for (int x = 0;x<4;x++)
        {
            temp = driver.findElement(By.xpath("//*[@id='div7DayForecast']/div/div/div["+c+"]")).getText();
            pos2 = temp.lastIndexOf('C');
            daysHigh[x] =temp.substring(pos2-3,pos2-1);
            c++;
        }
        return daysHigh;
    }
}
