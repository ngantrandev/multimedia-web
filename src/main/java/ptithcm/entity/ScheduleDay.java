package ptithcm.entity;

public class ScheduleDay {
	private MonHoc monhoc;
	private String thu; // ngay hoc trong tuan
	private String buoi; // sang || chieu
	private String tengv;
	private String thoigian;
	
	public String getTengv() {
		return tengv;
	}
	public void setTengv(String tengv) {
		this.tengv = tengv;
	}
	public String getThoigian() {
		return thoigian;
	}
	public void setThoigian(String thoigian) {
		this.thoigian = thoigian;
	}
	public MonHoc getMonhoc() {
		return monhoc;
	}
	public void setMonhoc(MonHoc monhoc) {
		this.monhoc = monhoc;
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
	public ScheduleDay() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ScheduleDay(MonHoc monhoc, String thu, String buoi, String tengv, String thoigian) {
		super();
		this.monhoc = monhoc;
		this.thu = thu;
		this.buoi = buoi;
		this.tengv = tengv;
		this.thoigian = thoigian;
	}
	
	
	
}
