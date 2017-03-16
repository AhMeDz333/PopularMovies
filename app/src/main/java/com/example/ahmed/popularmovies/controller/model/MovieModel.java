package com.example.ahmed.popularmovies.controller.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ahmed on 17/12/16.
 */

@Table(name = "movies", id = "_id")
public class MovieModel extends Model implements Parcelable {
	@Column(name = "poster_path")
	@SerializedName("poster_path")
	private String posterPath;
	@Column(name = "adult")
	@SerializedName("adult")
	private boolean adult;
	@Column(name = "overview")
	@SerializedName("overview")
	private String overview;
	@Column(name = "release_date")
	@SerializedName("release_date")
	private String releaseDate;
	@Column(name = "id")
	@SerializedName("id")
	private Integer movieID;
	@Column(name = "original_title")
	@SerializedName("original_title")
	private String originalTitle;
	@Column(name = "original_language")
	@SerializedName("original_language")
	private String originalLanguage;
	@Column(name = "title")
	@SerializedName("title")
	private String title;
	@Column(name = "backdrop_path")
	@SerializedName("backdrop_path")
	private String backdropPath;
	@Column(name = "popularity")
	@SerializedName("popularity")
	private Double popularity;
	@Column(name = "vote_count")
	@SerializedName("vote_count")
	private Integer voteCount;
	@Column(name = "video")
	@SerializedName("video")
	private Boolean video;
	@Column(name = "vote_average")
	@SerializedName("vote_average")
	private Double voteAverage;
	@Column(name = "favourite")
	private boolean isFavourite;

    public MovieModel() {
	    super();
    }

    public MovieModel(String posterPath, boolean adult, String overview, String releaseDate, Integer movieID,
                      String originalTitle, String originalLanguage, String title, String backdropPath, Double popularity,
                      Integer voteCount, Boolean video, Double voteAverage, Boolean isFavourite) {
        this.posterPath = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.movieID = movieID;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.title = title;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.video = video;
        this.voteAverage = voteAverage;
	    this.isFavourite = isFavourite;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getMovieId() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.posterPath);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeValue(this.movieID);
        dest.writeString(this.originalTitle);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.title);
        dest.writeString(this.backdropPath);
        dest.writeValue(this.popularity);
        dest.writeValue(this.voteCount);
        dest.writeValue(this.video);
        dest.writeValue(this.voteAverage);
	    dest.writeValue(this.isFavourite);
    }

    protected MovieModel(Parcel in) {
        this.posterPath = in.readString();
        this.adult = in.readByte() != 0;
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.movieID = (Integer) in.readValue(Integer.class.getClassLoader());
        this.originalTitle = in.readString();
        this.originalLanguage = in.readString();
        this.title = in.readString();
        this.backdropPath = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.voteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.video = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.isFavourite = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel source) {
            return new MovieModel(source);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

	public boolean isFavourite() {
		return isFavourite;
	}

	public void setFavourite(boolean favourite) {
		isFavourite = favourite;
	}

	public void toggleFavourite() {
		isFavourite = !isFavourite;
	}

	public static class MoviesResponse {
		@SerializedName("page")
		private int page;
		@SerializedName("results")
		private List<MovieModel> results;
		@SerializedName("total_results")
		private int totalResults;
		@SerializedName("total_pages")
		private int totalPages;

		public MoviesResponse() {
			super();
		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public List<MovieModel> getResults() {
			return results;
		}

		public void setResults(List<MovieModel> results) {
			this.results = results;
		}

		public int getTotalResults() {
			return totalResults;
		}

		public void setTotalResults(int totalResults) {
			this.totalResults = totalResults;
		}

		public int getTotalPages() {
			return totalPages;
		}

		public void setTotalPages(int totalPages) {
			this.totalPages = totalPages;
		}
	}
}