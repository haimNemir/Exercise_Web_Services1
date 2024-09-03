package Exercise1.Beans;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private double price;
    private double shoppingCost;
    @ManyToOne
    private Cart cart;

    public Product() {
    }

    public Product(String title, double price, double shoppingCost, Cart cart) {
        this.title = title;
        this.price = price;
        this.shoppingCost = shoppingCost;
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getShoppingCost() {
        return shoppingCost;
    }

    public void setShoppingCost(double shoppingCost) {
        this.shoppingCost = shoppingCost;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", shoppingCost=" + shoppingCost +
                ", cart=" + cart.getId() +
                '}';
    }
}
