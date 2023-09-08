package cl.usm.hdd.etc1.services;

import org.springframework.stereotype.Service;
import cl.usm.hdd.etc1.entities.Solicitud;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitudesServiceImpl implements  SolicitudesService{

    private static List<Solicitud> solicitudes= new ArrayList<>();

    @Override
    public Solicitud crear(Solicitud solicitud) {

        solicitudes.add(solicitud);
        return solicitud;
    }

    @Override
    public List<Solicitud> obtener() {
        return solicitudes;
    }

    @Override
    public List<Solicitud> filtrar(String tipoSolicitud) {
        return solicitudes.stream()
                .filter(s->s.getTipoSolicitud()
                        .equalsIgnoreCase(tipoSolicitud))
                .collect(Collectors.toList());
    }


}
