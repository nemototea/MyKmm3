package com.example.mykmm3.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mykmm3.Greeting
import android.widget.TextView
import com.example.mykmm3.data.DatabaseDriverFactory
import com.example.mykmm3.domain.repository.SpaceXRepository
import com.example.mykmm3.presentation.main.MainProcessor
import com.example.mykmm3.presentation.main.MainViewModel

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel(
        MainProcessor(
            SpaceXRepository(
                DatabaseDriverFactory(this)
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()
    }
}
