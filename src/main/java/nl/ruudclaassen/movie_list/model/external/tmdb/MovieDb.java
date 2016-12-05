package nl.ruudclaassen.movie_list.model.external.tmdb;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import nl.ruudclaassen.movie_list.model.external.tmdb.core.IdElement;
import nl.ruudclaassen.movie_list.model.external.tmdb.core.MovieKeywords;
import nl.ruudclaassen.movie_list.model.external.tmdb.core.ResultsPage;
import nl.ruudclaassen.movie_list.model.external.tmdb.keywords.Keyword;
import nl.ruudclaassen.movie_list.model.external.tmdb.people.PersonCast;
import nl.ruudclaassen.movie_list.model.external.tmdb.people.PersonCrew;

@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public class MovieDb extends IdElement implements Multi {

	@JsonProperty("title")
	private String title;
	@JsonProperty("original_title")
	private String originalTitle;

	@JsonProperty("popularity")
	private float popularity;

	@JsonProperty("backdrop_path")
	private String backdropPath;
	@JsonProperty("poster_path")
	private String posterPath;

	@JsonProperty("release_date")
	private String releaseDate;
	@JsonProperty("adult")
	private boolean adult;
	@JsonProperty("belongs_to_collection")
	private Collection belongsToCollection;
	@JsonProperty("budget")
	private long budget;
	@JsonProperty("genres")
	private List<Genre> genres;
	@JsonProperty("homepage")
	private String homepage;

	@JsonProperty("overview")
	private String overview;

	// todo still there??
	@JsonProperty("imdb_id")
	private String imdbID;

	@JsonProperty("original_language")
	private String originalLanguage;

	@JsonProperty("production_companies")
	private List<ProductionCompany> productionCompanies;
	@JsonProperty("production_countries")
	private List<ProductionCountry> productionCountries;

	@JsonProperty("revenue")
	private long revenue;
	@JsonProperty("runtime")
	private int runtime;

	@JsonProperty("spoken_languages")
	private List<Language> spokenLanguages;

	@JsonProperty("tagline")
	private String tagline;

	@JsonProperty("rating")
	private float userRating;

	@JsonProperty("vote_average")
	private float voteAverage;
	@JsonProperty("vote_count")
	private int voteCount;

	@JsonProperty("status")
	private String status;

	// Appendable responses

	@JsonProperty("alternative_titles")
	private MoviesAlternativeTitles alternativeTitles;

	@JsonProperty("credits")
	private Credits credits;

	@JsonProperty("images")
	private MovieImages images;

	// note: it seems to be a flaw in their api, because a paged result would be
	// more consistent
	@JsonProperty("keywords")
	private MovieKeywords keywords;

	@JsonProperty("releases")
	private ReleaseInfoResults releases;

	@JsonProperty("videos")
	private Video.Results videos;

	@JsonProperty("translations")
	private MovieTranslations translations;

	@JsonProperty("similar_movies")
	private ResultsPage<MovieDb> similarMovies;

	@JsonProperty("reviews")
	private ResultsPage<Reviews> reviews;

	@JsonProperty("lists")
	private ResultsPage<MovieList> lists;

	public String getBackdropPath() {
		return backdropPath;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public float getPopularity() {
		return popularity;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public String getTitle() {
		return title;
	}

	public boolean isAdult() {
		return adult;
	}

	public Collection getBelongsToCollection() {
		return belongsToCollection;
	}

	public long getBudget() {
		return budget;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public String getHomepage() {
		return homepage;
	}

	public String getImdbID() {
		return imdbID;
	}

	public String getOriginalLanguage() {
		return originalLanguage;
	}

	public String getOverview() {
		return overview;
	}

	public List<ProductionCompany> getProductionCompanies() {
		return productionCompanies;
	}

	public List<ProductionCountry> getProductionCountries() {
		return productionCountries;
	}

	public long getRevenue() {
		return revenue;
	}

	public int getRuntime() {
		return runtime;
	}

	public List<Language> getSpokenLanguages() {
		return spokenLanguages;
	}

	public String getTagline() {
		return tagline;
	}

	public float getVoteAverage() {
		return voteAverage;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public String getStatus() {
		return status;
	}

	public List<AlternativeTitle> getAlternativeTitles() {
		return alternativeTitles != null ? alternativeTitles.getTitles() : null;
	}

	public List<PersonCast> getCast() {
		return credits != null ? credits.getCast() : null;
	}

	public List<PersonCrew> getCrew() {
		return credits != null ? credits.getCrew() : null;
	}

	public List<Artwork> getImages(ArtworkType... artworkTypes) {
		return images != null ? images.getAll(artworkTypes) : null;
	}

	public List<Keyword> getKeywords() {
		return keywords != null ? keywords.getKeywords() : null;
	}

	public List<ReleaseInfo> getReleases() {
		return releases != null ? releases.getCountries() : null;
	}

	public List<Video> getVideos() {
		return videos != null ? videos.getVideos() : null;
	}

	public List<Translation> getTranslations() {
		return translations != null ? translations.getTranslations() : null;
	}

	public List<MovieDb> getSimilarMovies() {
		return similarMovies != null ? similarMovies.getResults() : null;
	}

	public List<MovieList> getLists() {
		return lists != null ? lists.getResults() : null;
	}

	public List<Reviews> getReviews() {
		return reviews != null ? reviews.getResults() : null;
	}

	public Credits getCredits() {
		return credits;
	}

	public float getUserRating() {
		return userRating;
	}

	@Override
	public String toString() {
		return title + " - " + releaseDate;
	}

	@Override
	public MediaType getMediaType() {
		return MediaType.MOVIE;
	}
}