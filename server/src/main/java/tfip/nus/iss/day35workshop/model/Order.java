package tfip.nus.iss.day35workshop.model;

import java.io.StringReader;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Order {

    private String orderId;
    private String name;
    private String email;
    private Date date;
    private List<Item> items;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public JsonObject toJson() {
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        items.stream()
                .forEach(v -> arrBuilder.add(v.toJson()));

        return Json.createObjectBuilder()
                .add("orderId", orderId)
                .add("name", name)
                .add("email", email)
                .add("items", arrBuilder.build())
                .build();
    }

    public static Order toOrder(String json) {
        JsonReader r = Json.createReader(new StringReader(json));
        return toOrder(r.readObject());
    }

    public static Order toOrder(JsonObject j) {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString().substring(0, 8));
        order.setName(j.getString("name"));
        order.setEmail(j.getString("email"));
        List<Item> items = j.getJsonArray("items").stream()
                .map(v -> v.asJsonObject())
                .map(Item::toItem)
                .toList();
        order.setItems(items);
        return order;
    }

}
