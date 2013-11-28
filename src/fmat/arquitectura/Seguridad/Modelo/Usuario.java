
package fmat.arquitectura.Seguridad.Modelo;

import java.util.ArrayList;

public class Usuario {
	private int id;
    private String nombre;
    private String contrase�a;
    private Perfil perfil;
    private ArrayList<Accion> listaAcciones;

    public Usuario(String nombre, String contrase�a, Perfil perfil, ArrayList<Accion> listaAcciones) {
        this.nombre = nombre;
        this.contrase�a = contrase�a;
        this.perfil = perfil;
        this.listaAcciones = listaAcciones;
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrase�a() {
        return contrase�a;
    }

    public void setContrase�a(String contrase�a) {
        this.contrase�a = contrase�a;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public ArrayList<Accion> getListaAcciones() {
        return listaAcciones;
    }

    public void setListaAcciones(ArrayList<Accion> listaAcciones) {
        this.listaAcciones = listaAcciones;
    }
    
    
}
