package com.example.ahmed.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ahmed on 17/12/16.
 */

public class MovieModel implements Parcelable {
//	@Column(name = "poster_path")
	@SerializedName("poster_path")
	private String posterPath;
//	@Column(name = "overview")
	@SerializedName("overview")
	private String overview;
//	@Column(name = "release_date")
	@SerializedName("release_date")
	private String releaseDate;
//	@Column(name = "id")
	@SerializedName("id")
	private Integer movieID;
//	@Column(name = "title")
	@SerializedName("title")
	private String title;
//	@Column(name = "backdrop_path")
	@SerializedName("backdrop_path")
	private String backdropPath;
//	@Column(name = "vote_average")
	@SerializedName("vote_average")
	private Double voteAverage;
//	@Column(name = "favourite")
	private boolean isFavourite;

    public MovieModel() {
	    super();
    }

    public MovieModel(Integer movieID, String title, String overview,
                      String posterPath, String backdropPath,
                      String releaseDate, Double voteAverage, Boolean isFavourite) {
	    this.movieID = movieID;
	    this.title = title;
	    this.overview = overview;
	    this.posterPath = posterPath;
	    this.backdropPath = backdropPath;
	    this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
	    this.isFavourite = isFavourite;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
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
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeValue(this.movieID);
        dest.writeString(this.title);
	    dest.writeString(this.backdropPath);
        dest.writeValue(this.voteAverage);
	    dest.writeValue(this.isFavourite);
    }

    protected MovieModel(Parcel in) {
        this.posterPath = in.readString();
        this.overview = in.readString();
	    this.releaseDate = in.readString();
	    this.movieID = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
	    this.backdropPath = in.readString();
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