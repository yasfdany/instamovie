package dev.studiocloud.meditation.data.services.response.tvDetailResponse;


import com.google.gson.annotations.SerializedName;


public class LastEpisodeToAir{

	@SerializedName("production_code")
	private String productionCode;

	@SerializedName("air_date")
	private String airDate;

	@SerializedName("overview")
	private String overview;

	@SerializedName("episode_number")
	private int episodeNumber;

	@SerializedName("show_id")
	private int showId;

	@SerializedName("vote_average")
	private double voteAverage;

	@SerializedName("name")
	private String name;

	@SerializedName("season_number")
	private int seasonNumber;

	@SerializedName("id")
	private int id;

	@SerializedName("still_path")
	private String stillPath;

	@SerializedName("vote_count")
	private int voteCount;

	public void setProductionCode(String productionCode){
		this.productionCode = productionCode;
	}

	public String getProductionCode(){
		return productionCode;
	}

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

	public void setEpisodeNumber(int episodeNumber){
		this.episodeNumber = episodeNumber;
	}

	public int getEpisodeNumber(){
		return episodeNumber;
	}

	public void setShowId(int showId){
		this.showId = showId;
	}

	public int getShowId(){
		return showId;
	}

	public void setVoteAverage(double voteAverage){
		this.voteAverage = voteAverage;
	}

	public double getVoteAverage(){
		return voteAverage;
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

	public void setStillPath(String stillPath){
		this.stillPath = stillPath;
	}

	public String getStillPath(){
		return stillPath;
	}

	public void setVoteCount(int voteCount){
		this.voteCount = voteCount;
	}

	public int getVoteCount(){
		return voteCount;
	}

	@Override
 	public String toString(){
		return 
			"LastEpisodeToAir{" + 
			"production_code = '" + productionCode + '\'' + 
			",air_date = '" + airDate + '\'' + 
			",overview = '" + overview + '\'' + 
			",episode_number = '" + episodeNumber + '\'' + 
			",show_id = '" + showId + '\'' + 
			",vote_average = '" + voteAverage + '\'' + 
			",name = '" + name + '\'' + 
			",season_number = '" + seasonNumber + '\'' + 
			",id = '" + id + '\'' + 
			",still_path = '" + stillPath + '\'' + 
			",vote_count = '" + voteCount + '\'' + 
			"}";
		}
}