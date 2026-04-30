package Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // Genera Getters, Setters, toString, equals y hashCode
@NoArgsConstructor // Genera el constructor vacío: public Models.Users() {}
@AllArgsConstructor // Genera el constructor con todos los campos: public Models.Users(String name, ...)

public class Pay {
    private Double amount;
    private String method;

    // Método para procesar pago (simulado)
    public boolean processPayment() {
        // Simulación: siempre true si amount > 0
        return amount != null && amount > 0;
    }
}
