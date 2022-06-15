package com.muliavka.academyawards.dao.repository;

import com.muliavka.academyawards.dao.entity.RatingEntity;
import com.muliavka.academyawards.dao.entity.RatingId;
import com.muliavka.academyawards.dao.entity.projection.RatingViewProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, RatingId> {

    @Query(nativeQuery = true, value = "select grade, user_id as userId, movie_id as movieId " +
            "from USER_MOVIE_RATING where user_id = :userId AND movie_id = :titleId")
    RatingViewProjection getRatingViewByUserIdAndTitleId(@Param("userId") Long userId,
                                                         @Param("titleId") Long titleId);

    @Modifying
    @Query(nativeQuery = true, value = "insert into USER_MOVIE_RATING values (:titleId, :userId, :grade)")
    void insertTitleRatingFromUser(@Param("titleId") Long titleId,
                                   @Param("userId") Long userId,
                                   @Param("grade") Integer grade);

    @Modifying
    @Query(nativeQuery = true, value = "update USER_MOVIE_RATING r set r.grade = :grade " +
            "where r.user_id = :userId and r.movie_id = :titleId")
    void updateGradeByUserIdAndTitleId(@Param("titleId") Long titleId,
                                       @Param("userId") Long userId,
                                       @Param("grade") Integer grade);

}
