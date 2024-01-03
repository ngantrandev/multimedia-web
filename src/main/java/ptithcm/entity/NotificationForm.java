package ptithcm.entity;

import org.springframework.web.multipart.MultipartFile;

public class NotificationForm {
	private String title;
	private String content;
	private MultipartFile[] files;
	
	
	
	public NotificationForm() {

	}
	public NotificationForm(String title, String content, MultipartFile[] files) {
		super();
		this.title = title;
		this.content = content;
		this.files = files;
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
