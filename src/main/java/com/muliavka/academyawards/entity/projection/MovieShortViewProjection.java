package com.muliavka.academyawards.entity.projection;

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
