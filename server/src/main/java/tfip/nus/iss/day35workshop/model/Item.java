package tfip.nus.iss.day35workshop.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Item {

    private String item;
    private Integer quantity;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("item", item)
                .add("quantity", quantity)
                .build();
    }

    public static Item toItem(JsonObject j) {
        Item i = new Item();
        i.setItem(j.getString("item"));
        i.setQuantity(j.getInt("quantity"));
        return i;
    }

}
