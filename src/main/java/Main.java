import java.util.Scanner;
import dao.EmpleadoDAO;
import entidades.Departamento;
import entidades.Empleado;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmpleadoDAO empleadoDAO = new EmpleadoDAO("hibernate.cfg.xml");
        System.out.println("Selecciona una opción");
        System.out.println("1- Insertar registro");
        System.out.println("2- Buscar/Leer registros por su ID.");
        System.out.println("3- Modificar datos de un registro existente.");
        System.out.println("4- Borrar registros de la base de datos");
        int opcion = sc.nextInt();
        sc.nextLine();
        switch (opcion){
            case 1:
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();

                System.out.print("Apellido: ");
                String apellido = sc.nextLine();

                System.out.print("Oficio: ");
                String oficio = sc.nextLine();

                System.out.print("Salario: ");
                BigDecimal salario = new BigDecimal(sc.nextLine());

                System.out.print("Dept_no existente: ");
                int deptNo = Integer.parseInt(sc.nextLine());

                // OJO: el DAO debe tener un método para obtener el Departamento por id
                Departamento d = empleadoDAO.buscarDepartamentoPorId(deptNo);

                if (d == null) {
                    System.out.println("No existe departamento con id " + deptNo);
                    break;
                }

                Empleado e1 = new Empleado(nombre, apellido, oficio, LocalDate.now(), salario, d);

                empleadoDAO.insertarEmpleado(e1);
                System.out.println("Empleado insertado correctamente");
                break;

            case 2:

                    System.out.print("ID del empleado (emp_no): ");
                    int id = Integer.parseInt(sc.nextLine());

                    Empleado e2 = empleadoDAO.buscarPorId(id);

                    if (e2 == null) {
                        System.out.println("No existe empleado con id " + id);
                    } else {
                        System.out.println("Empleado encontrado:");
                        System.out.println("ID: " + e2.getEmpNo());
                        System.out.println("Nombre: " + e2.getNombre());
                        System.out.println("Apellido: " + e2.getApellido());
                        System.out.println("Oficio: " + e2.getOficio());
                        System.out.println("Salario: " + e2.getSalario());
                        System.out.println("Dept: " + e2.getDepartamento().getDeptNo() + " - " + e2.getDepartamento().getDnombre());
                    }
            break;
            case 3:
                System.out.print("ID del empleado a modificar (emp_no): ");

                int idMod = Integer.parseInt(sc.nextLine());
                Empleado eMod = empleadoDAO.buscarPorId(idMod);

                if (eMod == null) {
                    System.out.println("No existe empleado con id " + idMod);
                    break;
                }
                System.out.println("Empleado actual: " + eMod.getNombre() + " " + eMod.getApellido());
                System.out.print("Nuevo oficio (enter para no cambiar): ");
                String nuevoOficio = sc.nextLine();
                if (!nuevoOficio.isBlank()) {
                    eMod.setOficio(nuevoOficio);
                }

                System.out.print("Nuevo salario (enter para no cambiar): ");
                String salarioTxt = sc.nextLine();
                if (!salarioTxt.isBlank()) {
                    eMod.setSalario(new BigDecimal(salarioTxt));
                }
                empleadoDAO.actualizarEmpleado(eMod);
                System.out.println("Empleado actualizado correctamente");
            break;
            case 4:
                System.out.print("ID del empleado a borrar (emp_no): ");
                int idBor = Integer.parseInt(sc.nextLine());

                Empleado eBor = empleadoDAO.buscarPorId(idBor);

                if (eBor == null) {
                    System.out.println("No existe empleado con id " + idBor);
                    break;
                }

                empleadoDAO.borrarEmpleado(eBor);
                System.out.println("Empleado borrado correctamente");
                break;

        }
    }
}
