package com.muliavka.academyawards.dao.entity.projection;

public interface MovieShortViewProjection {

    Long getId();

    String getTitle();

    String getOskarYear();

    Double getUsersRating();

    Integer getNumberOfUsersRating();

    Double getImdbRating();

    String getIsWon();

    Long getBoxOffice();
}
