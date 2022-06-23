package co.incubyte;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@MicronautTest
class TmdbKataTest {

  @Inject
  TmdbTestOnlyClient tmdbTestOnlyClient;

  @BeforeEach
  void setUp() {
    TmdbMockServer.start();
  }

  @AfterEach
  void tearDown() {
    TmdbMockServer.stop();
  }

  @Test
  void check_for_artist_by_name() {
    Optional<List<Actor>> actorByName = tmdbTestOnlyClient.getByName("tom");
    if (actorByName.isPresent()) {
      List<Actor> actorList = actorByName.get();
      assertThat(actorList.get(0).getName()).isEqualTo("Tom Cruise");
      assertThat(actorList.get(0).getId()).isEqualTo(1136406);
      assertThat(actorList.get(0).getProfilePath()).isEqualTo("/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg");
    }
//    TmdbMockServer.verifyFindByActorsname("tom");
  }

  @Test
  void check_for_artist_by_id() {
    Optional<Artist> mayBeArtist = tmdbTestOnlyClient.getById(500);
    TmdbMockServer.verifyFindByIdActorsRequest(500);
    if (mayBeArtist.isPresent()) {
      Artist artist = mayBeArtist.get();
      assertActor(artist);
    }
  }

  @Test
  void check_for_movie_credits_by_actor_id() {
    Optional<List<Movies>> moviesByActorId = tmdbTestOnlyClient.getMoviesById(500);

    if (moviesByActorId.isPresent()) {
      List<Movies> moviesList = moviesByActorId.get();
      Assertions.assertFalse(moviesList.isEmpty());
    }
    TmdbMockServer.verifyMovieCreditsByActorId(500);
  }

  @Test
  void check_for_tv_shows_by_actor_id() {
    Optional<List<TvShows>> tvShowsByActorId = tmdbTestOnlyClient.getTvShowsById(500);
    if (tvShowsByActorId.isPresent()) {
      TvShows tvShows = tvShowsByActorId.get().get(0);
      assertEquals("The One Show", tvShows.getOriginalName());
    }
    TmdbMockServer.verifyTvCreditsByActorId(500);
  }

  @Test
  void check_for_combined_credits_by_actor_id() {
    Optional<List<CombinedCredits>> combinedCreditsByActorId = tmdbTestOnlyClient.getCombinedCredits(500);
    if (combinedCreditsByActorId.isPresent()) {
      List<CombinedCredits> combinedCreditsList = combinedCreditsByActorId.get();
      Assertions.assertFalse(false);
      assertEquals(25, combinedCreditsList.get(0).getVoteCount());
    }
    TmdbMockServer.verifyCombinedCreditsByActorId(500);
  }

  private void assertActor(Artist artist) {
    assertThat(artist.getName()).isEqualTo("Tom Cruise");
    assertThat(artist.getGender()).isEqualTo(2);
    assertThat(artist.getAge()).isZero();
  }
}
