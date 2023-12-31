package ptithcm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="anpham")
public class AnPham {
	
	@Id
	@Column(name="maanpham")
	private String maAnPham;
	
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
	public String getMaAnPham() {
		return maAnPham;
	}
	public void setMaAnPham(String maAnPham) {
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
	
	
	
	
	

}
