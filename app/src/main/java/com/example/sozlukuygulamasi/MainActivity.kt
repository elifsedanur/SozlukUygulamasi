package com.example.sozlukuygulamasi

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sozlukuygulamasi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() ,SearchView.OnQueryTextListener{
    private lateinit var binding:ActivityMainBinding
    private  lateinit var kelimeListesi:ArrayList<Kelimeler>
    private lateinit var adapter: KelimelerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setTitle("Sözlük Uygulaması")
        setSupportActionBar(binding.toolbar)
        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = LinearLayoutManager(this@MainActivity)

        kelimeListesi = ArrayList()

        var k1 = Kelimeler(1,"Dog","Köpek")
        var k2 = Kelimeler(2,"Fish ","Balık")
        var k3 = Kelimeler(3,"Table","Masa")

        kelimeListesi.add(k1)
        kelimeListesi.add(k2)
        kelimeListesi.add(k3)

        adapter = KelimelerAdapter(this@MainActivity,kelimeListesi)
        binding.rv.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        val item = menu?.findItem(R.id.action_ara)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
       return true
    }
}