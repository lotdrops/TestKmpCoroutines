package com.example.kmmbase.androidApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.kmmbase.androidApp.theme.KMMBaseTheme
import com.example.kmmbase.shared.Greeting

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO add state saving
        setContent {
            KMMBaseTheme {
                AppLayout()
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun AppLayout() {
    val navController = rememberNavController()
    Scaffold { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            RootNavigation(navController)
        }
    }
}

fun greet(): String {
    return Greeting().greeting()
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun AppLayoutPreview() {
    AppLayout()
}
