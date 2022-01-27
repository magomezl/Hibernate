package modelo;
// Generated 26 ene 2022 14:21:38 by Hibernate Tools 5.5.7.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Departamentos generated by hbm2java
 */

public class Departamentos implements java.io.Serializable {
	
	private byte deptno;
	private String dnombre;
	private String loc;
	private Set empleadoses = new HashSet(0);

	public Departamentos() {
	}

	public Departamentos(byte deptno) {
		this.deptno = deptno;
	}

	public Departamentos(byte deptno, String dnombre, String loc, Set empleadoses) {
		this.deptno = deptno;
		this.dnombre = dnombre;
		this.loc = loc;
		this.empleadoses = empleadoses;
	}

	public byte getDeptno() {
		return this.deptno;
	}

	public void setDeptno(byte deptno) {
		this.deptno = deptno;
	}

	public String getDnombre() {
		return this.dnombre;
	}

	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}

	public String getLoc() {
		return this.loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public Set getEmpleadoses() {
		return this.empleadoses;
	}

	public void setEmpleadoses(Set empleadoses) {
		this.empleadoses = empleadoses;
	}

}
