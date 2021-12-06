package dev.studiocloud.meditation.data.services.response.movieDetailResponse;


import com.google.gson.annotations.SerializedName;


class ProductionCompaniesItem{

	@SerializedName("logo_path")
	private String logoPath;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("origin_country")
	private String originCountry;

	public void setLogoPath(String logoPath){
		this.logoPath = logoPath;
	}

	public String getLogoPath(){
		return logoPath;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setOriginCountry(String originCountry){
		this.originCountry = originCountry;
	}

	public String getOriginCountry(){
		return originCountry;
	}

	@Override
 	public String toString(){
		return 
			"ProductionCompaniesItem{" + 
			"logo_path = '" + logoPath + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",origin_country = '" + originCountry + '\'' + 
			"}";
		}
}