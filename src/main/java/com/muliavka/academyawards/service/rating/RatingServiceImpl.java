package com.muliavka.academyawards.service.rating;

import com.muliavka.academyawards.dao.entity.projection.RatingViewProjection;
import com.muliavka.academyawards.dao.repository.RatingRepository;
import com.muliavka.academyawards.web.api.rating.RatingDto;
import com.muliavka.academyawards.util.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RatingServiceImpl implements RatingService {

    private static final Logger logger = LoggerFactory.getLogger(RatingServiceImpl.class);

    private final RatingRepository repository;

    public RatingServiceImpl(RatingRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public RatingViewProjection getRatingByUserIdAndTitleId (Long userId, Long titleId) {
        final String methodName = "getRatingByUserIdAndTitleId";
        LoggerUtil.logRequest(logger, methodName, userId, titleId);

        return repository.getRatingViewByUserIdAndTitleId(userId, titleId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveRating(RatingDto rating) {
        final String methodName = "saveRating";
        LoggerUtil.logRequest(logger, methodName, rating);

        repository.insertTitleRatingFromUser(rating.getTitleId(), rating.getUserId(), rating.getGrade());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateRating(RatingDto rating) {
        final String methodName = "updateRating";
        LoggerUtil.logRequest(logger, methodName, rating);

        repository.updateGradeByUserIdAndTitleId(rating.getTitleId(), rating.getUserId(), rating.getGrade());
    }
}
