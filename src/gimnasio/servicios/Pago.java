package gimnasio.servicios;

import gimnasio.models.Usuario;

import java.time.LocalDate;
///  clase que se ocupa de procesar pagos a tipo de cheque
public class Pago
{
    private LocalDate fechaPago;
    private String metodoPago;
    private double monto;
    private Usuario usuario;


    public Pago()
    {

    }
    public Pago(Usuario usuario,double monto,String metodoPago)
    {
        this.usuario = usuario;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.fechaPago = LocalDate.now();

    }

    public double getMonto() {
        return monto;
    }

    public LocalDate getFechaPago()
    {
        return this.fechaPago;
    }
}
