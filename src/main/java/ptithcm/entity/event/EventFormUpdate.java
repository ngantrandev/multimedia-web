package ptithcm.entity.event;

import org.springframework.web.multipart.MultipartFile;

public class EventFormUpdate {
	private String eventCode;
	private String name;
	private String content;
	private String time;
	private MultipartFile file;
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
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile files) {
		this.file = files;
	}
	public EventFormUpdate(String eventCode, String name, String content, String time) {
		super();
		this.eventCode = eventCode;
		this.name = name;
		this.content = content;
		this.time = time;
	}
	public EventFormUpdate() {
		super();
	}
	
	
}
