import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/shoes_test", null, null);
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String stores = "DELETE FROM stores *;";
      String brands = "DELETE FROM brands *;";
      String stores_brands = "DELETE FROM stores_brands *;";
      con.createQuery(stores).executeUpdate();
      con.createQuery(brands).executeUpdate();
      con.createQuery(stores_brands).executeUpdate();

    }
  }
}