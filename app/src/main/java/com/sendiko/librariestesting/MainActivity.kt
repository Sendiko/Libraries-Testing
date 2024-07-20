package com.sendiko.librariestesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sendiko.librariestesting.about.AboutScreen
import com.sendiko.librariestesting.contentbox.ContentBoxDemoScreen
import com.sendiko.librariestesting.contentbox.ContentBoxDemoScreenViewModel
import com.sendiko.librariestesting.dashboard.DashboardScreen
import com.sendiko.librariestesting.navigation.AboutScreen
import com.sendiko.librariestesting.navigation.ContentBoxScreen
import com.sendiko.librariestesting.navigation.DashboardScreen
import com.sendiko.librariestesting.navigation.SelectorScreen
import com.sendiko.librariestesting.navigation.VariousTextFieldScreen
import com.sendiko.librariestesting.selector.SelectorDemoScreen
import com.sendiko.librariestesting.selector.SelectorDemoScreenViewModel
import com.sendiko.librariestesting.ui.theme.LibrariesTestingTheme
import com.sendiko.librariestesting.varioustextfields.VariousTextFieldScreen
import com.sendiko.librariestesting.varioustextfields.VariousTextFieldScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LibrariesTestingTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = DashboardScreen
                ) {
                    composable<DashboardScreen> {
                        DashboardScreen(
                            onNavigate = {
                                navController.navigate(it)
                            }
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
                        AboutScreen(
                            onNavigate = {
                                navController.navigateUp()
                            }
                        )
                    }
                }
            }
        }
    }
}