package ptithcm.entity;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.util.Collection;

@Entity
@Table(name = "ctthongbao")
public class Notification {
	
	
    public Notification() {
		super();
	}

	@Id
    @Column(name = "matb")
    private String notifyCode;

    @Column(name = "noidung")
    private String content; 
    
    @Column(name="tieude")
    private String title;
    
    @ManyToOne
    @JoinColumn(name="poster")
    private Student poster;

    @Column(name = "sendat")
    private String timeSent;

    private String type;

    @Column(name = "list_file_name")
    private String files;

    @ManyToMany(mappedBy = "notifications")
    private Collection<Student> students;

    public String getNotifyCode() {
        return notifyCode;
    }

    public void setNotifyCode(String notifyCode) {
        this.notifyCode = notifyCode;
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(String timeSent) {
        this.timeSent = timeSent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }
    
    

	public Student getPoster() {
		return poster;
	}

	public void setPoster(Student poster) {
		this.poster = poster;
	}

	public Notification(String notifyCode, String content, String title, Student poster, String timeSent, String type,
			String files, Collection<Student> students) {
		super();
		this.notifyCode = notifyCode;
		this.content = content;
		this.title = title;
		this.poster = poster;
		this.timeSent = timeSent;
		this.type = type;
		this.files = files;
		this.students = students;
	}
	
	
    
    
}
