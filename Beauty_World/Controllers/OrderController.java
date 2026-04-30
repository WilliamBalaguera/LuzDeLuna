package Controllers;

import Models.Order;
import Models.StateOrder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OrderController {
    private List<Order> orders = new ArrayList<>();
    private int orderIdCounter = 1; // Simulado, ya que no hay ID

    // Crear pedido
    public void createOrder(Order order) {
        if (order != null) {
            order.setDate(new Date());
            orders.add(order);
        }
    }

    // Obtener todos los pedidos
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    // Obtener pedidos por estado
    public List<Order> getOrdersByState(StateOrder state) {
        return orders.stream().filter(o -> o.getState() == state).toList();
    }

    // Actualizar estado del pedido
    public boolean updateOrderState(Date date, StateOrder newState) {
        Optional<Order> orderOpt = orders.stream().filter(o -> o.getDate().equals(date)).findFirst();
        if (orderOpt.isPresent()) {
            orderOpt.get().setState(newState);
            return true;
        }
        return false;
    }

    // Eliminar pedido
    public boolean deleteOrder(Date date) {
        return orders.removeIf(o -> o.getDate().equals(date));
    }
}