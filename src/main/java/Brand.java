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
  
  public void addStore(Store store) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO stores_brands (brand_id, store_id) VALUES (:brand_id, :store_id)";
    con.createQuery(sql)
      .addParameter("brand_id", this.getId())
      .addParameter("store_id", store.getId())
      .executeUpdate();
  }
}
  
  public List<Store> getStores() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT stores.* FROM brands JOIN stores_brands ON (brands.id = stores_brands.brand_id) JOIN stores ON (stores_brands.store_id = stores.id) WHERE brands.id = :id";
      List<Store> stores = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Store.class);
        return stores;
      }
  }
}