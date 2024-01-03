package ptithcm.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "monhoc")
public class Subject {
	@Id
	@Column(name="mamh")
	private String id;
	@Column(name="tenmh")
	private String name;
	@Column(name="sotc")
	private int soTin;
	
	@OneToMany(mappedBy="subject",fetch=FetchType.EAGER)
	private Collection<Point> point;

	public Subject() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSoTin() {
		return soTin;
	}

	public void setSoTin(int soTin) {
		this.soTin = soTin;
	}

	public Collection<Point> getPoint() {
		return point;
	}

	public void setPoint(Collection<Point> point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", soTin=" + soTin + "]";
	}
	
	
}
