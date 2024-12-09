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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.s1114707.ui.theme.S1114707Theme
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import android.app.Activity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 設定螢幕方向為直向
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // 設定 UI 組件，這裡調用 setContent
        setContent {
            S1114707Theme {
                MyApp() // 在 setContent 中調用 @Composable 函數
            }
        }
    }
}

@Composable
fun MyApp() {
    Box(
        modifier = Modifier
            .background(Color(0xff95fe95)) // 設定背景顏色
            .fillMaxSize() // 使 Box 充滿螢幕
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize() // 讓 Column 佔據整個螢幕
                .wrapContentSize(Alignment.Center), // 垂直排列並且置中對齊
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "2024期末上機考(資管3B許皓翔)",
                style = androidx.compose.ui.text.TextStyle(fontSize = 24.sp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 顯示圖片，根據班級選擇對應圖片
            val imageResource = painterResource(id = R.drawable.class_b) // 更改為 class_a 或 class_b 取決於班級
            Image(
                painter = imageResource,
                contentDescription = "Class Image",
                modifier = Modifier.size(600.dp) // 圖片大小
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "遊戲持續時間 0 秒",
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
                    // 使用 LocalContext.current 並將其轉換為 Activity
                    val context = LocalContext
                    if (context is Activity) {
                        // 結束應用程式
                        Toast.makeText(context, "應用程式將關閉", Toast.LENGTH_SHORT).show()
                        context.finish()  // 正確調用 finish() 來關閉 Activity
                    }
                    // 如果 finish() 不生效，可以強制結束應用
                    System.exit(0)  // 強制退出應用
                }
            ) {
                Text(text = "結束App")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    S1114707Theme {
        MyApp() // 在預覽中調用 @Composable 函數
    }
}
