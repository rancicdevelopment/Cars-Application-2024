package com.rancic.development.demo.app.ui.screen.nofilesscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rancic.development.demo.app.R

@Composable
fun NoFilesScreen(){
    Box(contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()){
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                painter = painterResource(id = R.drawable.no_files_bg),
                contentDescription = null)
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.no_items_here),
                color = Color.LightGray,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold)
        }
    }
}


@Preview
@Composable
fun NoFilesPreview(){
    NoFilesScreen()
}