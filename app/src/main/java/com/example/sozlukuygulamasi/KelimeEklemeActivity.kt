package com.example.sozlukuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.sozlukuygulamasi.databinding.ActivityKelimeEklemeBinding

class KelimeEklemeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKelimeEklemeBinding
    private lateinit var vt:VeriTabaniYardimcisi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKelimeEklemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vt = VeriTabaniYardimcisi(this@KelimeEklemeActivity)

        binding.buttonEkle.setOnClickListener {
            val ingilizce = binding.editTextIngilizce.text.toString()
            val turkce = binding.editTextTurkce.text.toString()

            if(!TextUtils.isEmpty(ingilizce) && !TextUtils.isEmpty(turkce)){
                Kelimelerdao().kelimeEkle(vt,ingilizce,turkce)
                startActivity(Intent(this@KelimeEklemeActivity,MainActivity::class.java))
                finish()
            }else{
                Toast.makeText(this,"Girdiğiniz verileri kontrol edin",Toast.LENGTH_LONG).show()
            }
        }

    }
}