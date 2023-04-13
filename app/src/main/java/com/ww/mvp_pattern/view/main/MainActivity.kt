package com.ww.mvp_pattern.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.ww.mvp_pattern.R
import com.ww.mvp_pattern.adapter.ImageAdapter
import com.ww.mvp_pattern.data.source.image.SampleImageRepository
import com.ww.mvp_pattern.view.main.presenter.MainContract
import com.ww.mvp_pattern.view.main.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View {

    private val recyclerView by lazy {
        findViewById<RecyclerView>(R.id.recycler_view)
    }

    private val imageAdapter: ImageAdapter by lazy {
        ImageAdapter()
    }

    private val presenter: MainPresenter by lazy {
        MainPresenter(
            view = this@MainActivity,
            imageData = SampleImageRepository,
            adapterModel = imageAdapter,
            adapterView = imageAdapter
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView.adapter = imageAdapter

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(
                view,
                "Replace with your own action",
                Snackbar.LENGTH_LONG
            ).setAction("Action", null).show()
        }

        presenter.loadItems(this, false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reload) {
            presenter.loadItems(this, true)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showToast(title: String) {
        Toast.makeText(this, "OnClick Item $title", Toast.LENGTH_SHORT).show()
    }
}