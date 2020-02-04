package com.example.restservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class Controller {

    @GetMapping("/")
    public ArrayList<String> index() {
        ArrayList<String> cmds = new ArrayList<>();
        cmds.add("URLs:");
        cmds.add("add_item?item=x");
        cmds.add("set_stock?item=x?level=0");
        cmds.add("add_stock?item=x?level=0");
        cmds.add("remove_stock?item=x?level=0");
        cmds.add("list_stock");
        return cmds;
    }

    @GetMapping("/test")
    public String test() {
        return "Hello World!";
    }

    @GetMapping("/add_item")
    public ResponseEntity<String> addItem(@RequestParam(value = "item") String item_name) {
        if (StockManager.addItem(item_name)) return ResponseEntity.ok(item_name + " Added");
        else return ResponseEntity.badRequest().body(item_name + " already exists");
    }

    @GetMapping("/set_stock")
    public ResponseEntity<String> setStock(@RequestParam(value = "item") String item_name, @RequestParam(value = "level") int stockLevel) {
        if (StockManager.setStock(item_name, stockLevel))
            return ResponseEntity.ok(item_name + " now has stock level: " + stockLevel);
        else return ResponseEntity.badRequest().body("Could not update stock level for " + item_name);
    }

    @GetMapping("/add_stock")
    public ResponseEntity<String> addStock(@RequestParam(value = "item") String item_name, @RequestParam(value = "level") int stockLevel) {
        if (StockManager.addStock(item_name, stockLevel))
            return ResponseEntity.ok(item_name + " stock level increased by " + stockLevel);
        else return ResponseEntity.badRequest().body("Could not update stock level for " + item_name);
    }

    @GetMapping("/remove_stock")
    public ResponseEntity<String> removeStock(@RequestParam(value = "item") String item_name, @RequestParam(value = "level") int stockLevel) {
        if (StockManager.removeStock(item_name, stockLevel))
            return ResponseEntity.ok(item_name + " stock level decreased by " + stockLevel);
        else return ResponseEntity.badRequest().body("Could not update stock level for " + item_name);

    }

    @GetMapping("/list_stock")
    public ResponseEntity<HashMap> listStock() {
        return ResponseEntity.ok(StockManager.listStock());
    }
}
