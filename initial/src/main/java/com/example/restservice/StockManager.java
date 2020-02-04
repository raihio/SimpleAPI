package com.example.restservice;

import java.util.HashMap;

public class StockManager {

    static HashMap<String, Integer> stock = new HashMap<>();

    public static boolean addItem(String item) {
        if (stock.containsKey(item)) return false;
        stock.put(item, 0);
        return true;
    }

    public static boolean setStock(String item, int stockLevel) {
        if (stock.containsKey(item)) {
            stock.replace(item, stockLevel);
            return true;
        }
        return false;
    }

    public static boolean addStock(String item, int numItem) {
        if (stock.containsKey(item)) {
            int currentStock = stock.get(item);
            currentStock += numItem;
            stock.replace(item, currentStock);
            return true;
        }
        return false;
    }

    public static boolean removeStock(String item, int numItem) {
        if (stock.containsKey(item)){
            int currentStock = stock.get(item);
            currentStock -= numItem;
            stock.replace(item, currentStock);
            return true;
        }
        return false;
    }

    public static HashMap listStock() {
        return stock;
    }
}
