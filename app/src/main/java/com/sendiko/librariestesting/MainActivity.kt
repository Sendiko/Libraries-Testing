package com.sendiko.librariestesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sendiko.librariestesting.about.AboutScreen
import com.sendiko.librariestesting.about.AboutScreenViewModel
import com.sendiko.librariestesting.contentbox.ContentBoxDemoScreen
import com.sendiko.librariestesting.contentbox.ContentBoxDemoScreenViewModel
import com.sendiko.librariestesting.core.preference.AppPreferenceViewModel
import com.sendiko.librariestesting.dashboard.DashboardScreen
import com.sendiko.librariestesting.dashboard.DashboardScreenViewModel
import com.sendiko.librariestesting.navigation.AboutScreen
import com.sendiko.librariestesting.navigation.ContentBoxScreen
import com.sendiko.librariestesting.navigation.DashboardScreen
import com.sendiko.librariestesting.navigation.SelectorScreen
import com.sendiko.librariestesting.navigation.VariousCardScreen
import com.sendiko.librariestesting.navigation.VariousTextFieldScreen
import com.sendiko.librariestesting.selector.SelectorDemoScreen
import com.sendiko.librariestesting.selector.SelectorDemoScreenViewModel
import com.sendiko.librariestesting.ui.theme.LibrariesTestingTheme
import com.sendiko.librariestesting.variouscards.VariousCardScreen
import com.sendiko.librariestesting.variouscards.VariousCardsViewModel
import com.sendiko.librariestesting.varioustextfields.VariousTextFieldScreen
import com.sendiko.librariestesting.varioustextfields.VariousTextFieldScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(lightScrim = 0, darkScrim = 0),
        )
        setContent {
            val themeViewModel = hiltViewModel<AppPreferenceViewModel>()
            val themeState by themeViewModel.state.collectAsStateWithLifecycle()
            val theme = themeState.isDarkTheme
            val themeBase = themeState.isThemeBasedSystem
            LibrariesTestingTheme(
                darkTheme = if (themeBase) isSystemInDarkTheme() else if (theme) true else false,
            ) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = DashboardScreen
                ) {
                    composable<DashboardScreen> {
                        val viewModel = hiltViewModel<DashboardScreenViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()
                        DashboardScreen(
                            onNavigate = {
                                navController.navigate(it)
                            },
                            state = state,
                            onEvent = viewModel::onEvent
                        )
                    }
                    composable<ContentBoxScreen> {
                        val viewModel = viewModel<ContentBoxDemoScreenViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()
                        ContentBoxDemoScreen(
                            state = state,
                            onEvent = viewModel::onEvent,
                            onNavigate = {
                                navController.navigateUp()
                            }
                        )
                    }
                    composable<SelectorScreen> {
                        val viewModel = viewModel<SelectorDemoScreenViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()
                        SelectorDemoScreen(
                            state = state,
                            onEvent = viewModel::onEvent,
                            onNavigate = {
                                navController.navigateUp()
                            }
                        )
                    }
                    composable<VariousTextFieldScreen> {
                        val viewModel = viewModel<VariousTextFieldScreenViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()
                        VariousTextFieldScreen(
                            onNavigate = { navController.navigateUp() },
                            state = state,
                            onEvent = viewModel::onEvent
                        )
                    }
                    composable<AboutScreen> {
                        val viewModel = hiltViewModel<AboutScreenViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()
                        AboutScreen(
                            onNavigate = {
                                navController.navigateUp()
                            },
                            state = state,
                            onEvent = viewModel::onEvent
                        )
                    }
                    composable<VariousCardScreen> {
                        val viewModel = viewModel<VariousCardsViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()
                        VariousCardScreen(
                            state = state,
                            onEvent = viewModel::onEvent,
                            onNavigateBack = {
                                navController.navigateUp()
                            }
                        )
                    }
                }
            }
        }
    }
}