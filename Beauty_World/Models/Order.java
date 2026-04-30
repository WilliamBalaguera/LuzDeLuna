package Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Data // Genera Getters, Setters, toString, equals y hashCode
@NoArgsConstructor // Genera el constructor vacío: public Models.Users() {}
@AllArgsConstructor // Genera el constructor con todos los campos: public Models.Users(String name, ...)

public class Order {
    private Date date;
    private Double total;
    private StateOrder state;

    // Método para actualizar total (simulado)
    public void updateTotal(double newTotal) {
        if (newTotal >= 0) {
            this.total = newTotal;
        }
    }

    // Método para verificar si el pedido está completado
    public boolean isCompleted() {
        return state == StateOrder.ENTREGADO;
    }
}
