package com.company.transportapp.service;

import com.company.transportapp.dto.GeneratedPasswordDTO;
import com.company.transportapp.model.entities.User;
import com.company.transportapp.model.enums.UserRole;
import com.company.transportapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    // Vytvoření účtu pro vozidlo
    public GeneratedPasswordDTO createUserForTruck(String plateNumber) {

        if (userRepository.findByUsername(plateNumber).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Účet pro SPZ " + plateNumber + " již existuje");
        }

        String rawPassword = generatePassword();

        User user = new User();
        user.setUsername(plateNumber);  // SPZ = login
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(UserRole.DRIVER);
        user.setEnabled(true);

        userRepository.save(user);

        // Heslo se vrací jen pro vytvoření
        return new GeneratedPasswordDTO(plateNumber, rawPassword);
    }

    // Blokace účtu
    public void blockUser(Long id) {
        User user = get(id);
        user.setEnabled(false);
        userRepository.save(user);
    }

    // Odblokování účtu
    public void unblockUser(Long id) {
        User user = get(id);
        user.setEnabled(true);
        userRepository.save(user);
    }

    // Smazání účtu
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User get(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Uživatel nenalezen"));
    }

    // Generování hesla
    private String generatePassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz23456789!@#$%";
        SecureRandom random = new SecureRandom();

        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }


}
