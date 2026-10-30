package com.darien.week6vp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import android.graphics.Color as AndroidColor
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.darien.week6vp.Navigation.AppNavHost
//import com.darien.week6vp.ui.Theme.VpWeek6Theme
import com.darien.week6vp.ui.Week6VpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(AndroidColor.TRANSPARENT, AndroidColor.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(AndroidColor.TRANSPARENT, AndroidColor.TRANSPARENT)
        )
        setContent {
            Week6VpTheme {
                AppNavHost()
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Week6VpTheme(){
        AppNavHost()
    }
}