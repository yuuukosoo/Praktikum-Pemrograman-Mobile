package com.example.modul01_compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.modul01_compose.ui.theme.Modul01_ComposeTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Modul01_ComposeTheme {
                DiceRollerApp()
            }
        }
    }
}

@Composable
fun DiceRollerApp(){
    val context = LocalContext.current
    var result by remember { mutableIntStateOf(1) }
    var result2 by remember { mutableIntStateOf(1) }

    fun getDiceResource(result: Int): Int{
        return when(result) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
    Row{
        Image(
            painter = painterResource(id = getDiceResource(result)),
            contentDescription = "$result",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))

        Image(
            painter = painterResource(id = getDiceResource(result2)),
            contentDescription = "$result2",
            modifier = Modifier.size(150.dp)
        )
    }

        Spacer(modifier = Modifier.height(32.dp))
    
        Button(onClick = {
            result = (1..6).random()
            result2 = (1..6).random()

            val text  = if (result == result2) "Selamat, anda dapat dadu double!"
            else "Anda belum beruntung!"

            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

                } )
        {
            Text(text = "Roll the Dice")
            }

    }
}

@Preview(showBackground = true)
@Composable
fun DiceRollerPreview() {
    Modul01_ComposeTheme {
        DiceRollerApp()
    }
}