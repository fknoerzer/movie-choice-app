package com.github.fknoerzer.backend.controller;

import com.github.fknoerzer.backend.model.Actor;
import com.github.fknoerzer.backend.model.Criteria;
import com.github.fknoerzer.backend.model.Genre;
import com.github.fknoerzer.backend.model.WatchProvider;
import com.github.fknoerzer.backend.repository.CriteriaRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CriteriaControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private CriteriaRepo criteriaRepo;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void cleanUp() {
        criteriaRepo.deleteAll();
    }

    @Test
    void getCriteriaById() {
        //Given
        criteriaRepo.insert(testCriteria1);
        String criteriaId = "1";

        //When
        List<Criteria> actual = webTestClient.get()
                .uri("/api/criteria/" + criteriaId)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(Criteria.class)
                .returnResult()
                .getResponseBody();

        //Then
        assertNotNull(actual);
        List<Criteria> expected = List.of(testCriteria1);
        assertEquals(expected, actual);
    }

    @Test
    void addNewCriteria() {
        //Given

        //When
        Criteria actual = webTestClient.post()
                .uri("http://localhost:" + port + "/api/criteria")
                .bodyValue(testCriteria1)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Criteria.class)
                .returnResult()
                .getResponseBody();

        //Then
        assertNotNull(actual);
        assertNotNull(actual.getId());
        Criteria expected = testCriteria1;
        expected.setId(actual.getId());
        assertEquals(expected, actual);

    }

    Criteria testCriteria1 = Criteria.builder()
            .actors(List.of(Actor.builder()
                    .actorName("Depp")
                    .actorName("Willis")
                    .actorName("Watson")
                    .build()))
            .id("1")
            .genres(List.of(Genre.builder()
                    .genreName("Action")
                    .genreName("Horror")
                    .genreName("Comedy")
                    .build()))
            .maxRuntime(150)
            .minRating(3)
            .minReleaseDate("2000")
            .watchProviders(List.of(WatchProvider.builder()
                    .watchProviderName("Netflix")
                    .watchProviderName("Amazon Prime")
                    .build()))
            .build();
}
