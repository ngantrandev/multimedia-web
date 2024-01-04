package ptithcm.entity.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ptithcm.entity.Student;

@Entity
@Table(name = "chitietsukien")
public class Event {
	@Id
	@Column(name="masukien")
	private String eventCode;
	
	@Column(name="ten")
	private String name;
	
	@Column(name="noidung")
	private String content;
	
	@Column(name="thoigian")
	private String time;
	
	@Column(name="thumb")
	private String nameThumbImg;
	
	@ManyToOne
	@JoinColumn(name="poster")
	private Student poster;
	
	public Event() {
		super();
	}

	public Event(String eventCode, String name, String content, String time, String nameThumbImg, Student poster) {
		super();
		this.eventCode = eventCode;
		this.name = name;
		this.content = content;
		this.time = time;
		this.nameThumbImg = nameThumbImg;
		this.poster = poster;
	}

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getNameThumbImg() {
		return nameThumbImg;
	}

	public void setNameThumbImg(String nameThumbImg) {
		this.nameThumbImg = nameThumbImg;
	}

	public Student getPoster() {
		return poster;
	}

	public void setPoster(Student poster) {
		this.poster = poster;
	}
	
	
}
