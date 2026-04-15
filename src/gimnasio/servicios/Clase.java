package gimnasio.servicios;

import gimnasio.models.Usuario;


import java.util.ArrayList;

///  clase que se usa para agregar clases de zumba o pilates o lo que sea
public class Clase
{
    private int cupoMaximo = 30;
    private String  horario;
    private String nombre;
    private ArrayList<Usuario> listaClase;


    public Clase()
    {

    }

    public Clase(String nombre,String horario)
    {
        this.nombre = nombre;
        this.listaClase = new ArrayList<Usuario>();
        this.horario = horario;


    }

    public void inscribirUsuarios(Usuario ... usuario)
    {
        for(var i : usuario){
            listaClase.add(i);
            this.cupoMaximo -= 1;
        }

    }

    @Override
    public String toString() {
        return "Clase" +
                "cupoMaximo:" + cupoMaximo +
                ", horario: '" + horario + '\'' +
                ", nombre:'" + nombre + '\'' +
                ", ClaseLista: " + listaClase ;
    }
}
