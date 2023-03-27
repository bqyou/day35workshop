package tfip.nus.iss.day35workshop.service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import jakarta.json.JsonObject;
import tfip.nus.iss.day35workshop.model.Order;

@Service
public class OrderService {

    @Autowired
    private MongoTemplate template;

    public void createOrder(Order order) {
        JsonObject j = order.toJson();
        Document doc = Document.parse(j.toString());
        template.insert(doc, "new_orders");
    }

}
