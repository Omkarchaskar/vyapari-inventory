package com.vyapari.inventorymanagement.controller;

import com.vyapari.inventorymanagement.model.Product;
import com.vyapari.inventorymanagement.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class InventoryController {

    @Autowired
    private ProductRepository productRepository;


    // ✅ SHOW INVENTORY PAGE (with search)
    @GetMapping("/inventory")
    public String inventoryPage(
            @RequestParam(value = "search", required = false) String search,
            HttpSession session,
            Model model) {

        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }

        List<Product> products;

        if (search != null && !search.isEmpty()) {
            products = productRepository.findByNameContainingIgnoreCase(search);
            model.addAttribute("searchQuery", search);
        } else {
            products = productRepository.findAll();
            model.addAttribute("searchQuery", "");
        }

        model.addAttribute("products", products);
        return "inventory";
    }


    // ✅ ADD NEW PRODUCT
    @PostMapping("/addProduct")
    public String addProduct(
            @RequestParam String name,
            @RequestParam String purchaseDate,
            @RequestParam int quantity,
            @RequestParam double pricePerUnit,
            @RequestParam int minStockLevel) {

        Product p = new Product();
        p.setName(name);
        p.setPurchaseDate(purchaseDate);  // String
        p.setQuantity(quantity);
        p.setPricePerUnit(pricePerUnit);
        p.setMinStockLevel(minStockLevel);

        productRepository.save(p);
        return "redirect:/inventory";
    }


    // ✅ SHOW EDIT FORM
    @GetMapping("/editProduct")
    public String editProduct(@RequestParam Long id, Model model, HttpSession session) {

        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }

        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);

        return "edit_product";
    }


    // ✅ UPDATE PRODUCT
    @PostMapping("/updateProduct")
    public String updateProduct(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String purchaseDate,   // FIXED — changed to String
            @RequestParam int quantity,
            @RequestParam double pricePerUnit,
            @RequestParam int minStockLevel) {

        Product p = productRepository.findById(id).orElse(null);

        if (p != null) {
            p.setName(name);
            p.setPurchaseDate(purchaseDate);  // String
            p.setQuantity(quantity);
            p.setPricePerUnit(pricePerUnit);
            p.setMinStockLevel(minStockLevel);
            productRepository.save(p);
        }

        return "redirect:/inventory";
    }


    // ✅ DELETE PRODUCT
    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam Long id) {
        productRepository.deleteById(id);
        return "redirect:/inventory";
    }


    // ✅ INCREASE STOCK
    @GetMapping("/increaseStock")
    public String increaseStock(@RequestParam Long id) {
        Product p = productRepository.findById(id).orElse(null);

        if (p != null) {
            p.setQuantity(p.getQuantity() + 1);
            productRepository.save(p);
        }

        return "redirect:/inventory";
    }


    // ✅ DECREASE STOCK
    @GetMapping("/decreaseStock")
    public String decreaseStock(@RequestParam Long id) {
        Product p = productRepository.findById(id).orElse(null);

        if (p != null && p.getQuantity() > 0) {
            p.setQuantity(p.getQuantity() - 1);
            productRepository.save(p);
        }

        return "redirect:/inventory";
    }
}
