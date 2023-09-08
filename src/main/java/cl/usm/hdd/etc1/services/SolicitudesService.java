package cl.usm.hdd.etc1.services;
import cl.usm.hdd.etc1.entities.Solicitud;

import java.util.List;

public interface SolicitudesService {
    Solicitud crear(Solicitud solicitud);
    List<Solicitud> obtener();
    List<Solicitud> filtrar(String tipoSolicitud);
}
