package com.example.s1114707

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay
import android.app.Activity
import androidx.compose.runtime.mutableStateOf
import com.example.s1114707.ui.theme.S1114707Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 設定螢幕方向為直向
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // 獲取螢幕寬度
        val screenWidth = resources.displayMetrics.widthPixels

        // 設定 UI 組件，這裡調用 setContent
        setContent {
            S1114707Theme {
                MyApp(this, screenWidth) // 傳遞 Activity 和螢幕寬度到 @Composable 函數
            }
        }
    }
}

@Composable
fun MyApp(activity: Activity, screenWidth: Int) { // 接收螢幕寬度作為參數
    // 管理遊戲時間（秒數）
    val gameDuration = remember { mutableStateOf(0) }

    // 管理瑪利亞圖示的X軸位置
    val initialXPosition = remember { mutableStateOf(0f) }

    // 管理遊戲是否結束
    val isGameOver = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .background(Color(0xff95fe95)) // 設定背景顏色
            .fillMaxSize() // 使 Box 充滿螢幕
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize() // 讓 Column 佔據整個螢幕
                .wrapContentSize(Alignment.TopCenter), // 置中對齊
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "2024期末上機考(資管3B許皓翔)",
                style = androidx.compose.ui.text.TextStyle(fontSize = 24.sp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 顯示圖片，根據班級選擇對應圖片
            val imageResource = painterResource(id = R.drawable.class_b) // class_b圖片
            Image(
                painter = imageResource,
                contentDescription = "Class Image",
                modifier = Modifier
                    .size(600.dp) // class_b圖片的大小
                    .align(Alignment.CenterHorizontally) // 保持圖片在螢幕正中間
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 顯示遊戲時間
            Text(
                text = "遊戲持續時間 ${gameDuration.value} 秒",
                style = androidx.compose.ui.text.TextStyle(fontSize = 18.sp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "您的成績 0 分",
                style = androidx.compose.ui.text.TextStyle(fontSize = 18.sp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // 結束應用程式，使用傳遞的 Activity 參數來結束
                    Toast.makeText(activity, "應用程式將關閉", Toast.LENGTH_SHORT).show()
                    activity.finish() // 結束活動
                }
            ) {
                Text(text = "結束App")
            }

            // 顯示遊戲結束訊息
            if (isGameOver.value) {
                Text(
                    text = "遊戲結束！",
                    style = androidx.compose.ui.text.TextStyle(fontSize = 24.sp),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }

        // 當瑪利亞移動時，會在螢幕的左下角開始
        LaunchedEffect(Unit) {
            // 遊戲計時，每秒鐘增加1秒
            while (!isGameOver.value) { // 當遊戲未結束時繼續計時
                delay(1000L) // 每秒延遲一次
                if (!isGameOver.value) { // 如果遊戲還沒結束，才更新時間
                    gameDuration.value += 1
                }
            }
        }

        // 更新瑪利亞的位置，根據遊戲時間，每秒鐘向右移動50像素
        LaunchedEffect(gameDuration.value) {
            if (!isGameOver.value) {
                if (initialXPosition.value < (screenWidth - 200)) { // 使用傳遞過來的螢幕寬度
                    initialXPosition.value += 50f  // 瑪利亞每秒鐘向右移動50像素
                } else {
                    // 遊戲結束，停止計時，顯示結束訊息
                    isGameOver.value = true
                    Toast.makeText(activity, "遊戲結束！", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // 在螢幕左下角顯示瑪利亞
        Image(
            painter = painterResource(id = R.drawable.maria2),  // 瑪利亞圖示
            contentDescription = "Maria Image",
            modifier = Modifier
                .size(200.dp) // 設定大小
                .align(Alignment.BottomStart) // 放置在螢幕的左下角
                .offset(x = initialXPosition.value.dp) // 根據計算的x座標移動
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    S1114707Theme {
        MyApp(Activity(), 1080) // 預覽中傳遞螢幕寬度
    }
}
