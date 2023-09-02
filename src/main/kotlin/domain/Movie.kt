package domain

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Movie(var id: Int, var title: String, var name: String?, var poster_path: String, var vote_average: Double, var overview: String) {
    companion object {
        fun fromJson(json: String): Movie {
            val gson = Gson()
            return gson.fromJson(json, Movie::class.java)
        }
        fun toJson(movie: Movie): String {
            val gson = Gson()
            return gson.toJson(movie)
        }
        fun listFromJson(json: String): List<Movie> {
            val gson = Gson()
            return gson.fromJson(json, object:TypeToken<List<Movie>>() {}.type)
        }
        fun listToJson(movie: List<Movie>): String {
            val gson = Gson()
            return gson.toJson(movie)
        }
    }
}
