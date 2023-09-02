package infrastructure.driven_adapter.api

import infrastructure.helpers.ApiHelper
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody

//com.squareup.okhttp3:okhttp:4.11.0
class ConnectionFactory {
    companion object {
         fun getJsonTrendingMoviesFromConnectionApiAuthAccessToken(): String {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://api.themoviedb.org/3/trending/all/day?language=es-ES")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer ${ApiHelper.ACCESS_TOKEN.value}")
                .build()

            val response = client.newCall(request).execute()
            return response.body!!.string()
        }

        fun getJsonIdMovieFromConnectionApiAuthAccessToken(id: Int): String {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://api.themoviedb.org/3/movie/${id}?language=es-ES")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer ${ApiHelper.ACCESS_TOKEN.value}")
                .build()

            val response = client.newCall(request).execute()
            return response.body!!.string()
        }
    }
}