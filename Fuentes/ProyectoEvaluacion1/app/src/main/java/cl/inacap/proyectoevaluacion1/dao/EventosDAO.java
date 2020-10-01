package cl.inacap.proyectoevaluacion1.dao;

import java.util.List;

import cl.inacap.proyectoevaluacion1.dto.Evento;

public interface EventosDAO {


    List<Evento> getAll();
    void add(Evento e);
}
