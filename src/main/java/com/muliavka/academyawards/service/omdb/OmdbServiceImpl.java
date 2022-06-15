package com.muliavka.academyawards.service.omdb;

import com.muliavka.academyawards.service.movie.dto.MovieInfoForUpdateDto;
import com.muliavka.academyawards.service.omdb.dto.OmdbInfoDto;
import com.muliavka.academyawards.service.omdb.mapper.OmdbDataToDataBaseDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.muliavka.academyawards.util.LoggerUtil.logException;
import static com.muliavka.academyawards.util.LoggerUtil.logRequest;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

@Service
public class OmdbServiceImpl implements OmdbService {

    private static final Logger logger = LoggerFactory.getLogger(OmdbServiceImpl.class);

    private final OmdbDataToDataBaseDataMapper omdbMapper;
    private final RestTemplate restTemplate;

    @Autowired
    public OmdbServiceImpl(OmdbDataToDataBaseDataMapper omdbMapper, RestTemplate restTemplate) {
        this.omdbMapper = omdbMapper;
        this.restTemplate = restTemplate;
    }

    @Value("${omdb.api.key}")
    private String apiKey;
    @Value("${omdb.api.uri}")
    private String omdbUri;

    @Override
    public List<MovieInfoForUpdateDto> getOmdbDataForUpdateMovie(Map<Long, String> movieIdNomineeMap) {
        final String methodName = "getOmdbDataForUpdateMovie";
        logRequest(logger, methodName, movieIdNomineeMap);

        final List<MovieInfoForUpdateDto> omdbInfoForUpdate = new ArrayList<>(getCapacityForArrayFromMap(movieIdNomineeMap.size()));
        if (isNotEmpty(movieIdNomineeMap)) {
            movieIdNomineeMap.entrySet()
                    .parallelStream()
                    .forEach(entry -> {
                        final String title = entry.getValue();
                        final Long movieId = entry.getKey();
                        ResponseEntity<OmdbInfoDto> omdbResponseEntity;
                        try {
                            omdbResponseEntity = restTemplate.getForEntity(omdbUri, OmdbInfoDto.class, apiKey, title);
                        } catch (Exception ignoring) {
                            logException(logger, methodName, ignoring);

                            return;
                        }

                        final OmdbInfoDto omdbInfo = omdbResponseEntity.getBody();
                        if (omdbInfo != null) {
                            final String omdbTitle = omdbInfo.getTitle();
                            if (title.equalsIgnoreCase(omdbTitle)) {
                                final MovieInfoForUpdateDto infoForUpdate = omdbMapper.map(movieId, omdbInfo);
                                omdbInfoForUpdate.add(infoForUpdate);
                            } else {
                                logger.error(String.format("OmdbService return movie with another title, search was by: %s, return: %s", title, omdbTitle));
                            }
                        } else {
                            logger.error(String.format("OmdbService return response without body, http status: %s\n" +
                                    "search by title: %s", omdbResponseEntity.getStatusCode(), title));
                        }
                    });
        }

        return omdbInfoForUpdate;
    }

    private int getCapacityForArrayFromMap(int mapCapacity) {
        return mapCapacity - (mapCapacity / 4);
    }
}
