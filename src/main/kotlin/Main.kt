
import app.App
import domain.UseCaseMovie
import infrastructure.driven_adapter.db.ConnectionFactory
import infrastructure.driven_adapter.db.DaoMovie
import ui.MainMenu
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement

fun main(args: Array<String>) {
    App().run()
}