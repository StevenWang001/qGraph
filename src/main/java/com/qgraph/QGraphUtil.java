package com.qgraph;

public class QGraphUtil {
  public static Long getId(Object id) {
    if (id instanceof Long) {
      return (Long)id;
    } else if (id instanceof Number) {
      return ((Number) id).longValue();
    } else if (id instanceof String) {
      String str = ((String)id).replaceAll("-", "");
      return Long.valueOf(str);
    }
    throw new IllegalArgumentException("Invalid id");
  }
}
