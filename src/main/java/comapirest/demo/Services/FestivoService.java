package comapirest.demo.Services;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class FestivoService {

    private static final Set<LocalDate> fechasFestivas = new HashSet<>();

    static {
        // Agregar algunas fechas festivas predefinidas para demostración
        fechasFestivas.add(LocalDate.of(2023, 1, 1)); // Año Nuevo
        fechasFestivas.add(LocalDate.of(2023, 12, 25)); // Navidad
        // Agrega más fechas festivas según sea necesario
    }

    public boolean esFestiva(LocalDate fecha) {
        return fechasFestivas.contains(fecha);
    }
}