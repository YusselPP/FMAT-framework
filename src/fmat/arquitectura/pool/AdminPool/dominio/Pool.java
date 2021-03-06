package fmat.arquitectura.pool.AdminPool.dominio;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public class Pool  {
	
	private static Pool INSTANCE=createInstance();
	
	public static Pool getInstance() {
        return INSTANCE;
    }
	
	public Conexion obtenerConexionDisp() {
		Conexion conexionDisponible=null;
		for (int segmento=1;segmento<this.segmentos;segmento++){
			if(piscinaConexiones.containsKey(segmento)){
				conexiones= piscinaConexiones.get(segmento);
				for(int i=0; i<conexiones.size();i++){
					Conexion conexion=conexiones.get(i);
					if(conexion.getEstado()){
						conexion.setEstado(false);
						conexionDisponible= conexion;
						return conexionDisponible;	
					}
				}
			}else{
				break;
			}
		}
		return conexionDisponible;
	}
	
	public Conexion crearConexion(Connection conn){
		boolean estadoConexion=true;
		Conexion conexion =new Conexion(conn,estadoConexion);
		return conexion;
	}
	
	public ArrayList<Conexion> crearSegmentoConexiones(){
		segmentosCreados+=1;
		return this.conexiones=new ArrayList<Conexion>();
	}
	
	public void asignarSegmentoCreado(ArrayList<Conexion> conexiones){
		this.conexiones=conexiones;
		piscinaConexiones.put(segmentosCreados, this.conexiones);
	}
	public int getSegmentos() {
		return segmentos;
	}

	public void setSegmentos(int segmentos) {
		this.segmentosCreados=0;
		this.segmentos = segmentos;
	}

	public int getTamañoSegmentos() {
		return tamañoSegmentos;
	}

	public void setTamañoSegmentos(int tamañoSegmentos) {
		this.tamañoSegmentos = tamañoSegmentos;
	}
	
	public int getSegmentosCreados(){
		return this.segmentosCreados;
	}
	
	public boolean conexionesRestantes(){
		int conexionesRestantes=conexionesDisponibles();
		int conexionesPiscina=this.segmentosCreados*this.tamañoSegmentos;
		int porcentajeConRestante=conexionesRestantes/conexionesPiscina;
		if(porcentajeConRestante>.2){
			return true;
		}else{
			return false;
		}
		
	}
	 
	private ArrayList<Conexion> conexiones;
	private HashMap<Integer,ArrayList<Conexion>> piscinaConexiones;
	private int segmentos=3, tamañoSegmentos=2;
	private int segmentosCreados=0;
	
	private Pool(){
		piscinaConexiones=new HashMap<Integer,ArrayList<Conexion>>();
	}

	private synchronized static Pool createInstance() {
		INSTANCE = new Pool();
		return INSTANCE;
    }
	
	
	private int conexionesDisponibles(){
		int conexionesRestantes=0;
		for (int segmento=0;segmento<this.segmentos;segmento++){
			if(piscinaConexiones.containsKey(segmento)){
				conexiones= piscinaConexiones.get(segmento);
				for(int i=0; i<conexiones.size();i++){
					Conexion conexion=conexiones.get(i);
					if(conexion.getEstado()){
						conexionesRestantes+=1;
					}
				}
			}else{
				break;
			}
		}
		return conexionesRestantes;
	}
 
	
}
 
