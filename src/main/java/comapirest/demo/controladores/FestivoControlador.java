package comapirest.demo.controladores;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import comapirest.demo.core.interfaces.IFestivoServicio;
import comapirest.demo.entidades.Festivos;

@RestController
@RequestMapping("/festivos")
public class FestivoControlador {

    @Autowired
    private IFestivoServicio servicio;

    @RequestMapping(value = "/verificar/{año}/{mes}/{dia}", method = RequestMethod.GET)
    public String verificarFestivo(@PathVariable int año, @PathVariable int mes, @PathVariable int dia) {
        String fechaString = String.format("%d-%02d-%02d", año, mes, dia);
        if (servicio.esFechaValida(fechaString)) {
            LocalDate fecha = LocalDate.of(año, mes, dia);
            return servicio.esFestivo(fecha) ? "Es Festivo" : "No es festivo";
        } else {
            return "Fecha no válida";
        }
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/obtener/{año}")
    public List<Festivos> obtener(@PathVariable int año) {
        return (List<Festivos>) servicio.obtenerFestivos(año);
    }

}