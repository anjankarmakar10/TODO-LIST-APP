package com.example.android.my_todo_list

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.android.my_todo_list.ui.add_edit_todo.AddEditTodoScreen
import com.example.android.my_todo_list.ui.theme.MYTODOLISTTheme
import com.example.android.my_todo_list.ui.todo_list.TodoListScreen
import com.example.android.my_todo_list.util.Routes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "flash"
            ) {
                composable("flash") {
                    FlashScreen(navController = navController)
                }
                composable(Routes.TODO_LIST) {
                    TodoListScreen(
                        onNavigate = {
                            navController.navigate(it.route)
                        }
                    )
                }
                composable(
                    Routes.ADD_EDIT_TODO + "?todoId={todoId}",
                    arguments = listOf(
                        navArgument("todoId") {
                            type = NavType.IntType
                            defaultValue = -1
                        }
                    )
                ) {
                    AddEditTodoScreen(onPopBackStack = { navController.popBackStack() })
                }
            }
        }
    }
}

@Composable
fun FlashScreen(navController: NavController){
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 500,
                easing =  {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(2500L)
        navController.navigate(Routes.TODO_LIST)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.icon),
            contentDescription = "logo",
            modifier = Modifier.scale(scale.value)
        )
    }

}
