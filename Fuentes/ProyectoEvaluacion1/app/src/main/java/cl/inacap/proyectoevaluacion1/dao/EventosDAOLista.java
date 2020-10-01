package cl.inacap.proyectoevaluacion1.dao;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.proyectoevaluacion1.dto.Evento;

public class EventosDAOLista implements EventosDAO {

    private static List<Evento> eventos = new ArrayList<>();
    @Override
    public List<Evento> getAll() {
        return eventos;
    }

    @Override
    public void add(Evento e) {
        eventos.add(e);

    }
}
