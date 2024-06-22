package eagb.rh.controller;

import eagb.rh.entity.Empleado;
import eagb.rh.excepcion.ClienteNoEncontradoException;
import eagb.rh.service.EmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("rh-app")
@CrossOrigin(value = "http://localhost:3000")
public class EmpleadoController {

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoController.class);

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados(){
        List<Empleado> ep = empleadoService.listarEmpleados();
        return ep;
    }
    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleados(@PathVariable Integer id){
        Empleado em = empleadoService.buscarEmpleadoPorId(id);
            if(em == null)
                throw new ClienteNoEncontradoException("No se encontro el cliente");
            else
                return ResponseEntity.status(HttpStatus.OK).body(em);
    }
    @PostMapping("/empleados")
    public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
       return empleadoService.guardarEmpleado(empleado);
    }
    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable Integer id) {
        Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
        empleadoService.eliminarEmpleado(empleado);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado",Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuesta);
    }
    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleado) {
       Empleado empleado1 =  empleadoService.actualizarEmpleado(id, empleado);
       return ResponseEntity.status(HttpStatus.OK).body(empleado1);
    }
}
