package ptithcm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "donhang")
public class DonHang {

	@Id
	@Column(name = "madonhang")
	@GeneratedValue
	private int maDonHang;

	@ManyToOne
	@JoinColumn(name = "buyer")
	private Student student;

	
	@ManyToOne
	@JoinColumn(name="maanpham")
	private AnPham anPham;
	
	@Column(name = "soluong")
	private int soLuong;
	private String time;
	private byte state;

	public String getMaDonHang() {
		return maDonHang;
	}

	public void setMaDonHang(String maDonHang) {
		this.maDonHang = maDonHang;
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public AnPham getAnPham() {
		return anPham;
	}

	public void setAnPham(AnPham anPham) {
		this.anPham = anPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	@Override
	public String toString() {
	    return "DonHang{" +
	            "maDonHang='" + maDonHang + '\'' +
	            ", student=" + student.getStudentCode() + // Lấy mã sinh viên hoặc thông tin khác của sinh viên
	            ", anPham=" + anPham.getMaAnPham() + // Lấy mã ấn phẩm hoặc thông tin khác của ấn phẩm
	            ", soLuong=" + soLuong +
	            ", time='" + time + '\'' +
	            ", state=" + state +
	            '}';
	}

	
	
	

}
