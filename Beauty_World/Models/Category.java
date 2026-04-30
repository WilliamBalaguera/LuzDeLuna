package Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // Genera Getters, Setters, toString, equals y hashCode
@NoArgsConstructor // Genera el constructor vacío: public Models.Users() {}
@AllArgsConstructor // Genera el constructor con todos los campos: public Models.Users(String name, ...)

public class Category {
    private String name;

    // Método para validar nombre
    public boolean isValidName() {
        return name != null && !name.trim().isEmpty();
    }
}
