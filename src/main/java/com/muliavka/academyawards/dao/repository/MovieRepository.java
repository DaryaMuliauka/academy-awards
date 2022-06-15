package com.muliavka.academyawards.dao.repository;

import com.muliavka.academyawards.dao.entity.MovieEntity;
import com.muliavka.academyawards.dao.entity.projection.MovieOmdbProjection;
import com.muliavka.academyawards.dao.entity.projection.MovieShortViewProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity,Long> {

    @Query(nativeQuery = true, value = "select id, nominee as title, oskar_year as oskarYear, is_won as isWinner, " +
            "avg(grade) as usersRating, count(grade) as numberOfUsersRating, imdb_rating as imdbRating, box_office as boxOffice " +
            "from movie as a " +
            "left join user_movie_rating as b " +
            "on a.id = b.movie_id group by id",
    countQuery = "select count(id) from movie")
    Page<MovieShortViewProjection> getAllMoviesShortInfo(Pageable page);

    @Query(nativeQuery = true, value = "select id, nominee as title from movie")
    List<MovieOmdbProjection> getAllIdAndTitle();

    @Modifying
    @Query("update MovieEntity m set " +
            "m.boxOffice = ifnull(:boxOffice, m.boxOffice), " +
            "m.imdbRating = ifnull(:imdbRating, m.imdbRating) " +
            "where m.id = :id")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void updateBoxOfficeAndOmdbRatingById(@Param("id") Long id,
                                          @Param("boxOffice") Long boxOffice,
                                          @Param("imdbRating") Double imdbRating);
}
