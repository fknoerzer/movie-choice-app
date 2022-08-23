package com.github.fknoerzer.backend.service;


import com.github.fknoerzer.backend.model.Actor;
import com.github.fknoerzer.backend.model.Criteria;
import com.github.fknoerzer.backend.model.Genre;
import com.github.fknoerzer.backend.model.WatchProvider;
import com.github.fknoerzer.backend.repository.CriteriaRepo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CriteriaServiceTest {

    private final CriteriaRepo criteriaRepo = mock(CriteriaRepo.class);

    private final CriteriaService criteriaService = new CriteriaService(criteriaRepo);
    
    @Test
    void getById_WhenIdIsValid() {
        //Given
        when(criteriaRepo.findById("1")).thenReturn(
                Optional.of(testCriteria1));

        //When
        Criteria actual = criteriaService.getById("1");

        //Then
        Criteria expected = testCriteria1;
        verify(criteriaRepo).findById("1");
        assertEquals(expected, actual);
    }

    @Test
    void getById_WhenIdIsNotValid() {
        //Given
        when(criteriaRepo.findById("3")).thenReturn(
                Optional.empty());

        //When //Then
        assertThrows(NoSuchElementException.class, () -> criteriaService.getById("3"));
        verify(criteriaRepo).findById("3");
    }

    @Test
    void addNewCriteria_WhenValid() {
        //Given
        when(criteriaRepo.insert(testCriteria1)).thenReturn(testCriteria1);

        //When
        Criteria actual = criteriaService.addNewCriteria(testCriteria1);

        //Then
        Criteria expected = testCriteria1;
        verify(criteriaRepo).insert(testCriteria1);
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