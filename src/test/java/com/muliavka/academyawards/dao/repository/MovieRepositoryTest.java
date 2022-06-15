package com.muliavka.academyawards.dao.repository;

import com.muliavka.academyawards.dao.entity.projection.MovieOmdbProjection;
import com.muliavka.academyawards.dao.entity.projection.MovieShortViewProjection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@DataJpaTest
@RunWith(SpringRunner.class)
@Sql(scripts = "/init_db.sql")
@Sql(scripts = "/insert_data.sql")
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository repository;

    @Test
    public void testGetAllMoviesShortInfo() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<MovieShortViewProjection> page = repository.getAllMoviesShortInfo(pageRequest);

        assertNotNull(page);
        assertTrue(page.isLast());
        assertNotNull(page.getContent());
        List<MovieShortViewProjection> content = page.getContent();
        assertEquals(content.size(), 3);
    }

    @Test
    public void testGetAllIdAndTitle() {
        List<MovieOmdbProjection> allIdAndTitle = repository.getAllIdAndTitle();

        assertNotNull(allIdAndTitle);
        assertEquals(allIdAndTitle.size(), 3);
    }
}
