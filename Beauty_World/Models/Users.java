package Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // Genera Getters, Setters, toString, equals y hashCode
@NoArgsConstructor // Genera el constructor vacío: public Models.Users() {}
@AllArgsConstructor // Genera el constructor con todos los campos: public Models.Users(String name, ...)
public class Users {
    private String name;
    private String email;
    private String password;

    // Método para validar email
    public boolean isValidEmail() {
        return email != null && email.contains("@");
    }

    // Método para hashear contraseña (simulado)
    public void hashPassword() {
        // Simulación de hash
        this.password = "hashed_" + this.password;
    }
}