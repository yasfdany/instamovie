package dev.studiocloud.meditation.data.services.response.tvDetailResponse;


import com.google.gson.annotations.SerializedName;


class CreatedByItem{

	@SerializedName("gender")
	private int gender;

	@SerializedName("credit_id")
	private String creditId;

	@SerializedName("name")
	private String name;

	@SerializedName("profile_path")
	private String profilePath;

	@SerializedName("id")
	private int id;

	public void setGender(int gender){
		this.gender = gender;
	}

	public int getGender(){
		return gender;
	}

	public void setCreditId(String creditId){
		this.creditId = creditId;
	}

	public String getCreditId(){
		return creditId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setProfilePath(String profilePath){
		this.profilePath = profilePath;
	}

	public String getProfilePath(){
		return profilePath;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"CreatedByItem{" + 
			"gender = '" + gender + '\'' + 
			",credit_id = '" + creditId + '\'' + 
			",name = '" + name + '\'' + 
			",profile_path = '" + profilePath + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}