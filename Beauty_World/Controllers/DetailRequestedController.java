package Controllers;

import Models.DetailRequested;
import java.util.ArrayList;
import java.util.List;

public class DetailRequestedController {
    private List<DetailRequested> details = new ArrayList<>();

    // Crear detalle
    public void createDetail(DetailRequested detail) {
        if (detail != null) {
            detail.calculateSubTotal();
            details.add(detail);
        }
    }

    // Obtener todos los detalles
    public List<DetailRequested> getAllDetails() {
        return new ArrayList<>(details);
    }

    // Calcular total de detalles
    public double calculateTotal(List<DetailRequested> orderDetails) {
        return orderDetails.stream().mapToDouble(DetailRequested::getSubTotal).sum();
    }
}