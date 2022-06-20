package com.muliavka.academyawards.service.rating;

import com.muliavka.academyawards.entity.projection.RatingViewProjection;
import com.muliavka.academyawards.repository.RatingRepository;
import com.muliavka.academyawards.data.RatingViewProjectionImp;
import com.muliavka.academyawards.dto.rating.RatingDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RatingServiceImplTest {

    @Mock
    private RatingRepository ratingRepository;

    private RatingServiceImpl ratingService;

    private RatingDto rating;

    @Before
    public void init() {
        ratingService = new RatingServiceImpl(ratingRepository);
        rating = new RatingDto();
        rating.setGrade(1);
        rating.setTitleId(1L);
        rating.setUserId(1L);
    }

    @Test
    public void testGetRatingByUserIdAndTitleId() {
        when(ratingRepository.getRatingViewByUserIdAndTitleId(1L, 1L)).thenReturn(new RatingViewProjectionImp());

        RatingViewProjection result = ratingService.getRatingByUserIdAndTitleId(1L, 1L);

        verify(ratingRepository).getRatingViewByUserIdAndTitleId(1L, 1L);
        assertNotNull(result);
    }

    @Test
    public void testSaveRating() {
        doNothing().when(ratingRepository).insertTitleRatingFromUser(1L, 1L, 1);

        ratingService.saveRating(rating);

        verify(ratingRepository).insertTitleRatingFromUser(1L, 1L, 1);
    }

    @Test
    public void testUpdateRating() {
        doNothing().when(ratingRepository).updateGradeByUserIdAndTitleId(1L, 1L, 1);

        ratingService.updateRating(rating);

        verify(ratingRepository).updateGradeByUserIdAndTitleId(1L,1L,1);
    }
}
