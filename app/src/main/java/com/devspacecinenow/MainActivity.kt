package com.devspacecinenow

import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.devspacecinenow.ui.theme.CineNowTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CineNowTheme {
                var upComingMovies by remember { mutableStateOf <List<MovieDto>>(emptyList()) }
                var nowPlayingMovies by remember { mutableStateOf <List<MovieDto>>(emptyList()) }
                var topRatedMovies by remember { mutableStateOf <List<MovieDto>>(emptyList())}
                var popularMovies by remember { mutableStateOf <List<MovieDto>>(emptyList()) }

                val apiService = RetrofitClient.retrofitInstance.create(ApiService::class.java)

                val callNowPlaying = apiService.getNowPlayingMovies()
                callNowPlaying.enqueue(object: Callback<MovieResponse>{
                    override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                    ) {
                        if(response.isSuccessful){
                            val movies = response.body()?.results
                            if (movies != null){
                                nowPlayingMovies = movies
                            }
                        }else {
                            Log.d("MainActivity", "Request Error :: ${response.errorBody()}")
                        }
                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        Log.d("MainActivity", "Network Error :: ${t.message}")
                    }

                })

                val callUpComingMovies = apiService.getUpComingMovies()
                callUpComingMovies.enqueue(object : Callback<MovieResponse>{
                    override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                    ) {
                        if(response.isSuccessful){
                            val movies = response.body()?.results
                            if (movies != null){
                                upComingMovies = movies
                            }
                        }else {
                            Log.d("MainActivity", "Request Error :: ${response.errorBody()}")
                        }
                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        Log.d("MainActivity", "Network Error :: ${t.message}")

                    }

                })

                val callTopRatedMovies = apiService.getTopRatedMovies()
                callTopRatedMovies.enqueue(object : Callback<MovieResponse>{
                    override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                    ) {
                        if(response.isSuccessful){
                            val movies = response.body()?.results
                            if (movies != null){
                                topRatedMovies = movies
                            }
                        }else {
                            Log.d("MainActivity", "Request Error :: ${response.errorBody()}")
                        }
                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        Log.d("MainActivity", "Network Error :: ${t.message}")

                    }

                })

                val callPopularMovies = apiService.getPopularMovies()
                callPopularMovies.enqueue(object : Callback<MovieResponse>{
                    override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                    ) {
                        if(response.isSuccessful){
                            val movies = response.body()?.results
                            if (movies != null){
                                popularMovies = movies
                            }
                        }else {
                            Log.d("MainActivity", "Request Error :: ${response.errorBody()}")
                        }
                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        Log.d("MainActivity", "Network Error :: ${t.message}")

                    }

                })

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {


                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            fontSize = 40.sp,
                            fontWeight = FontWeight.SemiBold,
                            text = "CineBox",
                        )

                        MovieSession(
                            label = "Upcoming00001",
                            movieList = upComingMovies,
                            onClick = { movieClick ->

                            }
                        )

                        MovieSession(
                            label = "Now Playing",
                            movieList = nowPlayingMovies,
                            onClick = { movieClick ->

                            }
                        )


                        MovieSession(
                            label = "Top Rated",
                            movieList = topRatedMovies,
                            onClick = { movieClick ->

                            }
                        )

                        MovieSession(
                            label = "Popular",
                            movieList = popularMovies,
                            onClick = { movieClick ->

                            }
                        )

                    }
                }
            }
        }
    }
}

@Composable

fun MovieList(
    movieList: List<MovieDto>,
    onClick: (MovieDto) -> Unit,
) {

    LazyRow {
        items(movieList){
            MovieItem(
                movieDto = it,
                onClick = onClick
            )
        }
    }

}

@Composable
fun MovieSession (
    label: String,
    movieList: List<MovieDto>,
    onClick: (MovieDto) -> Unit
){

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {
        Text(
            fontSize = 24.sp,
            text = label,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.size(8.dp))

        MovieList(movieList = movieList, onClick = onClick)

        }
    }



@Composable
fun MovieItem (
    movieDto: MovieDto,
    onClick: (MovieDto) -> Unit
) {
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Min)
            .clickable {
                onClick.invoke(movieDto)
            }
    ){
        AsyncImage(
            modifier = Modifier
                .padding(end = 4.dp)
                .width(120.dp)
                .height(150.dp),
            contentScale = ContentScale.Crop,
            model = movieDto.posterFullPath,
            contentDescription ="$.{movie.Dto.title} Poster image"
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold,
            text = movieDto.title
        )
        Text(
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            text = movieDto.overview
        )
    }
}

