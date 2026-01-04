package com.company.transportapp.service;

import com.company.transportapp.model.entities.User;
import com.company.transportapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordGenerator passwordGenerator;
    private final PasswordEncoder passwordEncoder;

    public GeneratedPasswordDTO createdUserForVehicle(
            String spz,
            UserRole role
    ) {
        String rawPassword = passwordGenerator.generate(10);

        User user = new User();
        user.setUsername(spz);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(role);
        user.setEnabled(true);

        userRepository.save(user);

        return new GeneratedPasswordDTO(spz, rawPassword);
    }

    public String regeneratePassword(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow();

        String newPassword = passwordGenerator.generate(10);
        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);

        return newPassword;
    }

}
