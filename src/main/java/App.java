import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String,Object>();
      model.put("template", "templates/home.vtl");
  
      model.put("brands", Brand.all());
      model.put("stores", Store.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
    
    post("/new-store", (request, response) -> {
      Map<String, Object> model = new HashMap<String,Object>();
      model.put("template", "templates/new-client.vtl");
      model.put("brands", Brand.all());
      model.put("stores", Store.all());
      
      String name = request.queryParams("storeName");
      String phone = request.queryParams("storePhone");
      String address = request.queryParams("storeAddress");
      Store newStore = new Store(name, phone, address);
      newStore.save();
      
      response.redirect("/");
      return null;
      });
    
    post("/new-brand", (request, response) -> {
      Map<String, Object> model = new HashMap<String,Object>();
      model.put("template", "templates/new-client.vtl");
      model.put("brands", Brand.all());
      model.put("stores", Store.all());
      
      String name = request.queryParams("brandName");
      Brand newBrand = new Brand(name);
      newBrand.save();
      
      response.redirect("/");
      return null;
      });
    
    get("/store/:id/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String,Object>();
      model.put("template", "templates/update-store.vtl");
      model.put("store", Brand.find(Integer.parseInt(request.params(":id"))));
      model.put("brands", Brand.all());
      model.put("stores", Store.all());

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
    
    post("/store/:id/edit-store-success", (request, response) -> {
      Map<String, Object> model = new HashMap<String,Object>();
      Store store = Store.find(Integer.parseInt(request.params(":id")));
      String name = request.queryParams("storeName");
      String phone = request.queryParams("storePhone");
      String address = request.queryParams("storeAddress");
      store.update(name, phone, address);

      response.redirect("/");
      return null;
      });
    
    post("/store/:id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String,Object>();
      Store store = Store.find(Integer.parseInt(request.params(":id")));
      store.delete();
      
      response.redirect("/");
      return null;
      });
  }
}
