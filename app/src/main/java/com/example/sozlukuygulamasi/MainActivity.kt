package com.example.sozlukuygulamasi

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hazirveritabanikullanimi.DatabaseCopyHelper
import com.example.sozlukuygulamasi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() ,SearchView.OnQueryTextListener{
    private lateinit var binding:ActivityMainBinding
    private  lateinit var kelimeListesi:ArrayList<Kelimeler>
    private lateinit var adapter: KelimelerAdapter
    private lateinit var vt:VeriTabaniYardimcisi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        veriTabaniKopyala()

        binding.toolbar.setTitle("Sözlük Uygulaması")
        setSupportActionBar(binding.toolbar)
        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = LinearLayoutManager(this@MainActivity)

        vt = VeriTabaniYardimcisi(this@MainActivity)
        kelimeListesi = Kelimelerdao().tumKelimeler(vt)



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
        if (query != null) {
            arama(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            arama(newText)
        }
       return true
    }
    fun veriTabaniKopyala(){
        val copyHelper = DatabaseCopyHelper(this@MainActivity)

        try {
            copyHelper.createDataBase()
            copyHelper.openDataBase()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
    fun arama(arananKelime:String){
        kelimeListesi = Kelimelerdao().kelimeAra(vt,arananKelime)
        adapter = KelimelerAdapter(this@MainActivity,kelimeListesi)
        binding.rv.adapter = adapter
    }
}