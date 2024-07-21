package com.example.finale.enums;

public enum UserRole {
    ADMIN,
    USER; // Assurez-vous que le nom est en majuscules pour correspondre à votre base de données

    // Méthode pour vérifier si le nom du rôle correspond à la chaîne spécifiée, de manière insensible à la casse
    public boolean equalsIgnoreCase(String roleName) {
        return this.name().equalsIgnoreCase(roleName);
    }
}
