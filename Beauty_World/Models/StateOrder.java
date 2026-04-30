package Models;

public enum StateOrder {
    PAGO_PENDIENTE, PAGADO, EN_PREPARACION, ENVIADO, ENTREGADO, CANCELADO;

    // Método para verificar si es un estado final
    public boolean isFinalState() {
        return this == ENTREGADO || this == CANCELADO;
    }
}
