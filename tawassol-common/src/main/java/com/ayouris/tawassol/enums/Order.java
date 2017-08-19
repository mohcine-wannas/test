package com.ayouris.tawassol.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Order {

    ASC("ASC"),
    DESC("DESC");


    private String value;

    private Order(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return this.value;
    }

    @JsonCreator
    public static Order create(String val) {
      Order[] orders = Order.values();
      for (Order order : orders) {
        if (order.getValue().equalsIgnoreCase(val)) {
          return order;
        }
      }
      return DESC;
    }
}

