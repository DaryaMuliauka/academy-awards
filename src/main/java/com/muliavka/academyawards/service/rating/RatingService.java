package com.muliavka.academyawards.service.rating;

import com.muliavka.academyawards.entity.Rating;
import com.muliavka.academyawards.entity.projection.RatingViewProjection;
import com.muliavka.academyawards.dto.rating.RatingDto;

public interface RatingService {

    /**
     * Search User's Movie rating by Movie id and UserEntity id.
     * Return view if rating present
     *
     * @param userId User entity id
     * @param titleId Movie id
     * @return rating View
     *
     * @see RatingViewProjection
     * @see Rating
     */
    RatingViewProjection getRatingByUserIdAndTitleId (Long userId, Long titleId);

    void saveRating(RatingDto rating);

    void updateRating(RatingDto rating);
}
