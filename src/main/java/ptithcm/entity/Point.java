package ptithcm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "point")
@IdClass(PointId.class)
public class Point  {
	@Id
	@Column(name = "mssv")
    private String studentCode;
	@Id
	@Column(name = "nh")
	private int nh;
	@Column(name = "hk")
	private int hk;
	@Id
	@ManyToOne
	@JoinColumn(name = "mamh")
	private Subject subject;
	private float cc;
	private float kt;
	private float th;
	private float se;
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
	
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	public int getNh() {
		return nh;
	}




	public void setNh(int nh) {
		this.nh = nh;
	}




	public int getHk() {
		return hk;
	}




	public void setHk(int hk) {
		this.hk = hk;
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
		return "Point [studentCode=" + studentCode + ", namhoc=" + nh + ", hocki=" + hk + ", subject=" + subject
				+ ", cc=" + cc + ", kt=" + kt + ", th=" + th + ", se=" + se + ", thi=" + thi + "]";
	}
	
	
	
}
