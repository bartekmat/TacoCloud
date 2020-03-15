package com.tacocloud.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tacocloud.models.Order;
import com.tacocloud.models.Taco;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository{

    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderTacoInserter;
    private ObjectMapper objectMapper;

    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.orderInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("Taco_Order").usingGeneratedKeyColumns("id");
        this.orderTacoInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("Taco_Order_Tacos");
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {

        order.setPlacedAt(new Date()); //we assign current date to order at the moment when it is being saved
        int orderId = saveOrderDetails(order);//we need to assign id to order -> it comes from method.
        order.setId(orderId); //at this point order has id and time set
        List<Taco> tacos = order.getTacos(); //here and below we iterate over tacos from order transient state and we save them to db
        for (Taco taco : tacos) {
            saveTacoToOrder(taco, orderId);
        }
        return order;
    }

    private int saveOrderDetails (Order order){ //this method aims to generate and return id for order being saved
        @SuppressWarnings("unchecked")
        //Map<String, Object> values = objectMapper.convertValue(order, Map.class); //we prepare a map
                Map<String, Object> values = new HashMap<>();
        values.put("deliveryName", order.getCustomer_name());
        values.put("deliveryStreet", order.getStreet());
        values.put("deliveryCity", order.getCity());
        values.put("deliveryZip", order.getZip());
        values.put("ccNumber", order.getCcNumber());
        values.put("ccExpiration", order.getCcExpiration());
        values.put("ccCVV", order.getCcCVV());
        values.put("placedAt", order.getPlacedAt()); //into column placedAt we put date that was generated in parent method
        return orderInserter.executeAndReturnKey(values).intValue();
    }
     private void saveTacoToOrder(Taco taco, int orderId){
        Map<String, Object> values = new HashMap<>();
        values.put("tacoOrder", orderId); //into column named tacoOrder put value orderId
        values.put("taco", taco.getId()); //into column named taco put id of taco
         //now we have entire row prepared, lets execute it
         orderTacoInserter.execute(values);
     }

}
