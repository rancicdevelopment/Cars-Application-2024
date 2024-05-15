package com.rancic.development.demo.app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rancic.development.demo.app.R

@Composable
fun ClassicCircularProgressBar() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            color = colorResource(id = R.color.purple_500),
            modifier = Modifier.then(Modifier.size(60.dp))
        )
    }
}

@Preview
@Composable
fun ClassicCircularProgressBarPreview() {
    ClassicCircularProgressBar()
}