package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;


public class Test {

    public static WebDriver driver = null;

    public static void main(String[] args) throws InterruptedException {
        
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        System.out.println("# opening the site");
        
        driver.navigate().to("https://jupiter.cloud.planittesting.com/#/");
        driver.manage().window().maximize();

        String title = driver.getTitle();
        System.out.println(title);
        
        
        System.out.println("# Checking the title of the website");

        if (title.equalsIgnoreCase("Jupiter Toys")) {
            System.out.println("Title matched");
        } else {
            System.out.println("title doesnot match " + title);
        }

        // Navigate to shop 
        System.out.println("Test case-1 : Items added and the cart count are equal.");
        WebElement categoryShop = driver.findElement(By.id("nav-shop"));
        categoryShop.click();

        // ADD products to the cart and count size as per the elements
        double total = 0.0;
        int size = 4;
        for (int i = 1; i <= size; i++) {
            WebElement product = driver.findElement(By.xpath("//*[@id=\"product-" + i + "\"]/div/p/a"));
            System.out.println("Clicked on: " + product.getText());
            product.click();

            WebElement price = driver.findElement(By.xpath("//*[@id=\"product-" + i + "\"]/div/p/span"));
            String priceVal = price.getText().substring(1);
            double cost = Double.parseDouble(priceVal);
            total = total + cost;
        }

        WebElement cartElement = driver.findElement(By.xpath("//*[@id=\"nav-cart\"]/a/span"));
        String cartCount = cartElement.getText();

        int count = Integer.parseInt(cartCount);

        // Check if the size is equal to count of no of elements in the cart
        assert count == size;
        System.out.println("Test case-1: Passed.");
        Thread.sleep(5000);
        
        // Navigate to cart
        System.out.println("Test case-2: Total amount displayed and the sum are equal.");
        WebElement cart = driver.findElement(By.id("nav-cart"));
        cart.click();
     
        WebElement totalCost = driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tfoot/tr[1]/td/strong"));
        double totalAmt = Double.parseDouble(totalCost.getText().substring(6));
        System.out.println(totalAmt);

        // check if the total calc is equal to totalAmt
        assert total == totalAmt;
        System.out.println("Test case-2: Passed.");
        Thread.sleep(5000);
        
        System.out.println("Test case-3: Required field mandatory check, by not giving email details.");
        WebElement checkOutButton = driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tfoot/tr[2]/td/a"));
        checkOutButton.click();

        WebElement foreNameId = driver.findElement(By.id("forename"));
        foreNameId.sendKeys("John");

        WebElement email = driver.findElement(By.id("email"));
      
        WebElement address = driver.findElement(By.id("address"));
        address.sendKeys("10 Landsdowne Pde\nTarneit Suburb\nMelbourne 3029\n(03) 9563 7401");

        Select cardType = new Select(driver.findElement(By.name("cardType")));
        cardType.selectByVisibleText("Visa");

        WebElement cardNum = driver.findElement(By.id("card"));
        cardNum.sendKeys("1234987612349876");

        WebElement submit = driver.findElement(By.id("checkout-submit-btn"));
        submit.click();
        
        System.out.println("Test case-3: Passed.");
        Thread.sleep(5000);
        
        //Validating if email address is given wrong
        System.out.println("Test case-4: Validating Email.");
        email.sendKeys("test@gcom");
        WebElement invalidEmail = driver.findElement(By.id("email-err"));   
        assert invalidEmail.isEnabled();
        
        if(invalidEmail.isEnabled()){
            System.out.println("Test case-4: Passed, but checkout-submit button should be disabled here.");
            Thread.sleep(5000);
        }
        
        //clicking th login button without any values
        System.out.println("Test case-5: Both the fields are blank and Login button clicked.");
        WebElement login = driver.findElement(By.xpath("//*[@id=\"nav-login\"]/ng-login/a"));
        login.click();
        
        WebElement loginButton = driver.findElement(By.xpath("/html/body/div[3]/div[3]/button[1]"));
        loginButton.click();
        
        System.out.println("Test case-5: Passsed, but should change the login fields to mandatory.");
        Thread.sleep(5000);
        
         //login page is cancelled
        WebElement cancelLogin = driver.findElement(By.xpath("/html/body/div[3]/div[3]/button[2]"));
        cancelLogin.click();
         
        //moving to cart
        cart.click();
        System.out.println("Test case-6: Discarding to empty the cart & checking if cart count is still the same.");
        WebElement emptyCart = driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tfoot/tr[2]/td/ng-confirm/a"));
        emptyCart.click();
         // discard emptying the cart
        WebElement discardEmpty = driver.findElement(By.xpath("/html/body/div[3]/div[3]/a[2]"));
        discardEmpty.click();
         
        String cartCount2 = cartElement.getText();
        int count2 = Integer.parseInt(cartCount2);
         
        assert count2 == size;
        System.out.println("Test case-6: Passed.");
        Thread.sleep(5000);
        
        System.out.println("Test case-7: Removing an item from the cart.");
        //getting the item count and 
        WebElement itemCount = driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr[2]/td[3]/input"));
        int removedItemCount = Integer.parseInt(itemCount.getAttribute("value"));
        
        //clicking on remove button and confirming removal
        WebElement remove = driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr[2]/td[5]/ng-confirm/a"));
        remove.click();
        WebElement removeConfirm = driver.findElement(By.xpath("/html/body/div[3]/div[3]/a[1]"));
        removeConfirm.click();
        //getting the cart count displayed
        int count3 = Integer.parseInt(cartElement.getText());
       
        assert count3 == size - removedItemCount ;
        System.out.println("Test case-7: Passed.");
        Thread.sleep(5000);
        
        System.out.println("Test case-8: Checking if checkout button is disabled, when item count is made 0.");
        //cart count of fluffy bunny is changed to 0
        WebElement itemQuantityToZero = driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr[3]/td[3]/input")) ;
        itemQuantityToZero.clear();
        itemQuantityToZero.sendKeys("0");
       
        assert !checkOutButton.isEnabled();
        System.out.println("Test case-8: Passed.");
        Thread.sleep(5000);
      
       System.out.println("Test case-9: Shopping hyperlink check on the cart page.");
       WebElement shoppingLink = driver.findElement(By.xpath("/html/body/div[2]/div/p/a[2]"));
       assert shoppingLink.isEnabled();
       System.out.println("Test case-9: Passed.");
       Thread.sleep(5000);
        
       System.out.println("Test case-10: Confirming to empty the car.t");  
       emptyCart.click();
       WebElement confirmEmpty = driver.findElement(By.xpath("/html/body/div[3]/div[3]/a[1]"));
       confirmEmpty.click();  
       String cartCount4 = cartElement.getText();
       int count4 = Integer.parseInt(cartCount4);
       assert count4 == 0; 
       System.out.println("Test case-10: Passed.");
       Thread.sleep(5000);
        
       driver.close();
    }

   
}
