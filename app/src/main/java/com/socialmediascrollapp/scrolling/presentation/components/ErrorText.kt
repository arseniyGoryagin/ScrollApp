package com.socialmediascrollapp.scrolling.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.LoadState


@Composable
fun ErrorText(error : String){
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Error ${error}",
            modifier = Modifier.align(alignment = Alignment.Center),
            textAlign = TextAlign.Center,
            color = Color.LightGray
        )
    }
}