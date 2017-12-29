package J;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleEngine {


    protected WebDriver _driver;

    public GoogleEngine(WebDriver driver) {
        _driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "btnK")
    private WebElement SubmitSearch;

    @FindBy(id = "lst-ib")
    private WebElement TextLine;

    @FindBy(id = "pnnext")
    private WebElement NextPage;


    public GoogleEngine Search(String request) {
        System.out.println("Search for \"" + request + "\"");
        SetSearch(request).SearchButtonPress();
        return this;
    }

    public GoogleEngine SetSearch(String request) {
        if (request == null) return this;
        TextLine.sendKeys(request);
        return this;
    }


    public GoogleEngine SearchButtonPress() {
        SubmitSearch.sendKeys(Keys.ENTER);
        return this;
    }

    public GoogleEngine GoToNext() {
        NextPage.sendKeys(Keys.ENTER);
        return this;
    }


    public GoogleEngine PageLinkSearch(String link) throws Exception {
        int pageNumber = 1;


        if (!_driver.findElement(By.id("pnnext")).getText().contains("Следующая")) {
            System.out.println("No pages or captcha");
            assert (false);
        }

        boolean notFinded = true;
        do {

            for (int f = 0; f < _driver.findElements(By.className("_Rm")).size(); f++) {
                if (notFinded && _driver.findElements(By.className("_Rm")).get(f).getText().contains(link)) {
                    System.out.println("Your link \"" + link + "\" on page: " + pageNumber);
                    System.out.println("On first page: " + ((pageNumber == 1) ? "True" : "False"));
                    notFinded = false;
                }
            }

            if (_driver.findElements(By.className("navend")).get(1).getText().contains("Следующая")) {
                GoToNext();
                pageNumber++;
            }


        } while (notFinded && _driver.findElements(By.className("navend")).get(1).getText().contains("Следующая"));


        if (notFinded) {
            System.out.println("\""+link + "\" was not found");
        }


        return this;
    }


}
