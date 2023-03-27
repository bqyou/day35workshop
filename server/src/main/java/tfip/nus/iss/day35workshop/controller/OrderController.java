package tfip.nus.iss.day35workshop.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import tfip.nus.iss.day35workshop.model.Order;
import tfip.nus.iss.day35workshop.service.OrderService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private Logger logger = Logger.getLogger(OrderController.class.getName());

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> saveOrder(@RequestBody String payload) {

        logger.info("New order: " + payload);

        Order o = Order.toOrder(payload);
        String orderId = o.getOrderId();
        orderService.createOrder(o);

        JsonObject resp = Json.createObjectBuilder()
                .add("orderId", orderId)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(resp.toString());
    }

}
