package ptithcm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import ptithcm.entity.AnPham;

@Entity
@Table(name = "giohang")
public class Order {
	@Id
	@GeneratedValue
	private String id;
	@ManyToOne
	@JoinColumn(name = "maanpham")
	private AnPham anPham;

	@ManyToOne
	@JoinColumn(name = "mssv")
	private Student nguoiDat;

	@Column(name = "soluong")
	private int soLuong;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AnPham getAnPham() {
		return anPham;
	}

	public void setAnPham(AnPham anPham) {
		this.anPham = anPham;
	}

	public Student getNguoiDat() {
		return nguoiDat;
	}

	public void setNguoiDat(Student nguoiDat) {
		this.nguoiDat = nguoiDat;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

}
