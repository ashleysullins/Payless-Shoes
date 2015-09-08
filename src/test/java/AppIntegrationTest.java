import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AppIntegrationTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Find Yo Shoes");
  }
  
  @Test
  public void storeIsCreated() {
    Store myStore = new Store("PDX Payless", "100 SW Main", "1-800-632-SHOES");
    myStore.save();
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("PDX Payless", "100 SW Main", "1-800-632-SHOES");
  }

  @Test
  public void brandIsCreated() {
    Brand myBrand = new Brand ("Adidas");
    myBrand.save();
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Adidas");
  }

  @Test
  public void storeIsUpdated() {
    Store myStore = new Store ("PDX Payless", "100 SW Main", "1-800-632-SHOES");
    myStore.save();
    myStore.update("Seattle Payless", "200 N Freemont", "1-800-543-SHOES");
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Seattle Payless", "200 N Freemont", "1-800-543-SHOES");
  }
  
  @Test
  public void storeIsAssociatedWithBrand() {
  Brand myBrand = new Brand("Adidas");
  myBrand.save();
  Store myStore = new Store("PDX Payless", "100 SW Main", "1-800-632-SHOES");
  myStore.save();
  myBrand.addStore(myStore);
  
  String brandPath = String.format("http://localhost:4567/brands/%d", myBrand.getId());
  
  goTo(brandPath);
  assertThat(pageSource()).contains("Adidas", "PDX Payless", "100 SW Main", "1-800-632-SHOES");
  }
  
  @Test
  public void brandIsAssociatedWithStore() {
  Brand myBrand = new Brand("Adidas");
  myBrand.save();
  Store myStore = new Store("PDX Payless", "100 SW Main", "1-800-632-SHOES");
  myStore.save();
  myStore.addBrand(myBrand);
  
  String storePath = String.format("http://localhost:4567/stores/%d", myStore.getId());
  
  goTo(storePath);
  assertThat(pageSource()).contains("Adidas", "PDX Payless", "Adidas");
  }
}