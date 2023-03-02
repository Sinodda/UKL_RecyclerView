package com.example.ukl_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvChar: RecyclerView
    private val list = arrayListOf<Char>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title=("Mode list")

        rvChar = findViewById(R.id.rv_char)
        rvChar.setHasFixedSize(true)

        list.addAll(CharData.listData)
        showRecyclerList()
     }

    private fun showRecyclerList() {
        rvChar.layoutManager = LinearLayoutManager(this)
        val listCharAdapter = ListCharAdapter(list)
        rvChar.adapter = listCharAdapter

        listCharAdapter.setOnItemClickCallback(object : ListCharAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Char) {
                showSelectedChar(data) }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu) : Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                title = "Mode List"
                showRecyclerList()
                supportActionBar?.title = (title)
            }
            R.id.action_grid -> {
                title = "Mode Grid"
                showRecyclerGrid()
                supportActionBar?.title = (title)
            }
        }
    }

    private fun showRecyclerGrid() {
        rvChar.layoutManager = GridLayoutManager(this, 2)
        val gridCharAdapter = GridCharAdapter(list)
        rvChar.adapter = gridCharAdapter

        gridCharAdapter.setOnItemClickCallback(object : GridCharAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Char) {
                showSelectedChar(data) }
        })
    }

    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = title
        }
    }

    private fun showSelectedChar(char: Char) {
        Toast.makeText(this, "Kamu memilih " + char.name, Toast.LENGTH_SHORT).show()
    }



}

