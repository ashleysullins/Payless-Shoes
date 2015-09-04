import org.junit.*;
import static org.junit.Assert.*;

public class StoreTest {
  
  @Rule
  public DatabaseRule database = new DatabaseRule();
  
  @Test
  public void all_emptyAtFirst() {
  assertEquals(Store.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfStoresAretheSame() {
    Store firstStore = new Store("PDX Payless", "100 SW Main", "1-800-632-SHOES");
    Store secondStore = new Store("PDX Payless", "100 SW Main", "1-800-632-SHOES");
    assertTrue(firstStore.equals(secondStore));
  }
  
  @Test
  public void save_assignsIdToObject() {
    Store myStore = new Store("PDX Payless", "100 SW Main", "1-800-632-SHOES");
    myStore.save();
    Store savedStore = Store.all().get(0);
    assertEquals(myStore.getId(), savedStore.getId());
  }
  
  @Test
  public void find_findsStoreInDatabase_true() {
    Store myStore = new Store("PDX Payless", "100 SW Main", "1-800-632-SHOES");
    myStore.save();
    Store savedStore = Store.find(myStore.getId());
    assertTrue(myStore.equals(savedStore));
  }
  
  @Test
  public void update_updateStoreInDatabase() {
    Store myStore = new Store("PDX Payless", "100 SW Main", "1-800-632-SHOES");
    myStore.save();
    myStore.update("Seattle Payless", "200 N Freemont", "1-800-543-SHOES");
    assertEquals("Seattle Payless", Store.all().get(0).getName());
  }
  
  @Test
  public void delete_deleteStoreInDatabase() {
    Store myStore = new Store("PDX Payless", "100 SW Main", "1-800-632-SHOES");
    myStore.save();
    myStore.delete();
    assertEquals(Store.all().size(), 0);
  }
}