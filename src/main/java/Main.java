import java.util.Scanner;
import dao.EmpleadoDAO;
import entidades.Departamento;
import entidades.Empleado;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

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

        switch (opcion){
            case 1:
                System.out.print("Emp_no: ");
                int empNo = Integer.parseInt(sc.nextLine());

                System.out.print("Nombre: ");
                String nombre = sc.nextLine();

                System.out.print("Apellido: ");
                String apellido = sc.nextLine();

                System.out.print("Oficio: ");
                String oficio = sc.nextLine();

                System.out.print("Salario: ");
                BigDecimal salario = new BigDecimal(sc.nextLine());

                // Departamento (FK)
                System.out.print("Nombre departamento: ");
                String dnombre = sc.nextLine();

                System.out.print("Localidad departamento: ");
                String loc = sc.nextLine();

                Departamento d = new Departamento(dnombre, loc);

                Empleado e = new Empleado(empNo,
                        nombre,
                        apellido,
                        oficio,
                        LocalDate.now(),
                        salario,
                        d
                );

                empleadoDAO.insertarEmpleado(e);
                System.out.println("Empleado insertado correctamente");
            break;
        }
    }
}
