package co.incubyte;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TmdbServiceShould {

  private TmdbService tmdbService;

  private TmdbClient tmdbClient;

  @BeforeEach
  void setUp() {
    tmdbClient = mock(TmdbClient.class);
    tmdbService = new TmdbService(tmdbClient);
  }

  @Test
  void call_client_to_find_from_actor_id() {
    when(tmdbClient.getArtistById(500, null)).thenReturn(Optional.of(new Artist()));

    Optional<Artist> artistByIdList = tmdbService.getArtistById(500);

    verify(tmdbClient).getArtistById(500, null);
    assertFalse(artistByIdList.isEmpty());
  }

  @Test
  void call_client_to_find_from_actor_name() {
    when(tmdbClient.getActorByName("Tom Holland", null)).thenReturn(Optional.of(getActorDto()));

    Optional<List<Actor>> actorByNameLsit  =
        tmdbService.getActorByName("Tom Holland");

    verify(tmdbClient).getActorByName("Tom Holland", null);

    assertFalse(actorByNameLsit .isEmpty());
  }

  private ActorDto getActorDto() {
    Actor actor = new Actor(1136406, "Tom Holland", "/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg");
    Actor actor1 = new Actor(299534, "Tom Holland", "/22eqkAJx8Uyaww4dB7BiriboLAp.jpg");

    List<Actor> actorList = new ArrayList<>();
    actorList.add(actor);
    actorList.add(actor1);

    ActorDto actorDto = new ActorDto();
    actorDto.setResults(actorList);
    return actorDto;
  }

  @Test
  void call_client_to_get_movies_from_actor_id() {
    when(tmdbClient.getMoviesByActorId(500, null)).thenReturn(Optional.of(getMoviesDto()));

    Optional<List<Movies>> moviesByIdList =
        tmdbService.getMoviesByActorId(500);

    verify(tmdbClient).getMoviesByActorId(500, null);

    assertFalse(moviesByIdList.isEmpty());
  }

  private MoviesWrapperDto getMoviesDto() {
    MoviesWrapperDto moviesWrapperDto = new MoviesWrapperDto();
    List<Movies> moviesList = new ArrayList<>();
    moviesList.add(new Movies("Legends of the Fall", LocalDate.of(1994, 12, 16)));
    moviesList.add(new Movies("Kalifornia", LocalDate.of(1993, 9, 1)));
    moviesWrapperDto.setCast(moviesList);
    return moviesWrapperDto;
  }

  @Test
  void call_client_to_get_tv_shows_grom_actor_id() {
    when(tmdbClient.getTvShowsByActorId(500, null)).thenReturn(Optional.of(getTvShowsDto()));

    Optional<List<TvShows>> tvShowsByIdList = tmdbService.getTvShowsByActorId(500);

    verify(tmdbClient).getTvShowsByActorId(500, null);

    assertFalse(tvShowsByIdList.isEmpty());
  }

  private TvShowsWrapperDto getTvShowsDto() {

    TvShowsWrapperDto tvShowsWrapperDto = new TvShowsWrapperDto();
    List<TvShows> tvShows = new ArrayList<>();
    tvShows.add(new TvShows("Growing Pains", LocalDate.of(1985, 9, 24)));
    tvShows.add(new TvShows("Jackass", LocalDate.of(2000, 4, 12)));
    tvShowsWrapperDto.setCast(tvShows);

    return tvShowsWrapperDto;
  }

  @Test
  void call_client_to_get_combined_credits_from_actor_id() {
    when(tmdbClient.getCombinedCreditsByActorId(500, null)).thenReturn(
        Optional.of(getCombinedCreditsDto()));
    Optional<List<CombinedCredits>> combinedCreditsList = tmdbService.getCombinedCreditsByActorID(500);

    verify(tmdbClient).getCombinedCreditsByActorId(500, null);

    assertFalse(combinedCreditsList.isEmpty());

  }

  private CombinedCreditsWrapperDto getCombinedCreditsDto() {
    CombinedCreditsWrapperDto combinedCreditsWrapperDto = new CombinedCreditsWrapperDto();

    List<CombinedCredits> combinedCreditsList = new ArrayList<>();
    combinedCreditsList.add(
        new CombinedCredits("Growing Pains", "/eKyeUFwjc0LhPSp129IHpXniJVR.jpg", "en", 25));
    combinedCreditsList.add(new
        CombinedCredits("Jackass", "/mz9PZo93dnIYHp1udcYsnBSLYTS.jpg", "en", 35));
    combinedCreditsWrapperDto.setCast(combinedCreditsList);
    return combinedCreditsWrapperDto;
  }
}