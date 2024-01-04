package ptithcm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tkb")
public class Schedule {

	@Id
	private int id;
	
	private String mssv;
	@ManyToOne
	@JoinColumn(name = "mamh")
	private MonHoc monhoc;

	private String tuanhoc;
	private String thu; // ngay hoc trong tuan
	private String buoi; // sang || chieu
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMssv() {
		return mssv;
	}

	public void setMssv(String mssv) {
		this.mssv = mssv;
	}

	public MonHoc getMonhoc() {
		return monhoc;
	}

	public void setMonhoc(MonHoc monhoc) {
		this.monhoc = monhoc;
	}

	public String getTuanhoc() {
		return tuanhoc;
	}

	public void setTuanhoc(String tuanhoc) {
		this.tuanhoc = tuanhoc;
	}

	public String getThu() {
		return thu;
	}

	public void setThu(String thu) {
		this.thu = thu;
	}

	public String getBuoi() {
		return buoi;
	}

	public void setBuoi(String buoi) {
		this.buoi = buoi;
	}

	public String getThuByTuan(int tuan) {
		if (tuan <= 0) {
			return "";
		}

		if (getTuanhoc().charAt(tuan - 1) == '-') {
			return "";
		} else
			return getThu();

	}

	public String getBuoiByTuan(int tuan) {
		if (tuan <= 0) {
			return "";
		}

		if (getTuanhoc().charAt(tuan - 1) == '-') {
			return "";
		} else
			return getThu();
	}
	
	public boolean isHocTuan(int tuan) { // check xem co hoc tuan hien tai
		if (tuan <= 0) {
			return false;
		}

		if (getTuanhoc().charAt(tuan - 1) == '-') {
			return false;
		} else
			return true;
	
	}

}
