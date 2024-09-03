package Exercise1.Beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    public Product() {
    }

    public Product(String title, double price, double shoppingCost) {
        this.title = title;
        this.price = price;
        this.shoppingCost = shoppingCost;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", shoppingCost=" + shoppingCost +
                '}';
    }
}
