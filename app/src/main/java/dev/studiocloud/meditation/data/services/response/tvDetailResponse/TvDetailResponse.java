package dev.studiocloud.meditation.data.services.response.tvDetailResponse;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class TvDetailResponse{

	@SerializedName("original_language")
	private String originalLanguage;

	@SerializedName("number_of_episodes")
	private int numberOfEpisodes;

	@SerializedName("networks")
	private List<NetworksItem> networks;

	@SerializedName("type")
	private String type;

	@SerializedName("backdrop_path")
	private String backdropPath;

	@SerializedName("genres")
	private List<GenresItem> genres;

	@SerializedName("popularity")
	private double popularity;

	@SerializedName("id")
	private int id;

	@SerializedName("number_of_seasons")
	private int numberOfSeasons;

	@SerializedName("vote_count")
	private int voteCount;

	@SerializedName("first_air_date")
	private String firstAirDate;

	@SerializedName("overview")
	private String overview;

	@SerializedName("seasons")
	private List<SeasonsItem> seasons;

	@SerializedName("languages")
	private List<String> languages;

	@SerializedName("created_by")
	private List<CreatedByItem> createdBy;

	@SerializedName("last_episode_to_air")
	private LastEpisodeToAir lastEpisodeToAir;

	@SerializedName("poster_path")
	private String posterPath;

	@SerializedName("origin_country")
	private List<String> originCountry;

	@SerializedName("production_companies")
	private List<ProductionCompaniesItem> productionCompanies;

	@SerializedName("original_name")
	private String originalName;

	@SerializedName("vote_average")
	private double voteAverage;

	@SerializedName("name")
	private String name;

	@SerializedName("episode_run_time")
	private List<Integer> episodeRunTime;

	@SerializedName("next_episode_to_air")
	private Object nextEpisodeToAir;

	@SerializedName("in_production")
	private boolean inProduction;

	@SerializedName("last_air_date")
	private String lastAirDate;

	@SerializedName("homepage")
	private String homepage;

	@SerializedName("status")
	private String status;

	public void setOriginalLanguage(String originalLanguage){
		this.originalLanguage = originalLanguage;
	}

	public String getOriginalLanguage(){
		return originalLanguage;
	}

	public void setNumberOfEpisodes(int numberOfEpisodes){
		this.numberOfEpisodes = numberOfEpisodes;
	}

	public int getNumberOfEpisodes(){
		return numberOfEpisodes;
	}

	public void setNetworks(List<NetworksItem> networks){
		this.networks = networks;
	}

	public List<NetworksItem> getNetworks(){
		return networks;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setBackdropPath(String backdropPath){
		this.backdropPath = backdropPath;
	}

	public String getBackdropPath(){
		return backdropPath;
	}

	public void setGenres(List<GenresItem> genres){
		this.genres = genres;
	}

	public List<GenresItem> getGenres(){
		return genres;
	}

	public void setPopularity(double popularity){
		this.popularity = popularity;
	}

	public double getPopularity(){
		return popularity;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setNumberOfSeasons(int numberOfSeasons){
		this.numberOfSeasons = numberOfSeasons;
	}

	public int getNumberOfSeasons(){
		return numberOfSeasons;
	}

	public void setVoteCount(int voteCount){
		this.voteCount = voteCount;
	}

	public int getVoteCount(){
		return voteCount;
	}

	public void setFirstAirDate(String firstAirDate){
		this.firstAirDate = firstAirDate;
	}

	public String getFirstAirDate(){
		return firstAirDate;
	}

	public void setOverview(String overview){
		this.overview = overview;
	}

	public String getOverview(){
		return overview;
	}

	public void setSeasons(List<SeasonsItem> seasons){
		this.seasons = seasons;
	}

	public List<SeasonsItem> getSeasons(){
		return seasons;
	}

	public void setLanguages(List<String> languages){
		this.languages = languages;
	}

	public List<String> getLanguages(){
		return languages;
	}

	public void setCreatedBy(List<CreatedByItem> createdBy){
		this.createdBy = createdBy;
	}

	public List<CreatedByItem> getCreatedBy(){
		return createdBy;
	}

	public void setLastEpisodeToAir(LastEpisodeToAir lastEpisodeToAir){
		this.lastEpisodeToAir = lastEpisodeToAir;
	}

	public LastEpisodeToAir getLastEpisodeToAir(){
		return lastEpisodeToAir;
	}

	public void setPosterPath(String posterPath){
		this.posterPath = posterPath;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public void setOriginCountry(List<String> originCountry){
		this.originCountry = originCountry;
	}

	public List<String> getOriginCountry(){
		return originCountry;
	}

	public void setProductionCompanies(List<ProductionCompaniesItem> productionCompanies){
		this.productionCompanies = productionCompanies;
	}

	public List<ProductionCompaniesItem> getProductionCompanies(){
		return productionCompanies;
	}

	public void setOriginalName(String originalName){
		this.originalName = originalName;
	}

	public String getOriginalName(){
		return originalName;
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

	public void setEpisodeRunTime(List<Integer> episodeRunTime){
		this.episodeRunTime = episodeRunTime;
	}

	public List<Integer> getEpisodeRunTime(){
		return episodeRunTime;
	}

	public void setNextEpisodeToAir(Object nextEpisodeToAir){
		this.nextEpisodeToAir = nextEpisodeToAir;
	}

	public Object getNextEpisodeToAir(){
		return nextEpisodeToAir;
	}

	public void setInProduction(boolean inProduction){
		this.inProduction = inProduction;
	}

	public boolean isInProduction(){
		return inProduction;
	}

	public void setLastAirDate(String lastAirDate){
		this.lastAirDate = lastAirDate;
	}

	public String getLastAirDate(){
		return lastAirDate;
	}

	public void setHomepage(String homepage){
		this.homepage = homepage;
	}

	public String getHomepage(){
		return homepage;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"TvDetailResponse{" + 
			"original_language = '" + originalLanguage + '\'' + 
			",number_of_episodes = '" + numberOfEpisodes + '\'' + 
			",networks = '" + networks + '\'' + 
			",type = '" + type + '\'' + 
			",backdrop_path = '" + backdropPath + '\'' + 
			",genres = '" + genres + '\'' + 
			",popularity = '" + popularity + '\'' + 
			",id = '" + id + '\'' + 
			",number_of_seasons = '" + numberOfSeasons + '\'' + 
			",vote_count = '" + voteCount + '\'' + 
			",first_air_date = '" + firstAirDate + '\'' + 
			",overview = '" + overview + '\'' + 
			",seasons = '" + seasons + '\'' + 
			",languages = '" + languages + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",last_episode_to_air = '" + lastEpisodeToAir + '\'' + 
			",poster_path = '" + posterPath + '\'' + 
			",origin_country = '" + originCountry + '\'' + 
			",production_companies = '" + productionCompanies + '\'' + 
			",original_name = '" + originalName + '\'' + 
			",vote_average = '" + voteAverage + '\'' + 
			",name = '" + name + '\'' + 
			",episode_run_time = '" + episodeRunTime + '\'' + 
			",next_episode_to_air = '" + nextEpisodeToAir + '\'' + 
			",in_production = '" + inProduction + '\'' + 
			",last_air_date = '" + lastAirDate + '\'' + 
			",homepage = '" + homepage + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}