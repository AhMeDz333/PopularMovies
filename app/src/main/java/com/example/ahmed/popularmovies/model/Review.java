package com.example.ahmed.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ahmed on 19/12/16.
 */

public class Review {

    @SerializedName("content")
    private String content;

    @SerializedName("id")
    private String id;

    @SerializedName("author")
    private String author;

    @SerializedName("url")
    private String url;

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getAuthor ()
    {
        return author;
    }

    public void setAuthor (String author)
    {
        this.author = author;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

	public class ReviewResponse {
		@SerializedName("id")
		private String id;

		@SerializedName("results")
		private List<Review> reviews;

		@SerializedName("page")
		private String page;

		@SerializedName("total_pages")
		private String totalPages;

		@SerializedName("total_results")
		private String totalResults;


		public void setId(String id) {
			this.id = id;
		}

		public void setReviews(List<Review> reviews) {
			this.reviews = reviews;
		}

		public void setPage(String page) {
			this.page = page;
		}

		public void setTotalPages(String totalPages) {
			this.totalPages = totalPages;
		}

		public void setTotalResults(String totalResults) {
			this.totalResults = totalResults;
		}

		public String getId() {
			return id;
		}

		public List<Review> getReviews() {
			return reviews;
		}

		public String getPage() {
			return page;
		}

		public String getTotalPages() {
			return totalPages;
		}

		public String getTotalResults() {
			return totalResults;
		}
	}
}
