package com.stf.presentation.datamanager;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class BaseControllerDM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected List<Integer> idRolList;
	protected Boolean permitirPantalla = false;
	protected Boolean todosAllow, alumnoAllow, representanteAllow,
			profesorAllow, inspectorAllow, jefeAreaAllow,
			inspectorGeneralAllow, sicologoAllow, secretariaAllow, rectorAllow,
			administradorInstitucionAllow, administradorAplicacionAllow;

	public List<Integer> getIdRolList() {
		return idRolList;
	}

	public void setIdRolList(List<Integer> idRolList) {
		this.idRolList = idRolList;
	}

	public Boolean getPermitirPantalla() {
		return permitirPantalla;
	}

	public void setPermitirPantalla(Boolean permitirPantalla) {
		this.permitirPantalla = permitirPantalla;
	}

	public Boolean getTodosAllow() {
		return todosAllow;
	}

	public void setTodosAllow(Boolean todosAllow) {
		this.todosAllow = todosAllow;
	}

	public Boolean getAlumnoAllow() {
		return alumnoAllow;
	}

	public void setAlumnoAllow(Boolean alumnoAllow) {
		this.alumnoAllow = alumnoAllow;
	}

	public Boolean getRepresentanteAllow() {
		return representanteAllow;
	}

	public void setRepresentanteAllow(Boolean representanteAllow) {
		this.representanteAllow = representanteAllow;
	}

	public Boolean getProfesorAllow() {
		return profesorAllow;
	}

	public void setProfesorAllow(Boolean profesorAllow) {
		this.profesorAllow = profesorAllow;
	}

	public Boolean getInspectorAllow() {
		return inspectorAllow;
	}

	public void setInspectorAllow(Boolean inspectorAllow) {
		this.inspectorAllow = inspectorAllow;
	}

	public Boolean getJefeAreaAllow() {
		return jefeAreaAllow;
	}

	public void setJefeAreaAllow(Boolean jefeAreaAllow) {
		this.jefeAreaAllow = jefeAreaAllow;
	}

	public Boolean getInspectorGeneralAllow() {
		return inspectorGeneralAllow;
	}

	public void setInspectorGeneralAllow(Boolean inspectorGeneralAllow) {
		this.inspectorGeneralAllow = inspectorGeneralAllow;
	}

	public Boolean getSicologoAllow() {
		return sicologoAllow;
	}

	public void setSicologoAllow(Boolean sicologoAllow) {
		this.sicologoAllow = sicologoAllow;
	}

	public Boolean getSecretariaAllow() {
		return secretariaAllow;
	}

	public void setSecretariaAllow(Boolean secretariaAllow) {
		this.secretariaAllow = secretariaAllow;
	}

	public Boolean getRectorAllow() {
		return rectorAllow;
	}

	public void setRectorAllow(Boolean rectorAllow) {
		this.rectorAllow = rectorAllow;
	}

	public Boolean getAdministradorInstitucionAllow() {
		return administradorInstitucionAllow;
	}

	public void setAdministradorInstitucionAllow(
			Boolean administradorInstitucionAllow) {
		this.administradorInstitucionAllow = administradorInstitucionAllow;
	}

	public Boolean getAdministradorAplicacionAllow() {
		return administradorAplicacionAllow;
	}

	public void setAdministradorAplicacionAllow(
			Boolean administradorAplicacionAllow) {
		this.administradorAplicacionAllow = administradorAplicacionAllow;
	}

}
