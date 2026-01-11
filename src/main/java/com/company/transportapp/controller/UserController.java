package com.company.transportapp.controller;

import com.company.transportapp.dto.GeneratedPasswordDTO;
import com.company.transportapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    // Vytvoření účtu pro vozidlo (SPZ)
    @PostMapping("/truck/{plateNumber}")
    @ResponseStatus(HttpStatus.CREATED)
    public GeneratedPasswordDTO createUserForTruck(@PathVariable String plateNumber) {
        return userService.createUserForTruck(plateNumber);
    }

    // Blokace účtu
    @PutMapping("/{id}/block")
    public void blockUser(@PathVariable Long id) {
        userService.blockUser(id);
    }

    // Odblokování účtu
    @PutMapping("/{id}/unblock")
    public void unblockUser(@PathVariable Long id) {
        userService.unblockUser(id);
    }

    // Smazání účtu
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
