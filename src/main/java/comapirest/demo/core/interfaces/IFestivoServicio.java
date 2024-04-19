package comapirest.demo.core.interfaces;

import java.sql.Date;
import antlr.collections.List;


public interface IFestivoServicio {

    public boolean esFestivo(Date Fecha);

    public List<Festivos> obtenerFestivos(int año);

    public boolean esFechaValida(String strFecha);

}