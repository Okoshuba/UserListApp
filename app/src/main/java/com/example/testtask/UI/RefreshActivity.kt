package com.example.testtask.UI

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.testtask.R

class RefreshActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.refresh_activity)
    }

    fun RefreshLoadListUsers(view: View){
        val intent = Intent(this, RecyclerActivity::class.java)
        startActivity(intent)
    }

}