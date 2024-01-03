package ptithcm.entity.notification;

import org.springframework.web.multipart.MultipartFile;

public class NotificationFormUpdate {
	private String notifiCode;
	private String title;
	private String content;
	private String time;
	private MultipartFile[] files;
	
	
	
	public NotificationFormUpdate() {

	}
	
	
	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public NotificationFormUpdate(String notifiCode, String title, String content,String time) {
		super();
		this.notifiCode = notifiCode;
		this.title = title;
		this.content = content;
		this.time = time;
	}
	
	public String getNotifiCode() {
		return notifiCode;
	}



	public void setNotifiCode(String notifiCode) {
		this.notifiCode = notifiCode;
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
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
}
