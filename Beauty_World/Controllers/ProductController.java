package Controllers;

import Models.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductController {
    private List<Product> products = new ArrayList<>();

    // Crear producto
    public void createProduct(Product product) {
        if (product != null) {
            products.add(product);
        }
    }

    // Obtener todos los productos
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    // Obtener productos disponibles
    public List<Product> getAvailableProducts() {
        return products.stream().filter(Product::isAvailable).toList();
    }

    // Obtener producto por nombre
    public Optional<Product> getProductByName(String name) {
        return products.stream().filter(p -> p.getName().equals(name)).findFirst();
    }

    // Actualizar producto
    public boolean updateProduct(String name, Product updatedProduct) {
        Optional<Product> productOpt = getProductByName(name);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setStock(updatedProduct.getStock());
            product.setImageUrl(updatedProduct.getImageUrl());
            product.setState(updatedProduct.getState());
            return true;
        }
        return false;
    }

    // Eliminar producto
    public boolean deleteProduct(String name) {
        return products.removeIf(p -> p.getName().equals(name));
    }
}