package com.example.finale.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @PostMapping
    public String uploadFile(@RequestParam("image") MultipartFile file) {
        // Vous pouvez gérer le fichier ici, par exemple, le sauvegarder sur le serveur.
        // Assurez-vous d'ajouter la logique nécessaire pour gérer le fichier correctement.

        // Exemple de réponse réussie
        return "Image uploaded successfully!";
    }
}