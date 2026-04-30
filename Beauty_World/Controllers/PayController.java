package Controllers;

import Models.Pay;
import java.util.ArrayList;
import java.util.List;

public class PayController {
    private List<Pay> payments = new ArrayList<>();

    // Procesar pago
    public boolean processPayment(Pay payment) {
        if (payment != null && payment.processPayment()) {
            payments.add(payment);
            return true;
        }
        return false;
    }

    // Obtener todos los pagos
    public List<Pay> getAllPayments() {
        return new ArrayList<>(payments);
    }
}