package cl.usm.hdd.etc1.controllers;

import cl.usm.hdd.etc1.entities.Solicitud;
import cl.usm.hdd.etc1.services.SolicitudesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
public class SolicitudesController {

    @Autowired
    private SolicitudesService solicitudesService;

    /**
     * Evalua que el tipo proporcionado corresponda a los de
     * solicitudes del registro civil
     * @author sarayar
     * @param tipoSolicitud
     * @return boolean
     */
    private boolean esTipoValido(String tipoSolicitud){

        /*String tipo1 ="SolicitudCedula";
        String tipo2 ="RetiroCedula";
        String tipo3 = "SolicitudCertificadoNac";
        String tipo4 = "SolicitudCertificadoDef";

        return !(!tipoSolicitud.equalsIgnoreCase(tipo1)
                && !tipoSolicitud.equalsIgnoreCase(tipo2)
                && !tipoSolicitud.equalsIgnoreCase(tipo3)
                && !tipoSolicitud.equalsIgnoreCase(tipo4));

         */
        String [] tipos = {"SolicitudCedula","RetiroCedula"
                ,"SolicitudCertificadoNac","SolicitudCertificadoDef"};

        return Stream.of(tipos)
                .anyMatch(t->t.equalsIgnoreCase(tipoSolicitud));

    }

    @GetMapping("/verSolicitudes/{tipo}")
    public ResponseEntity<List<Solicitud>> filtrar(@PathVariable String tipo){
        try{
            return ResponseEntity.ok(this.solicitudesService.filtrar(tipo));
        }catch(Exception ex){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/verSolicitudes")
    public ResponseEntity<List<Solicitud>> obtener(){
        try{
            return ResponseEntity.ok(this.solicitudesService.obtener());
        }catch(Exception ex){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/ingresarSolicitud")
    public ResponseEntity<Solicitud> crear(@RequestBody Solicitud solicitud){

        if(!esTipoValido(solicitud.getTipoSolicitud())){
            //Larman patrones de diseño, ver patron builder, factory
            // cadena de responsabilidad (chain of responsability)
            return ResponseEntity.badRequest().build();
        }
        try{
            Solicitud sol = solicitudesService.crear(solicitud);
            return ResponseEntity.ok(sol);
        }catch(Exception ex){
            return ResponseEntity.internalServerError().build();
        }

    }
}
