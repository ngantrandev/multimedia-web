package ptithcm.entity;

import org.springframework.web.multipart.MultipartFile;

public class AnPhamForm {
	private String ten;
	private String gia;
	private String soLuongTon;
	private String moTa;
	private MultipartFile file;
	
	public AnPhamForm() {
		super();
	}
	
	public AnPhamForm(String ten, String gia, String soLuongTon, String moTa, MultipartFile file) {
		super();
		this.ten = ten;
		this.gia = gia;
		this.soLuongTon = soLuongTon;
		this.moTa = moTa;
		this.file = file;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}

	public String getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(String soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public String toString(){
		return "AnPhamForm [ten=" + ten + ", gia=" + gia + ", soLuongTon=" + soLuongTon + ", moTa=" + moTa + ", file="
				+ file + "]";
	}
	
}
