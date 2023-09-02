package infrastructure.driven_adapter.db

import java.sql.Connection
import java.sql.DriverManager

class ConnectionFactory
    internal constructor(
        // Si no existe la db, esta se crea
        private var connection: Connection = DriverManager.getConnection("jdbc:sqlite:FavoritesMovies.db")
    ) {
        fun getConnection(): Connection {
            return connection
        }
}