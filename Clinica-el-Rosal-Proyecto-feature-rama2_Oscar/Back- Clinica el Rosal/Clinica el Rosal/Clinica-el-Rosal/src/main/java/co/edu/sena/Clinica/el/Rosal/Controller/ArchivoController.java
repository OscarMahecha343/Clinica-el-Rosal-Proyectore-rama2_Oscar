package co.edu.sena.Clinica.el.Rosal.Controller;

import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;

@RestController
@RequestMapping("/archivos")
@CrossOrigin(origins = "*")
public class ArchivoController {

    private static final String UPLOAD_DIR = "uploads/";

    // POST: Subir archivo
    @PostMapping("/subir")
    public ResponseEntity<String> subirArchivo(@RequestParam("archivo") MultipartFile archivo) {
        try {
            String nombre = archivo.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + nombre);
            Files.copy(archivo.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return ResponseEntity.ok(nombre); // Retorna nombre para guardarlo en BD
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al subir archivo: " + e.getMessage());
        }
    }

    // GET: Descargar archivo
    @GetMapping("/{nombre}")
    public ResponseEntity<Resource> descargarArchivo(@PathVariable String nombre) throws IOException {
        Path ruta = Paths.get(UPLOAD_DIR).resolve(nombre).normalize();
        Resource recurso = new UrlResource(ruta.toUri());

        if (!recurso.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nombre + "\"")
                .body(recurso);
    }
}