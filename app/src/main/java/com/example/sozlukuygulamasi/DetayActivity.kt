package com.example.sozlukuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sozlukuygulamasi.databinding.ActivityDetayBinding

class DetayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetayBinding
    private lateinit var vt:VeriTabaniYardimcisi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vt = VeriTabaniYardimcisi(this@DetayActivity)

        val kelime = intent.getSerializableExtra("nesne") as Kelimeler

        binding.textViewIngilizce.text = kelime.ingilizce
        binding.textViewTurkce.text = kelime.turkce

        binding.buttonSil.setOnClickListener {
            Kelimelerdao().kelimeSil(vt,kelime.kelime_id)
            startActivity(Intent(this@DetayActivity,MainActivity::class.java))
            finish()
        }
    }
}