package com.company.transportapp.model.enums;

public enum EmployeeRole {

    DRIVER(UserRole.DRIVER),  // Řidič (SPZ / vozidlo)
    DISPATCHER(UserRole.DISPATCHER),  // Dispečer
    OWNER(UserRole.OWNER);  // Správce / vedení


    private final UserRole userRole;

    EmployeeRole(UserRole userRole) {
        this.userRole=userRole;
    }

    public UserRole toUserRole() {
        return userRole;
    }
}
