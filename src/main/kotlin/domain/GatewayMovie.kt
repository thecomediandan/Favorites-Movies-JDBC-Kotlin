package domain

interface GatewayMovie {
    fun getMovie(id: Int): Movie
    fun getListMovies(): List<Movie>
    fun deleteMovie(id: Int)
    fun addMovie(movie: Movie)
    fun updateRateMovie(id: Int, newRate: Double)
}