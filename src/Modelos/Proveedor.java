package Modelos;

public class Proveedor {
	//datos del proveedor
	public String RUC;
	public String Nombre;
	public String Direcci�n;
	public String Telefono;
	public String Email;
	public String Celular;
	public String Giro;

	public Proveedor(){}
	public Proveedor(String ruc, String nombre, String direccion, String telefono, String email, String celular, String giro){
		super();
		RUC = ruc;
		Nombre = nombre;
		Direcci�n = direccion;
	    Telefono = telefono;
		Email = email;
		Celular = celular;
		Giro = giro;
	}

}
