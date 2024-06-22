package eagb.rh.service;

import eagb.rh.entity.Empleado;
import eagb.rh.excepcion.ClienteNoEncontradoException;
import eagb.rh.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService implements IEmpleadoService{

    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado buscarEmpleadoPorId(Integer id) {
       return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
       return empleadoRepository.save(empleado);
    }

    @Override
    public void eliminarEmpleado(Empleado empleado) {
        empleadoRepository.delete(empleado);
    }

    @Override
    public Empleado actualizarEmpleado(Integer id, Empleado empleado) {
        Optional<Empleado> empleadoExistente = empleadoRepository.findById(id);
        if (!empleadoExistente.isPresent()) {
            throw new ClienteNoEncontradoException("No se encontro el cliente");
        } else {
            Empleado actual = empleadoExistente.get();
            actual.setNombre(empleado.getNombre());
            actual.setDepartamento(empleado.getDepartamento());
            actual.setSueldo(empleado.getSueldo());
            return empleadoRepository.save(actual);
        }
    }
}
