package ui.components

import domain.Movie
import domain.UseCaseMovie
import infrastructure.driven_adapter.db.DaoMovie
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.FlowLayout
import java.net.URL
import java.net.URLConnection
import javax.imageio.ImageIO
import javax.swing.BorderFactory
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.SwingConstants
import javax.swing.text.Utilities

class ItemMovie(var movie: Movie): JPanel() {

    fun config(): JPanel {
        layout = BorderLayout(10, 10)
        preferredSize = Dimension(240, 390)
        val jlTitle = JLabel(movie.title?: movie.name!!)
        jlTitle.alignmentX = CENTER_ALIGNMENT
        val jlImg = JLabel()
        Thread() {
            jlImg.icon = ImageIcon(URL("https://image.tmdb.org/t/p/w220_and_h330_face/${movie.poster_path}"))
        }.start()
        val jlRate = JLabel(Math.round(movie.vote_average).toString())
        jlRate.border = BorderFactory.createEmptyBorder(0,0,0,12)

        val jbFavorites = JButton("+ Add to Favorites")
        jbFavorites.addActionListener {
            UseCaseMovie(DaoMovie()).addMovie(movie)
        }
        val titlePanel = JPanel(FlowLayout(FlowLayout.CENTER))
        val imgPanel = JPanel(FlowLayout(FlowLayout.CENTER))
        imgPanel.add(jlImg)
        titlePanel.add(jlTitle)
        add(titlePanel, BorderLayout.NORTH)
        add(imgPanel, BorderLayout.CENTER)
        val info = JPanel(FlowLayout(FlowLayout.CENTER))
        info.add(jlRate)
        info.add(jbFavorites)
        add(info, BorderLayout.SOUTH)
        border = BorderFactory.createLineBorder(Color.BLACK, 2)

        val result = JPanel(FlowLayout(FlowLayout.CENTER))
        result.toolTipText = movie.overview
        result.add(this)
        return result
    }

}