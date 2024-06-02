package com.openbook.Dao;

public class media {
	private Long ph_id;
	private String name;
    private byte[] photo;
    private String createdAt;

    public media(String name, byte[] photo, String createdAt) {
        this.name = name;
        this.photo = photo;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public byte[] getPhoto() {
        return photo;
    }

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Long getPh_id() {
		return ph_id;
	}

	public void setPh_id(Long ph_id) {
		this.ph_id = ph_id;
	}
    
    
}
