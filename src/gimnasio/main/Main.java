package gimnasio.main;

import gimnasio.models.Usuario;
import gimnasio.models.Gimnasio;
import gimnasio.servicios.Membresia;
import gimnasio.servicios.Pago;
import gimnasio.servicios.Clase;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gimnasio gimnasio = new Gimnasio("Mi Gimnasio", "Av. Central");
        Usuario usuario = null;
        Clase claseZumba = new Clase("Zumba", "10:00 AM");

        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Identificarse (crear usuario)");
            System.out.println("2. Verificar estado de membresía");
            System.out.println("3. Pagar membresía Premium");
            System.out.println("4. Cancelar membresía");
            System.out.println("5. Activar membresía");
            System.out.println("6. Inscribirse en clase");
            System.out.println("7. Listar usuarios gratis");
            System.out.println("8. Listar usuarios premium");
            System.out.println("9. Acceder a área VIP (Premium)");
            System.out.println("10. Solicitar entrenador personal (Premium)");
            System.out.println("11. Ver historial de pagos (Premium)");
            System.out.println("12. Actualizar perfil");
            System.out.println("13. Cambiar usuario");
            System.out.println("14. Salir");
            System.out.print("Seleccione opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Ingrese email: ");
                    String email = sc.nextLine();
                    usuario = new Usuario(0,nombre, email);
                    System.out.println("Usuario creado: " + usuario.getNombre());
                    gimnasio.registrarUsuario(usuario);
                    break;

                case 2:
                    if (usuario != null && usuario.getMembresia() != null) {
                        usuario.getMembresia().verificarMembresia();
                    } else {
                        System.out.println("Primero debe identificarse.");
                    }
                    break;

                case 3:
                    if (usuario != null) {
                        System.out.print("Ingrese monto del pago (90) : ");
                        double monto = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Ingrese el metodo del pago: ");
                        String metodoPago = sc.nextLine();
                        Pago pago = new Pago(usuario, monto, metodoPago);

                        Membresia.MembresiaPremium premium =
                                new Membresia.MembresiaPremium(false, LocalDate.now().plusMonths(1), LocalDate.now());

                        premium.activarMembresia(pago);

                        if (premium.statusMembresia()) {
                            usuario.setMembresia(premium);
                            System.out.println(usuario.getNombre() + " ahora tiene Membresía Premium");
                        } else {
                            System.out.println("El pago no fue válido, sigue con Membresía Gratis");
                        }
                    } else {
                        System.out.println("Primero debe identificarse.");
                    }
                    break;

                case 4:
                    if (usuario != null && usuario.getMembresia() != null) {
                        usuario.getMembresia().cancelarMembresia();
                    } else {
                        System.out.println("No hay membresía activa.");
                    }
                    break;

                case 5:
                    if (usuario != null) {
                        usuario.getMembresia().activarMembresia(null);
                    } else {
                        System.out.println("No hay membresía para activar.");
                    }
                    break;

                case 6:
                    if (usuario != null) {
                        claseZumba.inscribirUsuarios(usuario);
                        System.out.println("Usuario inscrito en clase: " + claseZumba);
                    } else {
                        System.out.println("Primero debe identificarse.");
                    }
                    break;

                case 7:
                    gimnasio.ObtenerUsuariosGratis();
                    break;

                case 8:
                    gimnasio.ObtenerUsuariosPremium();
                    break;

                case 9:
                    if (usuario != null && usuario.getMembresia() instanceof Membresia.MembresiaPremium) {
                        ((Membresia.MembresiaPremium) usuario.getMembresia()).accederaAreaVIP();
                    } else {
                        System.out.println("Debe tener Membresía Premium para acceder al área VIP.");
                    }
                    break;

                case 10:
                    if (usuario != null && usuario.getMembresia() instanceof Membresia.MembresiaPremium) {
                        ((Membresia.MembresiaPremium) usuario.getMembresia()).solicitarEntrenador();
                    } else {
                        System.out.println("Debe tener Membresía Premium para solicitar entrenador personal.");
                    }
                    break;

                case 11:
                    if (usuario != null && usuario.getMembresia() instanceof Membresia.MembresiaPremium) {
                        ((Membresia.MembresiaPremium) usuario.getMembresia()).historialPagos();
                    } else {
                        System.out.println("Debe tener Membresía Premium para ver historial de pagos.");
                    }
                    break;

                case 12:
                    if(usuario != null)
                    {
                    System.out.println("Ingrese sus nuevo nombre: ");
                    String nombrenuevo = sc.nextLine();
                    System.out.println("Ingrese su nuevo correo: ");
                    String nuevoCorreo = sc.nextLine();
                    usuario.actualizarPerfil(nombrenuevo,nuevoCorreo);
                    }
                case 13:
                    if (gimnasio != null) {
                        System.out.print("Ingrese el índice del usuario que desea administrar (comienza desde el 0) : ");
                        int indice = sc.nextInt();
                        sc.nextLine(); // limpiar buffer
                        Usuario seleccionado = gimnasio.cambiarUsuario(indice);
                        if (seleccionado != null) {
                            usuario = seleccionado; // aquí cambias el usuario activo
                            System.out.println("Ahora estás administrando a: " + usuario.getNombre());
                        } else {
                            System.out.println("Índice inválido.");
                        }
                    } else {
                        System.out.println("No hay gimnasio creado.");
                    }
                    break;

                case 14:
                    salir = true;
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
