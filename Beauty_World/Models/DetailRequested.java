package Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // Genera Getters, Setters, toString, equals y hashCode
@NoArgsConstructor // Genera el constructor vacío: public Models.Users() {}
@AllArgsConstructor // Genera el constructor con todos los campos: public Models.Users(String name, ...)

public class DetailRequested {
    private int amount;
    private Double salePrice;
    private Double subTotal;

    // Método para calcular subtotal
    public void calculateSubTotal() {
        if (amount > 0 && salePrice != null && salePrice > 0) {
            this.subTotal = amount * salePrice;
        } else {
            this.subTotal = 0.0;
        }
    }
}
