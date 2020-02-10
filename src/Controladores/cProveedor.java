package Controladores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.DefaultTableModel;


import Modelos.*;

public class cProveedor {
	//arreglo dinámico de objetos. Arraylist es una coleccion predefinida en JAVA
	ArrayList<Proveedor> Lista = new ArrayList<Proveedor>();
	//encabezados de columnas de la tabla
	public String[] columnName = {"Ruc", "Empresa", "Direccion", "Telefono","Email", "Celular", "Cta"};

	public int Count(){
		return Lista.size();
	}

	/**
	 * Borrar todos los elementos del arreglo
	 */
	public void Clear(){
		Lista.clear();
	}

//Ingresar nuevo
	public void nuevo(Proveedor e) throws IOException{
		int pos=localizar(e.RUC);
		if(pos==-1){//si el ruc no esta registrado, se agrega nuevo proveedor
			Lista.add(e);
		}
		else{
			throw new RuntimeException("Proveedor existente! Por favor agregar uno nuevo");
		}
	}

//Modificar datos de Proveedor
	public void modificar(Proveedor e, String ruc) throws IOException{
		int pos=localizar(ruc);
		if(pos>-1){//si estudiante está registrado se modifica
			Lista.set(pos, e);
		}
		else{
			throw new RuntimeException("No existe un proveedor registrado con la cedula ingresada");
		}
	}

//TABLA
	public DefaultTableModel getTabla(){
        DefaultTableModel tabla = new DefaultTableModel(columnName, 0){
        	@Override
            public boolean isCellEditable(int row, int column) {  return false; }
        };
        for(int i=0; i<Lista.size();i++){
        	Proveedor e=(Proveedor)Lista.get(i);
        	Object[] row={
        			new Integer(i+1), e.RUC, e.Empresa, e.Direccion, e.Telefono,
        			e.Email, e.Celular, e.Cta
        			};
            tabla.addRow(row);
        }
        return tabla;
    }

//LOCALIZAR
	public int localizar(String ruc){
		int pos=-1; //se retorna -1 si no se encuentra en el arreglo
		for(int i=0; i<Lista.size(); i++){
			Proveedor e=(Proveedor)Lista.get(i);
			if(ruc.equals(e.RUC)){
				pos=i; //posicion encontrada
				break; //finaliza el ciclo for
			}
		}
		return pos;
	}
//ELIMINAR
	public void eliminar(String ruc) throws IOException{
		int pos=localizar(ruc);
		if(pos>-1){//si estudiante está registrado se elimina
			Lista.remove(pos);
		}
		else{
			throw new RuntimeException("No existe un proveedor con ese numero de cedula");
		}
	}

//LEER
	public static final String SEPARADOR=";";
    public static final String QUOTE="\"";

	public void leer() throws IOException{
		BufferedReader br = null;
	    try {
	    	String path = Global.getPath()+"\\Recursos\\dataEstudiantes.csv";
	    	br =new BufferedReader(new FileReader(path));
	        String line = br.readLine();
	        Clear(); //limpiar lista de objetos
	        line = br.readLine();
	        while (line!=null) {
	           String [] row = line.split(SEPARADOR);
	           removeTrailingQuotes(row);
	           Proveedor ob=new Proveedor();
	           ob.RUC=row[0];
	           ob.Empresa=row[1];
	           ob.Direccion=row[2];
	           ob.Telefono=row[3];
	           ob.Direccion= row[4];
	           ob.Email=row[5];
	           ob.Celular=row[6];
	           ob.Cta=row[7];
	           nuevo(ob);//agregar a la lista
	           System.out.println(ob.Empresa);
	           System.out.println(Arrays.toString(row));
	           line = br.readLine();
	        }
	     } catch (Exception e) {

	     } finally {
	        if (null!=br) {
	           br.close();
	        }
	     }
	}

	private static String[] removeTrailingQuotes(String[] fields) {

	    String result[] = new String[fields.length];

	    for (int i=0;i<result.length;i++){
	       result[i] = fields[i].replaceAll("^"+QUOTE, "").replaceAll(QUOTE+"$", "");
	    }
	    return result;
	 }

//GUARDAR
	public void guardar() throws IOException{
		FileWriter file;
	    try {
	    	String path = Global.getPath()+"\\Recursos\\dataEstudiantes.csv";
	    	file = new FileWriter(path);
	    	final String NEXT_LINE = "\n";

	    	file.append("RUC").append(SEPARADOR);
			file.append("Empresa").append(SEPARADOR);
			file.append("Direccion").append(SEPARADOR);
			file.append("Telefono").append(SEPARADOR);
			file.append("Email").append(SEPARADOR);
			file.append("Celular").append(SEPARADOR);
			file.append("Cta").append(NEXT_LINE);

	    	for(int i=0; i<Lista.size();i++){
	        	Proveedor ob=(Proveedor)Lista.get(i);
				file.append(ob.RUC).append(SEPARADOR);
				file.append(ob.Empresa).append(SEPARADOR);
				file.append(ob.Direccion).append(SEPARADOR);
				file.append(ob.Telefono).append(SEPARADOR);
				file.append(ob.Email).append(SEPARADOR);
				file.append(ob.Celular).append(SEPARADOR);
				file.append(ob.Cta).append(NEXT_LINE);

	        }
	    	file.flush();
			file.close();
	     } catch (Exception e) {
	    	 System.out.print(e.getMessage());
	     }
	}
}
