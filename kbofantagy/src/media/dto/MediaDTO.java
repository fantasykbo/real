package media.dto;

public class MediaDTO {
	//(id->videoId), (snippet-> publishedAt, title, description, (default->url(image)))
	Object videoId;
	Object publishedAt;
	Object title;
	Object description;
	Object image;
	
	public Object getVideoId() {
		return videoId;
	}

	public void setVideoId(Object videoId) {
		this.videoId = videoId;
	}

	public Object getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Object publishedAt) {
		this.publishedAt = publishedAt;
	}

	public Object getTitle() {
		return title;
	}

	public void setTitle(Object title) {
		this.title = title;
	}

	public Object getDescription() {
		return description;
	}

	public void setDescription(Object description) {
		this.description = description;
	}

	public Object getImage() {
		return image;
	}

	public void setImage(Object image) {
		this.image = image;
	}

	public MediaDTO(Object videoId, Object publishedAt, Object title, Object description, Object image) {
		this.videoId = videoId;
		this.publishedAt = publishedAt;
		this.title = title;
		this.description = description;
		this.image = image;
	}
		
}
