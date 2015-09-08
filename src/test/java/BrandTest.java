import org.junit.*;
import static org.junit.Assert.*;

public class BrandTest {
  
  @Rule
  public DatabaseRule database = new DatabaseRule();
  
  @Test
  public void all_emptyAtFirst() {
    assertEquals(Brand.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfBrandsAretheSame() {
    Brand firstBrand = new Brand("Adidas");
    Brand secondBrand = new Brand("Adidas");
    assertTrue(firstBrand.equals(secondBrand));
  }
  
  @Test
  public void save_assignsIdToObject() {
    Brand myBrand = new Brand("Adidas");
    myBrand.save();
    Brand savedBrand = Brand.all().get(0);
    assertEquals(myBrand.getId(), savedBrand.getId());
  }
  
  @Test
  public void find_findsBrandInDatabase_true() {
    Brand myBrand = new Brand("Adidas");
    myBrand.save();
    Brand savedBrand = Brand.find(myBrand.getId());
    assertTrue(myBrand.equals(savedBrand));
  }
  
  @Test
  public void add_addStoretoBrand() {
    Brand myBrand = new Brand("Adidas");
    myBrand.save();
    Store myStore = new Store("PDX Payless", "100 SW Main", "1-800-632-SHOES");
    myStore.save();
    myBrand.addStore(myStore);
    assertEquals(myBrand.getStores().size(), 1);
  }  
}