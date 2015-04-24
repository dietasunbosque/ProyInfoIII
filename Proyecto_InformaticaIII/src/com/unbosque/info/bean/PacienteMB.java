package com.unbosque.info.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.dao.DataAccessException;

import com.unbosque.info.entidad.Paciente;
import com.unbosque.info.service.PacienteService;

@ManagedBean(name = "pacienteMB")
@SessionScoped

public class PacienteMB implements Serializable{

	private static final long serialVersionUID = -839758297635437781L;

	private PacienteMB registroSeleccionado;

	// Spring Customer Service is injected...
	@ManagedProperty("#{PacienteService}")
	PacienteService pacienteService;
	
	List <Paciente> pacienteList;
	
	private Integer id;
	private Integer identificacion;
	private String iden = "";
	private String nombresApellidos;
	private String estado;
	private String telefono;
	private String correo;
	private String progNutricion;
	
	
	public void addPaciente (){
		try{	
			
			@SuppressWarnings("unused")
			RequestContext context = RequestContext.getCurrentInstance();
			
			Paciente paciente = new Paciente ();
			
			paciente.setId(id);
			paciente.setIdentificacion(Integer.parseInt(iden));
			paciente.setNombresApellidos(nombresApellidos);
			paciente.setEstado("A");
			paciente.setTelefono(telefono);
			paciente.setCorreo(correo);
			paciente.setProgNutricion(progNutricion);
			
			getPacienteService().addPaciente(paciente);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado Exitosamente", "Agregado Exitosamente"));
			
		}catch(DataAccessException e){
			e.printStackTrace();
		}
	}
	
	public List<Paciente> getPacientes (){
		pacienteList = new ArrayList<Paciente>();
		pacienteList.addAll(getPacienteService().getPacientes());
		for (int i = 0; i<pacienteList.size();i++){
			if(pacienteList.get(i).getEstado().equals("A"))
				pacienteList.get(i).setEstado("Activo");
			else
				pacienteList.get(i).setEstado("Inactivo");
			if(pacienteList.get(i).getProgNutricion().equals("S"))
				pacienteList.get(i).setProgNutricion("Afiliado");
			else
				pacienteList.get(i).setProgNutricion("NO Afiliado");
		}
		return pacienteList;
	}
	
	public void eliminar (Paciente pa){
		getPacienteService().eliminarPaciente(pa);
	}
	
	public String getIden() {
		return iden;
	}

	public void setIden(String iden) {
		this.iden = iden;
	}

	public PacienteMB getRegistroSeleccionado() {
		return registroSeleccionado;
	}

	public void setRegistroSeleccionado(PacienteMB registroSeleccionado) {
		this.registroSeleccionado = registroSeleccionado;
	}

	public PacienteService getPacienteService() {
		return pacienteService;
	}

	public void setPacienteService(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}

	public List<Paciente> getPacienteList() {
		return pacienteList;
	}

	public void setPacienteList(List<Paciente> pacienteList) {
		this.pacienteList = pacienteList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(Integer identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombresApellidos() {
		return nombresApellidos;
	}

	public void setNombresApellidos(String nombresApellidos) {
		this.nombresApellidos = nombresApellidos;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getProgNutricion() {
		return progNutricion;
	}

	public void setProgNutricion(String progNutricion) {
		this.progNutricion = progNutricion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
