import java.util.List;
import org.sql2o.*;

public class Store {
  private int id;
  private String name;
  private String address;
  private String phone;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
  
  public String getAddress() {
    return address;
  }
  
  public String getPhone() {
    return phone;
  }

  public Store(String name, String address, String phone) {
    this.name = name;
    this.address = address;
    this.phone = phone;
  }

  public static List<Store> all() {
    String sql = "SELECT id, name, address, phone FROM stores";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Store.class);
    }
  }
  
  @Override
  public boolean equals(Object otherStore){
    if (!(otherStore instanceof Store)) {
      return false;
    } else {
      Store newStore = (Store) otherStore;
      return this.getName().equals(newStore.getName()) &&
        this.getId() == newStore.getId() &&
        this.getAddress() == newStore.getAddress() &&
        this.getPhone() == newStore.getPhone();
    }
  }
  
  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO stores (name, address, phone) VALUES (:name, :address, :phone)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", name)
      .addParameter("address", address)
      .addParameter("phone", phone)
      .executeUpdate()
      .getKey();
    }
  }
  
  public static Store find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stores where id=:id";
      Store client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Store.class);
      return client;
    }
  }
  
  public void update(String name, String address, String phone) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stores SET (name, address, phone) = (:name, :address, :phone) WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("address", address)
        .addParameter("phone", phone)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
  
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM stores WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
  
}