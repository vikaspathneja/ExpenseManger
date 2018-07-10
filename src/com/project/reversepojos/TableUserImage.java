package com.project.reversepojos;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "table_user_image")
public class TableUserImage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_id")
	private int imageId;

	@Column(name = "image_name")
	private String imageName;

	@Column(name = "user_id")
	private long userId;

	/*
	 * @Lob
	 * 
	 * @Column(name="image_blob") private Blob imageblob;
	 */
	@Lob
	@Column(name = "image_blob", columnDefinition = "mediumblob")
	private byte[] image;

	@Column(name = "image_temp_path")
	private String imageTempPath;

	@Column(name = "image_absolute_path")
	private String imageAbsolutePath;

	public byte[] getImage() {
		System.out.println("image getter");
		return image;
	}

	public void setImage(byte[] image) {
		System.out.println("image setter");
		this.image = image;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getImageTempPath() {
		return imageTempPath;
	}

	public void setImageTempPath(String imageTempPath) {
		this.imageTempPath = imageTempPath;
	}

	public String getImageAbsolutePath() {
		return imageAbsolutePath;
	}

	public void setImageAbsolutePath(String imageAbsolutePath) {
		this.imageAbsolutePath = imageAbsolutePath;
	}

	@Override
	public String toString() {
		return "\nTableUserImage [imageId=" + imageId + ", imageName=" + imageName + ", userId=" + userId + ", image="
				+ Arrays.toString(image) + ", imageTempPath=" + imageTempPath + ", imageAbsolutePath="
				+ imageAbsolutePath + "]";
	}


}
