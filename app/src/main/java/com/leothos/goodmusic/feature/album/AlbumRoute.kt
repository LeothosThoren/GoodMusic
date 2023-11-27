package com.leothos.goodmusic.feature.album

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leothos.goodmusic.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumRoute(
    viewModel: AlbumViewModel = hiltViewModel(),
    onAlbumClick: (Int) -> Unit
) {
    viewModel.getAlbumsFromSongs()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val gridState = rememberLazyGridState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    stringResource(R.string.albums),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.headlineSmall
                )
            }, scrollBehavior = scrollBehavior)
        }, content = {

            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (uiState.value.isLoading) {
                    CircularProgressIndicator()
                } else {
                    AlbumListScreen(
                        innerPaddingValues = it,
                        albums = uiState.value.albums,
                        lazyGridState = gridState,
                        onAlbumClick
                    )
                }
            }
        })
}