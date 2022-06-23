package co.incubyte;

import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class TmdbService {

  private final TmdbClient tmdbClient;

  @Value("${tmdb.api-key}")
  private String apiKey;

  public TmdbService(TmdbClient tmdbClient) {
    this.tmdbClient = tmdbClient;
  }

  public Optional<Artist> getArtistById(Integer id) {
    return tmdbClient.getArtistById(id, apiKey);
  }

  public Optional<List<Actor>> getActorByName(String name) {
    Optional<ActorDto> actorByNameList = tmdbClient.getActorByName(name, apiKey);
    if(actorByNameList.isPresent()){
      ActorDto actorDto = actorByNameList.get();
      List<Actor> actorList = actorDto.getResults();
      if (actorList.isEmpty()) {
        return Optional.of(actorList);
      }
      return Optional.of(actorList);
    }
    return Optional.empty();
  }

  public Optional<List<Movies>> getMoviesByActorId(Integer id) {
    Optional<MoviesWrapperDto> moviesList = tmdbClient.getMoviesByActorId(id, apiKey);
    return moviesList.map(MoviesWrapperDto::getCast);
  }

  public Optional<List<TvShows>> getTvShowsByActorId(Integer id) {
    Optional<TvShowsWrapperDto> tvShowsList = tmdbClient.getTvShowsByActorId(id, apiKey);
    return tvShowsList.map(TvShowsWrapperDto::getCast);
  }

  public Optional<List<CombinedCredits>> getCombinedCreditsByActorID(Integer id) {
    Optional<CombinedCreditsWrapperDto> combinedCreditsList = tmdbClient.getCombinedCreditsByActorId(id, apiKey);
    return combinedCreditsList.map(CombinedCreditsWrapperDto::getCast);
  }
}
