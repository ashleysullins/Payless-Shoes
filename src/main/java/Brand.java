import java.util.List;
import org.sql2o.*;

public class Brand {
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Brand(String name) {
    this.name = name;
  }

  public static List<Brand> all() {
    String sql = "SELECT id, name FROM brands";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Brand.class);
    }
  }
  
  @Override
  public boolean equals(Object otherBrand){
    if (!(otherBrand instanceof Brand)) {
      return false;
    } else {
      Brand newBrand = (Brand) otherBrand;
      return this.getName().equals(newBrand.getName()) &&
        this.getId() == newBrand.getId();
    }
  }
  
  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO brands (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", name)
      .executeUpdate()
      .getKey();
    }
  }
  
  public static Brand find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM brands where id=:id";
      Brand brand = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Brand.class);
      return brand;
    }
  }
  
  public List<Store> getStores() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT brands.* FROM stores JOIN stores_brands ON (stores.id = stores_brands.store_id) JOIN brands ON (stores_brands.brand_id = brands.id) WHERE stores.id = :id";
      List<Store> stores = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Store.class);
        return stores;
      }
  }

}