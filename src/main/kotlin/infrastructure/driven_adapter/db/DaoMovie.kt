package infrastructure.driven_adapter.db

import domain.GatewayMovie
import domain.Movie
import java.sql.*

class DaoMovie: GatewayMovie {

    override fun getMovie(id: Int): Movie {
        try {
            ConnectionFactory().getConnection().use {connection ->
                val statement: Statement = connection.createStatement()
                statement.execute("SELECT * FROM movie WHERE id = ${id};")

                val resultSet = statement.resultSet
                resultSet.next()
                return Movie(id = resultSet.getInt("id"),
                    title = resultSet.getString("title"),
                    name = resultSet.getString("name"),
                    poster_path = resultSet.getString("url"),
                    vote_average = resultSet.getDouble("rate"),
                    overview = resultSet.getString("overview"))
            }
        }catch (e: SQLException) {
            println("Error al consultar a la base de datos")
            return Movie(id = 0,
                title = "",
                name = "",
                poster_path = "",
                vote_average = 0.0,
                overview = "")
        }
    }

    override fun getListMovies(): List<Movie> {
        val listFavoritesMovies = mutableListOf<Movie>()
        try {
            ConnectionFactory().getConnection().use {connection ->
                val statement: Statement = connection.createStatement()
                statement.execute("SELECT * FROM movie;")

                val resultSet = statement.resultSet
                while (resultSet.next()) {
                    println(resultSet.getString("title"))
                    listFavoritesMovies.add(
                        Movie(id = resultSet.getInt("id"),
                            title = resultSet.getString("title"),
                            name = resultSet.getString("name"),
                            poster_path = resultSet.getString("url"),
                            vote_average = resultSet.getDouble("rate"),
                            overview = resultSet.getString("overview"))
                    )
                }
                return listFavoritesMovies
            }

        }catch (e: SQLException) {
            println("Error al consultar a la base de datos")
            return listFavoritesMovies
        }
    }

    override fun deleteMovie(id: Int) {
        try {
            ConnectionFactory().getConnection().use {connection ->
                val statement: Statement = connection.createStatement()
                statement.execute("DELETE FROM movie WHERE id = ${id};")
                println("Se elimino la pelicula correctamente")
            }
        }catch (e: SQLException) {
            println("Error al intentar eliminar el dato")
            throw RuntimeException(e)
        }
    }

    override fun addMovie(movie: Movie) {
        try {
            ConnectionFactory().getConnection().use { connection ->
                val statement: PreparedStatement = connection.prepareStatement(
                    "INSERT INTO movie(id,title,name,url,rate,overview) VALUES(?,?,?,?,?,?);",
                    Statement.RETURN_GENERATED_KEYS
                )
                statement.setInt(1, movie.id)
                statement.setString(2, movie.title)
                statement.setString(3, movie.name)
                statement.setString(4, movie.poster_path)
                statement.setDouble(5, movie.vote_average)
                statement.setString(6, movie.overview)
                statement.execute()
                println("Peliculas insertada: ${movie.title}")
            }
        }catch (e: SQLException) {
            println("Error al intentar adicionar el dato")
            throw RuntimeException(e)
        }
    }

    override fun updateRateMovie(id: Int, newRate: Double) {
        try {
            ConnectionFactory().getConnection().use {connection ->
                val statement: Statement = connection.createStatement()
                statement.execute("UPDATE movie SET rate = '$newRate' WHERE id = '$id';")
                println("Se actualizo la pelicula correctamente")
            }
        }catch (e: SQLException) {
            println("Error al intentar actualizar el dato")
            throw RuntimeException(e)
        }
    }
}