package dev.studiocloud.instamovie.data.services.response.movieDetailResponse;

import com.google.gson.annotations.SerializedName;

class GenresItem{
	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

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

	@Override
	public String toString(){
		return
		"GenresItem{" +
				"name = '" + name + '\'' +
				",id = '" + id + '\'' +
				"}";
	}
}