package ui

import domain.Movie
import domain.UseCaseMovie
import infrastructure.driven_adapter.api.ApiMovie
import ui.components.ItemMovie
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.LayoutManager
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.SwingUtilities

class MainMenu() : JFrame() {

    lateinit var resultsLayout: JPanel
    lateinit var mainScroll: JScrollPane
    lateinit var panelResults: JPanel
    lateinit var favoritesMenu: Favorites

    private fun config() {
        title = "Aplicacion de Peliculas y Series en Tendencia"
        setSize(600,600)
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE
        val mainLayout: LayoutManager = BorderLayout(10,10)
        getRootPane().contentPane = JPanel(mainLayout)
        isResizable = false

        favoritesMenu = Favorites(this)
        resultsLayout = JPanel(BorderLayout(20,20))
        panelResults = JPanel(GridLayout(1,2, 20, 20))
        resultsLayout.add(panelResults, BorderLayout.NORTH)
        mainScroll = JScrollPane(resultsLayout, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)

        contentPane.add(configControls(), BorderLayout.NORTH)
        contentPane.add(mainScroll, BorderLayout.CENTER)
    }

    private fun configControls(): JPanel {
        val panelResult = JPanel(FlowLayout(FlowLayout.CENTER, 20, 20))
        val btnShowFavorites = JButton("Favoritos")
        btnShowFavorites.addActionListener {
            isVisible = false
            favoritesMenu.showView()
        }
        panelResult.add(btnShowFavorites)
        return panelResult
    }

    fun showView() {
        config()
        loadListMovie()
        isVisible = true
    }

    private fun addItem(movie: Movie) {
        Thread() {
            SwingUtilities.invokeLater() {
                if (panelResults.componentCount % 2 == 0) {
                    val rows = (panelResults.layout as GridLayout).rows
                    (panelResults.layout as GridLayout).rows = rows + 1
                }
                panelResults.add(ItemMovie(movie).config())
                panelResults.revalidate()
                panelResults.repaint()
            }
        }.start()
    }
    private fun loadListMovie() {
        val listMovies = UseCaseMovie(ApiMovie()).getListMovies()
        listMovies.forEach(){movie ->
            addItem(movie)
        }
    }
}

