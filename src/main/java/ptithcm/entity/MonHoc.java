package ptithcm.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="monhoc")
public class MonHoc {
	
	@Id
	private String mamh;
	private String tenmh;
	private String sotc;
	
	@Transient
    @OneToMany(mappedBy="monhoc",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    private Collection<Schedule> listTKB;
	
	public String getMamh() {
		return mamh;
	}
	public void setMamh(String mamh) {
		this.mamh = mamh;
	}
	public String getTenmh() {
		return tenmh;
	}
	public void setTenmh(String tenmh) {
		this.tenmh = tenmh;
	}
	public String getSotc() {
		return sotc;
	}
	public void setSotc(String sotc) {
		this.sotc = sotc;
	}
	

}
