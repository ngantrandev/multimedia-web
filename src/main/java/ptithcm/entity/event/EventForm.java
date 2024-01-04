package ptithcm.entity.event;

import org.springframework.web.multipart.MultipartFile;

public class EventForm {
	private String name;
	private String content;
	private MultipartFile file;
	
	
	
	public EventForm() {
		super();
	}
	public EventForm(String name, String content, MultipartFile file) {
		super();
		this.name = name;
		this.content = content;
		this.file = file;
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
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
}
