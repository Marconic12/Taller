package gimnasio.models;


import java.util.ArrayList;
//clase que se encarga de la gestion de usuarios

public class Gimnasio {
    private String direccion;
    private String nombre;
    private ArrayList<Usuario> listaUsuarios;


    public Gimnasio() {

    }

    public Gimnasio(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.listaUsuarios = new ArrayList<Usuario>();

    }

    public void registrarUsuario(Usuario usuario)
    {
        listaUsuarios.add(usuario);

    }

    public void listarUsuarios() {
        for (var i : this.listaUsuarios) {
            System.out.println("Lista de Usuarios: " + i);
        }
    }


    public void ObtenerUsuariosGratis() {
        for (var i : this.listaUsuarios) {
            if (i.getMembresia().getTipoMembresia() != null && i.getMembresia().getTipoMembresia().equals("Membresia Gratis")) {
                System.out.println("Usuarios con membresia gratuita: " + i.getNombre());


            }
        }
    }

    public void ObtenerUsuariosPremium() {
        for (var i : this.listaUsuarios) {
            if (i.getMembresia().getTipoMembresia() != null && i.getMembresia().getTipoMembresia().equals("Membresia Premium")) {
                System.out.println("Usuarios con membresia premium: " + i.getNombre());

            }
        }
    }


    public Usuario cambiarUsuario(int index) {
        if (index >= 0 && index < listaUsuarios.size()) {
            return listaUsuarios.get(index);
        } else {
            System.out.println("Índice fuera de rango");
            return null;
        }
    }

    @Override
    public String toString() {
        return "Gimnasio{" +
                "direccion='" + direccion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", listaUsuarios=" + listaUsuarios +
                '}';
    }
}


