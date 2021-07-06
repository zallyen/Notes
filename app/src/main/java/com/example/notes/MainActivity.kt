package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.adapter.NotasAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    var app:NotaApp?=null
    var recyclerNotas:RecyclerView?=null
    var notas:ArrayList<Nota>?=null
    var adapter: NotasAdapter?=null

    var fabAdd: FloatingActionButton?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app = applicationContext as NotaApp

        fabAdd = findViewById(R.id.fabAddNota)
        recyclerNotas = findViewById(R.id.recyclerNotas)

        notas = ArrayList<Nota>()

        lifecycleScope.launch {
            val notasdb = app!!.baseDeDatos.notaDao().getAll()
            notas!!.addAll(notasdb)
            adapter!!.notifyDataSetChanged()
        }



        adapter = NotasAdapter(notas!!,this)

        fabAdd!!.setOnClickListener {
            val i = Intent(this, AddNotaActivity::class.java)
            startActivity(i)
        }

        recyclerNotas!!.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        recyclerNotas!!.setHasFixedSize(true)
        recyclerNotas!!.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val notasDb = app!!.baseDeDatos.notaDao().getAll()
            notas!!.addAll(notasDb)
            adapter!!.notifyDataSetChanged()
        }
    }
}