package Controllers;

import Models.Product;
import Models.Order;
import Models.Pay;
import Models.StateOrder;
import Models.Users;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/tienda")
public class MainServlet extends HttpServlet {

    private ProductController productController = new ProductController();
    private OrderController orderController = new OrderController();
    private PayController payController = new PayController();
    private UsersControllers usersController = new UsersControllers();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Cargar lista de productos disponibles
            List<Product> products = productController.getAvailableProducts();
            request.setAttribute("products", products);

            // Redirigir al catálogo (asumiendo una página JSP llamada catalog.jsp)
            request.getRequestDispatcher("/catalog.jsp").forward(request, response);
        } catch (Exception e) {
            // Manejo de excepciones: redirigir a página de error
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar el catálogo: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            HttpSession session = request.getSession();

            if ("addToCart".equals(action)) {
                // Agregar producto al carrito
                String productName = request.getParameter("productName");
                Optional<Product> productOpt = productController.getProductByName(productName);
                if (productOpt.isPresent()) {
                    List<Product> cart = (List<Product>) session.getAttribute("cart");
                    if (cart == null) {
                        cart = new ArrayList<>();
                    }
                    cart.add(productOpt.get());
                    session.setAttribute("cart", cart);
                    // Redirigir de vuelta al catálogo
                    response.sendRedirect(request.getContextPath() + "/tienda");
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Producto no encontrado");
                }

            } else if ("processPayment".equals(action)) {
                // Procesar pago
                List<Product> cart = (List<Product>) session.getAttribute("cart");
                if (cart != null && !cart.isEmpty()) {
                    double total = cart.stream().mapToDouble(Product::getPrice).sum();
                    String paymentMethod = request.getParameter("paymentMethod"); // Asumir parámetro
                    Pay pay = new Pay(total, paymentMethod != null ? paymentMethod : "credit");

                    if (payController.processPayment(pay)) {
                        Order order = new Order();
                        order.setTotal(total);
                        order.setState(StateOrder.PAGADO);
                        orderController.createOrder(order);

                        // Limpiar carrito
                        session.removeAttribute("cart");

                        // Redirigir a página de confirmación
                        response.sendRedirect(request.getContextPath() + "/confirmation.jsp");
                    } else {
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error en el procesamiento del pago");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Carrito vacío");
                }

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida");
            }
        } catch (Exception e) {
            // Manejo de excepciones
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en la solicitud: " + e.getMessage());
        }
    }
}