package comapirest.demo.Entites;


import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Festivo")
public class Festivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "Nombre")
    private String nombre;
    
    @Column(name = "Dia")
    private int dia;
    
    @Column(name = "Mes")
    private int mes;
    
    @Column(name = "DiasPascua")
    private int diasPascua;
    
    @ManyToOne
    @JoinColumn(name = "IdTipo")
    private Tipo tipo;

    public boolean esFestivo(LocalDate fecha) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'esFestivo'");
    }
    
    
}