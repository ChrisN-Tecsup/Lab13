package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    CombinedAnimationsScreen()
                }
            }
        }
    }
}

@Composable
fun CombinedAnimationsScreen() {
    var isScaled by remember { mutableStateOf(false) }
    var isVisible by remember { mutableStateOf(true) }
    var isDarkMode by remember { mutableStateOf(false) }

    val size by animateDpAsState(
        targetValue = if (isScaled) 150.dp else 100.dp,
        animationSpec = tween(durationMillis = 500)
    )

    val backgroundColor = if (isScaled) Color.Red else Color.Blue

    val background = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .size(size)
                .background(backgroundColor, shape = CircleShape)
                .clickable { isScaled = !isScaled }
        )

        Spacer(modifier = Modifier.height(20.dp))

        AnimatedVisibility(visible = isVisible) {
            Button(
                onClick = {
                    isVisible = false
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .offset(y = if (isVisible) 0.dp else 100.dp)
                    .animateContentSize(animationSpec = tween(durationMillis = 300))
            ) {
                Text("Desaparecer")
            }
        }

        Button(
            onClick = { isDarkMode = !isDarkMode },
            modifier = Modifier.align(Alignment.TopCenter).padding(16.dp)
        ) {
            Text("Alternar Modo")
        }


        Text(
            text = if (isDarkMode) "Modo Oscuro" else "Modo Claro",
            color = textColor,
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 70.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CombinedAnimationsPreview() {
    MyApplicationTheme {
        CombinedAnimationsScreen()
    }
}


