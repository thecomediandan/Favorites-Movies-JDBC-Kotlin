package domain

class UseCaseMovie(private val gatewayMovie: GatewayMovie) {
    fun getMovie(id: Int): Movie {
        return gatewayMovie.getMovie(id)
    }
    fun getListMovies(): List<Movie> {
        return gatewayMovie.getListMovies()
    }

    // Estas siguietes funciones sirven como un Controller del DaoMovie
    fun deleteMovie(id: Int) {
        gatewayMovie.deleteMovie(id)
    }
    fun addMovie(movie: Movie) {
        gatewayMovie.addMovie(movie)
    }
    fun updateRateMovie(id: Int, newRate: Double) {
        gatewayMovie.updateRateMovie(id, newRate)
    }
}