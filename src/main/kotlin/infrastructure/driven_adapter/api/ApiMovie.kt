package infrastructure.driven_adapter.api

import domain.GatewayMovie
import domain.Movie

class ApiMovie: GatewayMovie {
    override fun getMovie(id: Int): Movie {
        val result = Movie.fromJson(ConnectionFactory.getJsonIdMovieFromConnectionApiAuthAccessToken(id))
        println(result)
        return result
    }

    override fun getListMovies(): List<Movie> {
        val response = ConnectionFactory.getJsonTrendingMoviesFromConnectionApiAuthAccessToken()
        val refactorResponse = response.substring(
            response.indexOf('['),
            response.lastIndexOf(']') + 1
        )
        return Movie.listFromJson(refactorResponse)
    }

    override fun deleteMovie(id: Int) {
        TODO("Not yet implemented")
    }

    override fun addMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    override fun updateRateMovie(id: Int, newRate: Double) {
        TODO("Not yet implemented")
    }
}