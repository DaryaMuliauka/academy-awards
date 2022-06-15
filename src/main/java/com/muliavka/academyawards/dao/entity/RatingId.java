package com.muliavka.academyawards.dao.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RatingId implements Serializable {

    /**
     * Movie id
     */
    @ManyToOne
    @JoinColumn (name="movie_id")
    private MovieEntity movieId;

    /**
     * user id
     */
    @ManyToOne
    @JoinColumn (name="user_id")
    private UserEntity userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingId ratingId = (RatingId) o;
        return movieId == ratingId.movieId && userId == ratingId.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, userId);
    }

    public MovieEntity getMovieId() {
        return movieId;
    }

    public void setMovieId(MovieEntity movieId) {
        this.movieId = movieId;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }
}
