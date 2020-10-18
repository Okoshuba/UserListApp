package com.example.testtask.UI

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtask.Adapter.AdapterUsers
import com.example.testtask.Listener.Listener
import com.example.testtask.Network.RetrofitBuilder
import com.example.testtask.Repository.Users
import kotlinx.android.synthetic.main.recycler_view_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import androidx.core.util.Pair
import kotlinx.android.synthetic.main.item_recycler_view.*
import androidx.core.app.ActivityOptionsCompat
import android.os.Build
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import org.w3c.dom.Text

class RecyclerActivity: AppCompatActivity(), Listener {

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view_layout)

        fetchData()
    }

    private fun fetchData() {
        RetrofitBuilder.newApi.getUsers().enqueue(object: Callback<List<Users>>{
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {

                showData(response.body()!!)
                progressBar.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE

                d("Check", "onResponse: ${response.body()!![0].name}")
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                d("Check", "onFailure: ${t.message}")
                toRefreshActivity()
            }
        })
    }

    //Метод, который формирует список пользователей в recyclerView
    private fun showData(users: List<Users>) {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AdapterUsers(this, users, this)
    }

    //Обработчик нажатия на элемент коллекции
    @SuppressLint("ObsoleteSdkInt")
    override fun onItemClickListener(position: Int, nameRV: TextView) {

        val intent = Intent(this, UsersPostsActivity::class.java)
        startActivity(intent)

        //Анимация
        val p1 = Pair.create(nameRV as View, "authorNameTN")
        val p2 = Pair.create(emailUser as View, "emailTN")
        val p3 = Pair.create(phoneUser as View, "phoneTN")
        val p4 = Pair.create(website as View, "websiteTN")

        val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1, p2, p3, p4)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent,optionsCompat.toBundle())
        }
        else
            startActivity(intent)
    }

    //Переход на активити перезагрузки данных из-за ошибки
    fun toRefreshActivity(){
        val intent = Intent(this, RefreshActivity::class.java)
        startActivity(intent)
    }
}