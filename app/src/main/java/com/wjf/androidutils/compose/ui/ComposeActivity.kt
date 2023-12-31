package com.wjf.androidutils.compose.ui


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.wjf.androidutils.compose.nav.MyNavHost
import com.wjf.androidutils.compose.theme.MyAppTheme

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/23 16:31
 *
 */
class ComposeActivity : ComponentActivity(){

    companion object{
        fun newInstance(context: Context){
            val intent = Intent(context,ComposeActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            MyAppTheme{
                Surface(modifier = Modifier.fillMaxSize()) {
                    MyNavHost()
                }
            }
        }
    }
}