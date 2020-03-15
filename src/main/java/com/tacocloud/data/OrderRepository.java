package com.tacocloud.data;

import com.tacocloud.models.Order;

public interface OrderRepository {
    Order save(Order order);
}
