package fmat.arquitectura.pool.AdminPool.dominio;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public class Pool  {
	
	private static Pool INSTANCE=null;
	
	public static Pool getInstance() {
        return INSTANCE;
    }
	
	public synchronized static void createInstance(int segmentos, int tama�oSegmentos) {
        if (INSTANCE == null) { 
            INSTANCE = new Pool(segmentos,tama�oSegmentos);
        }
    }
 
	public Conexion obtenerConexionDisp() {
		Conexion conexionDisponible=null;
		for (int segmento=0;segmento<this.segmentos;segmento++){
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
		this.segmentos = segmentos;
	}

	public int getTama�oSegmentos() {
		return tama�oSegmentos;
	}

	public void setTama�oSegmentos(int tama�oSegmentos) {
		this.tama�oSegmentos = tama�oSegmentos;
	}
	
	public int getSegmentosCreados(){
		return this.segmentosCreados;
	}
	 
	private ArrayList<Conexion> conexiones;
	private HashMap<Integer,ArrayList<Conexion>> piscinaConexiones;
	private int segmentos, tama�oSegmentos;
	private int segmentosCreados=0;
	
	private Pool(int segmentos, int tama�oSegmentos){
		this.setSegmentos(segmentos);
		this.setTama�oSegmentos(tama�oSegmentos);
		piscinaConexiones=new HashMap<Integer,ArrayList<Conexion>>();
	}

	
}
 
