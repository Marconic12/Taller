package gimnasio.servicios;


import java.time.LocalDate;
import java.util.ArrayList;
///  clase que se ocupa de la gestion de membresias y hereda a sus hijas
public abstract class Membresia
{
    protected boolean estaActiva = false;
    protected LocalDate fechaFin;
    protected LocalDate fechaInicio;
    protected String tipoMembresia;

    public String getTipoMembresia() {
        return tipoMembresia;
    }

    public boolean statusMembresia() {
        return estaActiva;
    }

    public Membresia()
    {

    }
    public Membresia(boolean estaActiva,LocalDate fechaFin,LocalDate fechaInicio)
    {
        this.estaActiva = estaActiva;
        this.fechaFin = LocalDate.now();
        this.fechaInicio = LocalDate.now();


    }

    public abstract void cancelarMembresia();

    public abstract void verificarMembresia();

    public abstract void activarMembresia(Pago pago);


    public static class MembresiaGratis extends Membresia
    {
        private int limiteClasesLibres = 3;

        public MembresiaGratis()
        {
            this.tipoMembresia = "Membresia Gratis";
        }

        @Override
        public void cancelarMembresia()
        {
            this.estaActiva = false;
            System.out.println("La membresia gratuita ha sido cancelada satisfactoriamente..");

        }

        @Override
        public void verificarMembresia()
        {
            if(this.estaActiva)
            {
                System.out.println("la membresia gratuita sigue activa");
            }
            else
            {
                System.out.println("la membresia gratuita esta inactiva");
            }

        }

        @Override
        public void activarMembresia(Pago pago)
        {
            this.estaActiva = true;
            System.out.println("la membresia gratuita ha sido activada");

        }
/// constructor
        public MembresiaGratis(boolean estaActiva,LocalDate fechaFin,LocalDate fechaInicio)
        {
            super(estaActiva,fechaFin,fechaInicio);
            this.tipoMembresia = "Membresia Gratis";

        }


        public void  usarClaseLibre()
        {
            this.limiteClasesLibres -= 1;
            System.out.println("el usuario dispone de " + limiteClasesLibres);
        }


        public int getLimiteClasesLibres() {
            return limiteClasesLibres;
        }
    }

    public static class MembresiaPremium extends Membresia {
        private boolean accesoAreasExclusivas = false;
        private boolean entrenadorPersonal = false;
        private ArrayList<Pago> listaPagos;
        private double precio = 90.00;


        public MembresiaPremium() {

        }

        public MembresiaPremium(boolean estaActiva, LocalDate fechaFin, LocalDate fechaInicio) {
            super(estaActiva, fechaFin, fechaInicio);
            this.listaPagos = new ArrayList<Pago>();
            this.tipoMembresia = "Membresia Premium";
        }


        @Override
        public void cancelarMembresia()
        {
            this.estaActiva = false;
            System.out.println("La membresia premium ha sido cancelada satisfactoriamente..");
        }

        @Override
        public void verificarMembresia()
        {
            if(this.estaActiva)
            {
                System.out.println("la membresia premium sigue activa");
            }
            else
            {
                System.out.println("la membresia premium esta inactiva");
            }

        }

        @Override
        public void activarMembresia(Pago pago)
        {
            if(this.precio == pago.getMonto())
            {
                this.estaActiva = true;
                listaPagos.add(pago);
                System.out.println("su membresia premium ha sido activada");
            }
            else
            {
                System.out.println("Ingrese el monto correcto");
            }

        }


        public void accederaAreaVIP()
        {
            if(!this.accesoAreasExclusivas)
            {
                this.accesoAreasExclusivas = true;
                System.out.println("su acceso ya ha sido habilitado");
            }
            else
            {
                System.out.println("ya tiene acceso privilegiado");
            }

        }

        public void solicitarEntrenador()
        {
            if(!this.entrenadorPersonal)
            {
                this.entrenadorPersonal = true;
                System.out.println("se le asignara un entrenador personal");
            }
            else
            {
                System.out.println("ya tiene un entrenador personal asignado");
            }


        }

        public void historialPagos()
        {
            for(var i : this.listaPagos)
            {
                System.out.println("Pago: " + i + "||"+ "Fecha: " + i.getFechaPago());
            }
        }




    }
}
