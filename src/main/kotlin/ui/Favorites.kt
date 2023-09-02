package ui

import domain.Movie
import domain.UseCaseMovie
import infrastructure.driven_adapter.db.DaoMovie
import ui.components.ItemFavoriteMovie
import ui.components.ItemMovie
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.LayoutManager
import javax.swing.*

class Favorites(var mainMenu: MainMenu): JFrame() {

    lateinit var resultsLayout: JPanel
    lateinit var mainScroll: JScrollPane
    lateinit var panelResults: JPanel
    lateinit var listFavoritesMovies: List<Movie>

    private fun config() {
        title = "Favoritos"
        setSize(600,600)
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE
        val mainLayout: LayoutManager = BorderLayout(10,10)
        getRootPane().contentPane = JPanel(mainLayout)
        isResizable = false

        listFavoritesMovies = UseCaseMovie(DaoMovie()).getListMovies()

        resultsLayout = JPanel(BorderLayout(20,20))
        panelResults = JPanel(GridLayout(1,2, 20, 20))
        resultsLayout.add(panelResults, BorderLayout.NORTH)
        mainScroll = JScrollPane(resultsLayout, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)

        contentPane.add(configControls(), BorderLayout.NORTH)
        contentPane.add(mainScroll, BorderLayout.CENTER)
    }

    private fun configControls(): JPanel {
        val panelResult = JPanel(FlowLayout(FlowLayout.CENTER, 20, 20))
        val btnHome = JButton("Regresar")
        btnHome.addActionListener {
            isVisible = false
            mainMenu.isVisible = true
        }
        panelResult.add(btnHome)
        return panelResult
    }

    fun showView() {
        config()
        loadMovies()
        isVisible = true
    }

    private fun addItem(movie: Movie) {
        Thread() {
            SwingUtilities.invokeLater() {
                if (panelResults.componentCount % 2 == 0) {
                    val rows = (panelResults.layout as GridLayout).rows
                    (panelResults.layout as GridLayout).rows = rows + 1
                }
                panelResults.add(
                    ItemFavoriteMovie(movie).config())
                panelResults.revalidate()
                panelResults.repaint()
            }
        }.start()
    }

    private fun loadMovies() {
        listFavoritesMovies.forEach {movie ->
            addItem(movie)
        }
    }
}