package com.example.to_docompose.navigation.destinations
//
//import androidx.compose.animation.ExperimentalAnimationApi
//import androidx.compose.animation.core.tween
//import androidx.compose.animation.slideOutVertically
//import androidx.navigation.NavGraphBuilder
//import com.example.to_docompose.ui.screens.splash.SplashScreen
//import com.example.to_docompose.util.Constants.SPLASH_SCREEN
//import com.google.accompanist.navigation.animation.composable
//
//@OptIn(ExperimentalAnimationApi::class)
//fun NavGraphBuilder.splashComposable(
//    navigateToListScreen: () -> Unit,
//) {
//    composable(
//        route = SPLASH_SCREEN,
//        exitTransition = {
//            slideOutVertically(
//                targetOffsetY = { fullHeight -> -fullHeight },
//                animationSpec = tween(durationMillis = 300)
//            )
//        }
//    ) {
//        SplashScreen(navigateToListScreen = navigateToListScreen)
//    }
//}