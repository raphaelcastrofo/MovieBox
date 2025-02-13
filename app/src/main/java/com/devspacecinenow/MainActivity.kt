package com.devspacecinenow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.devspacecinenow.detail.presentation.MovieDetailViewModel
import com.devspacecinenow.list.presentation.MovieListViewModel
import com.devspacecinenow.ui.theme.CineNowTheme

class MainActivity : ComponentActivity() {

    private val listViewModel by viewModels<MovieListViewModel> { MovieListViewModel.Factory }
    private val detailViewModel by viewModels<MovieDetailViewModel> { MovieDetailViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CineNowTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.black)
                ) {
                    CineNowApp(
                        listViewModel = listViewModel,
                        detailViewModel = detailViewModel

                    )
                }
            }
        }
    }
}


