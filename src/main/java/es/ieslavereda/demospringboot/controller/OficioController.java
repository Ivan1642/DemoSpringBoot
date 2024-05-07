package es.ieslavereda.demospringboot.controller;

import es.ieslavereda.demospringboot.model.Oficio;
import es.ieslavereda.demospringboot.service.OficioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class OficioController extends BaseController {
    private final Logger LOGGER = Logger.getLogger(getClass().getCanonicalName());

    @Autowired
    private OficioService oficioService;


    @GetMapping("/oficio")
    public ResponseEntity<?> getOficios(){

        return new ResponseEntity<>(oficioService.getOficios(), HttpStatus.OK);

    }

    @GetMapping("/oficio/{id}")
    public ResponseEntity<?> getOficio(@PathVariable("id") int id){

        try {
            Oficio oficio = oficioService.getOficioById(id);
            return new ResponseEntity<>(oficio,HttpStatus.OK);

        }catch (SQLException sqle){
            return getResponseError(sqle);
        }
    }

    @PostMapping("/oficio")
    public ResponseEntity<?> crearOficio(@RequestBody Oficio oficio){

        try{
            return new ResponseEntity<>(oficioService.crearOficio(oficio),HttpStatus.OK);
        }catch (SQLException sqle){
            return getResponseError(sqle);
        }

    }

    @DeleteMapping("/oficio/{id}")
    public ResponseEntity<?> deleteOficio(@PathVariable Integer id) {

        LOGGER.log(Level.INFO,"Eliminando el oficio " + id);
        try{
            Oficio oficio = oficioService.removeOficio(id);
            return new ResponseEntity<>(oficio, HttpStatus.OK);
        }catch (SQLException e){
            return getResponseError(e);
        }

    }

    @PutMapping("/oficio")
    public ResponseEntity<?> updateOficio(@RequestBody Oficio oficio) {
        try {
            Oficio o = oficioService.updateOficio(oficio);
            return new ResponseEntity<>(o, HttpStatus.OK);
        } catch (SQLException e) {
            return getResponseError(e);
        }
    }
}
