package ptithcm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "point")
public class Point {
	@Id
    @Column(name = "mssv")
    private String studentCode;
	@Column(name =" nh")
	private int namhoc;
	@Column(name="hk")
	private int hocki;
	@ManyToOne
	@JoinColumn(name = "mamh")
	private Subject subject;
	@Column(name= "cc")
	private float cc;
	@Column(name="kt")
	private float kt;
	@Column(name ="th")
	private float th;
	@Column(name = "se")
	private float se;
	@Column(name = "thi")
	private float thi;
	
	public Point() {
		super();
	}
	public String getStudentCode() {
		return studentCode;
	}
	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}
	public int getNamhoc() {
		return namhoc;
	}
	public void setNamhoc(int namhoc) {
		this.namhoc = namhoc;
	}
	public int getHocki() {
		return hocki;
	}
	public void setHocki(int hocki) {
		this.hocki = hocki;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public float getCc() {
		return cc;
	}
	public void setCc(float cc) {
		this.cc = cc;
	}
	public float getKt() {
		return kt;
	}
	public void setKt(float kt) {
		this.kt = kt;
	}
	public float getTh() {
		return th;
	}
	public void setTh(float th) {
		this.th = th;
	}
	public float getSe() {
		return se;
	}
	public void setSe(float se) {
		this.se = se;
	}
	public float getThi() {
		return thi;
	}
	public void setThi(float thi) {
		this.thi = thi;
	}
	@Override
	public String toString() {
		return "Point [studentCode=" + studentCode + ", namhoc=" + namhoc + ", hocki=" + hocki + ", subject=" + subject
				+ ", cc=" + cc + ", kt=" + kt + ", th=" + th + ", se=" + se + ", thi=" + thi + "]";
	}
	
	
	
}
