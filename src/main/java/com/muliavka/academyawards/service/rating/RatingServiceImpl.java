package com.muliavka.academyawards.service.rating;

import com.muliavka.academyawards.entity.projection.RatingViewProjection;
import com.muliavka.academyawards.repository.RatingRepository;
import com.muliavka.academyawards.dto.rating.RatingDto;
import com.muliavka.academyawards.util.LoggerUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingServiceImpl implements RatingService {

    private final RatingRepository repository;

    @Override
    @Transactional(readOnly = true)
    public RatingViewProjection getRatingByUserIdAndTitleId (Long userId, Long titleId) {
        final String methodName = "getRatingByUserIdAndTitleId";

        return repository.getRatingViewByUserIdAndTitleId(userId, titleId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveRating(RatingDto rating) {
        final String methodName = "saveRating";

        repository.insertTitleRatingFromUser(rating.getTitleId(), rating.getUserId(), rating.getGrade());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateRating(RatingDto rating) {
        final String methodName = "updateRating";

        repository.updateGradeByUserIdAndTitleId(rating.getTitleId(), rating.getUserId(), rating.getGrade());
    }
}
