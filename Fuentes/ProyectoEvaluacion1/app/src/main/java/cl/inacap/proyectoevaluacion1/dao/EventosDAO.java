package cl.inacap.proyectoevaluacion1.dao;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.proyectoevaluacion1.dto.Evento;

public class EventosDAO {

    private static List<Evento> eventos = new ArrayList<>();

    public void Add(Evento e){
        eventos.add(e);
    }

    public List<Evento> getAll(){

        return eventos;
    }

}
