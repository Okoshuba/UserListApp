package com.example.testtask.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.testtask.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun startLoadListUsers(view: View){
        val intent = Intent(this, RecyclerActivity::class.java)
        startActivity(intent)
    }

}
