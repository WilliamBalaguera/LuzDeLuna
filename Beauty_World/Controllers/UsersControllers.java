package Controllers;

import Models.Users;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersControllers {
    private List<Users> users = new ArrayList<>();

    // Crear usuario
    public void createUser(Users user) {
        if (user != null && user.isValidEmail()) {
            user.hashPassword();
            users.add(user);
        }
    }

    // Obtener todos los usuarios
    public List<Users> getAllUsers() {
        return new ArrayList<>(users);
    }

    // Obtener usuario por email
    public Optional<Users> getUserByEmail(String email) {
        return users.stream().filter(u -> u.getEmail().equals(email)).findFirst();
    }

    // Actualizar usuario
    public boolean updateUser(String email, Users updatedUser) {
        Optional<Users> userOpt = getUserByEmail(email);
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.hashPassword();
            return true;
        }
        return false;
    }

    // Eliminar usuario
    public boolean deleteUser(String email) {
        return users.removeIf(u -> u.getEmail().equals(email));
    }
}
