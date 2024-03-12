package comapirest.demo.Controlers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import comapirest.demo.Services.FestivoService;

@RestController
@RequestMapping("/api/festivos")
public class FestivoController {

    private FestivoService fechaService = new FestivoService();

    @Autowired
    public void FechaController(FestivoService fechaService) {
        this.fechaService = fechaService;
    }

    @GetMapping("/validar")
    public ResponseEntity<Boolean> validarFechaFestiva(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        boolean esFestiva = fechaService.esFestiva(fecha);
        return ResponseEntity.ok(esFestiva);
    }
}