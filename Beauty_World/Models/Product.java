package Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // Genera Getters, Setters, toString, equals y hashCode
@NoArgsConstructor // Genera el constructor vacío: public Models.Users() {}
@AllArgsConstructor // Genera el constructor con todos los campos: public Models.Users(String name, ...)

public class Product {
    private String name;
    private String description;
    private Double price;
    private int stock;
    private String imageUrl;
    private Boolean state;

    // Método para verificar si está disponible
    public boolean isAvailable() {
        return state != null && state && stock > 0;
    }

    // Método para aplicar descuento
    public void applyDiscount(double discountPercentage) {
        if (price != null && discountPercentage > 0 && discountPercentage <= 100) {
            this.price = this.price * (1 - discountPercentage / 100);
        }
    }
}
