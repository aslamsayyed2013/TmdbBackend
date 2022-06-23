package co.incubyte;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TmdbControllerShould {

  TmdbController tmdbController;
  TmdbService tmdbService;

  @BeforeEach
  void setUp() {
    tmdbService = mock(TmdbService.class);
    tmdbController = new TmdbController(tmdbService);
  }

  @Test
  void find_by_artist_id() {

    assertNotNull(tmdbController.getByArtistId(500));

    verify(tmdbService).getArtistById(500);
  }

  @Test
  void find_actor_by_name() {

    assertNotNull(tmdbController.getByActorName("Tom Cruise"));

    verify(tmdbService).getActorByName("Tom Cruise");
  }

  @Test
  void get_movies_by_actor_id() {

    assertNotNull(tmdbController.getMoviesByActorId(500));

    verify(tmdbService).getMoviesByActorId(500);
  }

  @Test
  void get_tv_shows_by_actor_id() {

    assertNotNull(tmdbController.getTvShowsByActorId(500));

    verify(tmdbService).getTvShowsByActorId(500);
  }

  @Test
  void get_combined_credits_by_actor_id() {

    assertNotNull(tmdbController.getCombinedCreditsByActorId(500));

    verify(tmdbService).getCombinedCreditsByActorID(500);
  }
}