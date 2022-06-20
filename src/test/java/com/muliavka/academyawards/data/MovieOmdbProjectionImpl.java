package com.muliavka.academyawards.data;

import com.muliavka.academyawards.entity.projection.MovieOmdbProjection;

public class MovieOmdbProjectionImpl implements MovieOmdbProjection {

    private final Long id;
    private final String title;

    public MovieOmdbProjectionImpl(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public MovieOmdbProjectionImpl() {
        this.id = null;
        this.title = null;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
