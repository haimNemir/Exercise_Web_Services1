package Exercise1.Beans;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double totalPrice;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart") // !!!!!!!!!!!!!!!!!!!!!!!!!!!ManyToMany!!!!!!!!!!!
    private List<Product> products;

    public Cart() {
    }

    public Cart(List<Product> products, double totalPrice) {
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", products=" + products +
                '}';
    }
}
