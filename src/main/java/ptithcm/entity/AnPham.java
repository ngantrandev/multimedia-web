package ptithcm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="anpham")
public class AnPham {
	
	@Id
	@Column(name="maanpham")
	@GeneratedValue
	private int maAnPham;
	
	private String mssv;
	
	@Column(name="tenanpham")
	private String tenAnPham;
	
	private String gia;
	
	@Column(name="soluongton")
	private int soLuongTon;
	@Column(name="mota")
	private String moTa;
	@Column(name="hinhanh")
	private String imgUrl;
	
	public int getMaAnPham() {
		return maAnPham;
	}
	public void setMaAnPham(int maAnPham) {
		this.maAnPham = maAnPham;
	}
	public String getMssv() {
		return mssv;
	}
	public void setMssv(String mssv) {
		this.mssv = mssv;
	}
	public String getTenAnPham() {
		return tenAnPham;
	}
	public void setTenAnPham(String tenAnPham) {
		this.tenAnPham = tenAnPham;
	}
	public String getGia() {
		return gia;
	}
	public void setGia(String gia) {
		this.gia = gia;
	}
	public int getSoLuongTon() {
		return soLuongTon;
	}
	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public AnPham(int maAnPham, String mssv, String tenAnPham, String gia, int soLuongTon, String moTa, String imgUrl) {
		super();
		this.maAnPham = maAnPham;
		this.mssv = mssv;
		this.tenAnPham = tenAnPham;
		this.gia = gia;
		this.soLuongTon = soLuongTon;
		this.moTa = moTa;
		this.imgUrl = imgUrl;
	}
	public AnPham() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	

}