package eagb.rh.service;

import eagb.rh.entity.Empleado;

import java.util.List;

public interface IEmpleadoService {

    public List<Empleado> listarEmpleados();

    public Empleado buscarEmpleadoPorId(Integer id);

    public Empleado guardarEmpleado(Empleado empleado);

    public void eliminarEmpleado(Empleado empleado);

    public Empleado actualizarEmpleado(Integer id, Empleado empleado);
}
