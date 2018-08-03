package WeatherCompare;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Runner
{
    WebDriver acuDriver,weather24Driver;

    @Before
    public void setUpBeforeTest()
    {
        System.setProperty("webdriver.chrome.driver", "C:/Drivers/ChromeDriver/chromedriver.exe");
        acuDriver = new ChromeDriver();
        weather24Driver = new ChromeDriver();

    }

    @Test
    public void getWeather() throws InterruptedException {
        AcuWeatherClass acuObj = new AcuWeatherClass(acuDriver);
        Weather24 w24Obj = new Weather24(weather24Driver);
        String[] daysLow24 = w24Obj.getDetailsLow();
        String[] daysHigh24 = w24Obj.getDetailsHigh();
        String[] daysHighAcu = acuObj.getHigh();
        String[] daysLowAcu = acuObj.getLow();
        boolean[] resultsMin = new boolean[4];
        boolean[] resultsMax = new boolean[4];
        for (int x = 0;x<4;x++)
        {
            resultsMax[x] = daysHigh24[x].equals(daysHighAcu[x]);
            resultsMin[x] = daysLow24[x].equals(daysLowAcu[x]);


            //System.out.println("Day: "+x+"High Acu "+daysHighAcu[x]+" High weather24 "+daysHigh24[x]);
            //System.out.println("Day: "+x+"Low Acu "+daysLowAcu[x]+" High weather24 "+daysLow24[x]);
        }

        XMLCreator objXML = new XMLCreator();
        try {
            objXML.createXML(daysLowAcu,daysHighAcu,daysLow24,daysHigh24,resultsMin,resultsMax);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }


    }
    @After
    public void tearDown()
    {
        acuDriver.quit();
        weather24Driver.quit();
    }
}
