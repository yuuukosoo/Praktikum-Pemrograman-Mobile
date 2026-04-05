package com.example.modul01_xml

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollbutton : Button = findViewById(R.id.rollButton)
        val diceImage1 : ImageView = findViewById(R.id.diceImage)
        val diceImage2 : ImageView = findViewById(R.id.diceImage2)

        rollbutton.setOnClickListener {
            val number1 = (1..6).random()
            val number2 = (1..6).random()

            diceImage1.setImageResource(getImageDice(number1))
            diceImage2.setImageResource(getImageDice(number2))

            if (number1 == number2){
                Toast.makeText(this, "Selamat, anda dapat dadu double!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Anda belum beruntung!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getImageDice(number : Int): Int {
        return when(number){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6

        }
    }
}