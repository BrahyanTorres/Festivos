package comapirest.demo.servicios;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import comapirest.demo.core.interfaces.IFestivoServicio;
import comapirest.demo.core.repositorios.IFestivoRepositorio;
import comapirest.demo.entidades.Festivos;

@Service
public class FestivoServicio implements IFestivoServicio {

    @Autowired
    IFestivoRepositorio repositorio;

    private Date obtenerDomingoPascua(int año) {
        int a = año % 19;
        int b = año / 100;
        int c = año % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        int mes = (h + l - 7 * m + 114) / 31;
        int dia = ((h + l - 7 * m + 114) % 31) + 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(año, mes - 1, dia);
        return calendar.getTime();
    }

    private LocalDate siguienteLunes(LocalDate fecha) {
        return fecha.with(DayOfWeek.MONDAY);
    }

    private LocalDate agregarDias(LocalDate fecha, int dias) {
        return fecha.plusDays(dias);
    }

    private List<Festivos> calcularFestivos(List<Festivos> festivos, int año) {
        if (festivos != null) {
            LocalDate pascua = obtenerDomingoPascua(año);
            for (Festivos festivo : festivos) {
                switch (festivos.getTipoFestivo().getId()) {
                    case 1:
                        festivos.setFecha(LocalDate.of(año, festivo.getMes(), festivo.getDia()));
                        break;
                    case 2:
                        festivos.setFecha(siguienteLunes(LocalDate.of(año, festivo.getMes(), festivo.getDia())));
                        break;
                    case 3:
                        festivos.setFecha(pascua.plusDays(festivo.getDiasPascua()));
                        break;
                    case 4:
                        festivos.setFecha(siguienteLunes(pascua.plusDays(festivo.getDiasPascua())));
                        break;
                }
            }
        }
        return festivos;
    }

    @Override
    public List<Festivos> obtenerFestivos(int año) {
        List<Festivos> festivos = repositorio.findAll();
        calcularFestivos(festivos, año);
        return festivos.stream()
                .map(festivo -> new Festivos(festivos.getNombre(), festivos.getFecha()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean esFestivo(Date fecha) {
        List<Festivos> festivos = repositorio.findAll();
        return esFestivo(fecha);
    }

    @Override
    public boolean esFechaValida(String strFecha) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            df.parse(strFecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}
