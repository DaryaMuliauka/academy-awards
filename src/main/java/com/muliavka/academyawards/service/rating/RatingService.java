package com.muliavka.academyawards.service.rating;

import com.muliavka.academyawards.dao.entity.RatingEntity;
import com.muliavka.academyawards.dao.entity.projection.RatingViewProjection;
import com.muliavka.academyawards.web.api.rating.RatingDto;

public interface RatingService {

    /**
     * Search User's Movie rating by MovieEntity id and UserEntity id.
     * Return view if rating present
     *
     * @param userId User entity id
     * @param titleId Movie id
     * @return rating View
     *
     * @see RatingViewProjection
     * @see RatingEntity
     */
    RatingViewProjection getRatingByUserIdAndTitleId (Long userId, Long titleId);

    void saveRating(RatingDto rating);

    void updateRating(RatingDto rating);
}
