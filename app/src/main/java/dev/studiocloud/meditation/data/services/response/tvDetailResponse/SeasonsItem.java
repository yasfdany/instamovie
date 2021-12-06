package dev.studiocloud.meditation.data.services.response.tvDetailResponse;


import com.google.gson.annotations.SerializedName;


class SeasonsItem{

	@SerializedName("air_date")
	private String airDate;

	@SerializedName("overview")
	private String overview;

	@SerializedName("episode_count")
	private int episodeCount;

	@SerializedName("name")
	private String name;

	@SerializedName("season_number")
	private int seasonNumber;

	@SerializedName("id")
	private int id;

	@SerializedName("poster_path")
	private String posterPath;

	public void setAirDate(String airDate){
		this.airDate = airDate;
	}

	public String getAirDate(){
		return airDate;
	}

	public void setOverview(String overview){
		this.overview = overview;
	}

	public String getOverview(){
		return overview;
	}

	public void setEpisodeCount(int episodeCount){
		this.episodeCount = episodeCount;
	}

	public int getEpisodeCount(){
		return episodeCount;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setSeasonNumber(int seasonNumber){
		this.seasonNumber = seasonNumber;
	}

	public int getSeasonNumber(){
		return seasonNumber;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setPosterPath(String posterPath){
		this.posterPath = posterPath;
	}

	public String getPosterPath(){
		return posterPath;
	}

	@Override
 	public String toString(){
		return 
			"SeasonsItem{" + 
			"air_date = '" + airDate + '\'' + 
			",overview = '" + overview + '\'' + 
			",episode_count = '" + episodeCount + '\'' + 
			",name = '" + name + '\'' + 
			",season_number = '" + seasonNumber + '\'' + 
			",id = '" + id + '\'' + 
			",poster_path = '" + posterPath + '\'' + 
			"}";
		}
}