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
        if (servicio.esFechaValida(String.valueOf(año) + "-" + String.valueOf(mes) + "-" + String.valueOf(dia))) {
            @SuppressWarnings("deprecation")
            Date fecha = new Date(año - 1900, mes - 1, dia);
            return servicio.esFestivo(fecha) ? "Es Festivo" : "No es festivo";
        } else {
            return "Fecha No valida";
        }

    }

    @SuppressWarnings("unchecked")
    @CrossOrigin(origins = "*")
    @GetMapping("/obtener/{año}")
    public List<Festivos> obtener(@PathVariable int año) {
        return (List<Festivos>) servicio.obtenerFestivos(año);
    }

}