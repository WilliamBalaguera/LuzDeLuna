package Controllers;

import Models.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryController {
    private List<Category> categories = new ArrayList<>();

    // Crear categoría
    public void createCategory(Category category) {
        if (category != null && category.isValidName()) {
            categories.add(category);
        }
    }

    // Obtener todas las categorías
    public List<Category> getAllCategories() {
        return new ArrayList<>(categories);
    }

    // Obtener categoría por nombre
    public Optional<Category> getCategoryByName(String name) {
        return categories.stream().filter(c -> c.getName().equals(name)).findFirst();
    }

    // Actualizar categoría
    public boolean updateCategory(String name, Category updatedCategory) {
        Optional<Category> categoryOpt = getCategoryByName(name);
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            category.setName(updatedCategory.getName());
            return true;
        }
        return false;
    }

    // Eliminar categoría
    public boolean deleteCategory(String name) {
        return categories.removeIf(c -> c.getName().equals(name));
    }
}