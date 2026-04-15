package gimnasio.models;

import gimnasio.servicios.Membresia;

import java.time.LocalDate;

/// clase que gestiona cada usuario

public class Usuario
{
    private static int UniversalId = 0;
    private int id;
    private String nombre;
    private String email;
    private Membresia membresia;



    public Usuario()
    {

    }

    public Usuario(int id,String nombre,String email)
    {
        Usuario.UniversalId++;
        this.id += Usuario.UniversalId;
        this.nombre = nombre;
        this.email  = email;


    }

    public void crearMembresia()
    {
        this.membresia = new Membresia.MembresiaGratis(true, LocalDate.now().plusMonths(1), LocalDate.now());

    }

    public String getNombre() {
        return nombre;
    }

    public Membresia getMembresia() {
        return this.membresia;
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }

    public void actualizarPerfil(String nombre, String email)
    {
        this.nombre = nombre;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario:" +
                "id:" + id +
                ", nombre:'" + nombre + '\'' +
                ", email:'" + email + '\'';
    }
}
