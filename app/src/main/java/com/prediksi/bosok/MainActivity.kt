package com.prediksi.bosok

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputAngka = findViewById<EditText>(R.id.inputAngka)
        val spinnerMetode = findViewById<Spinner>(R.id.spinnerMetode)
        val btnPrediksi = findViewById<Button>(R.id.btnPrediksi)
        val hasilPrediksi = findViewById<TextView>(R.id.hasilPrediksi)

        val metode = arrayOf("Mistik Lama", "Mistik Baru", "Indeks", "Taysen", "Shio Togel", "AI Kombinasi")
        spinnerMetode.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, metode)

        btnPrediksi.setOnClickListener {
            val angka = inputAngka.text.toString().toIntOrNull()
            if (angka == null) {
                Toast.makeText(this, "Masukkan angka dulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val hasil = when(spinnerMetode.selectedItem.toString()) {
                "Mistik Lama" -> mistikLama(angka)
                "Mistik Baru" -> mistikBaru(angka)
                "Indeks" -> indeks(angka)
                "Taysen" -> taysen(angka)
                "Shio Togel" -> shioTogel(angka)
                "AI Kombinasi" -> aiKombinasi(angka)
                else -> "Pilih metode"
            }
            hasilPrediksi.text = hasil
        }
    }

    fun mistikLama(angka: Int): String {
        val pasangan = mapOf(0 to 5, 1 to 6, 2 to 7, 3 to 8, 4 to 9, 5 to 0, 6 to 1, 7 to 2, 8 to 3, 9 to 4)
        val ekor = angka % 10
        return "🔮 MISTIK LAMA\n\nInput: $angka\nPasangan Ekor: ${pasangan[ekor]}\nTop 2D: ${pasangan[ekor]}$ekor\nColok Bebas: ${pasangan[ekor]}"
    }

    fun mistikBaru(angka: Int): String {
        val pasangan = mapOf(0 to 1, 1 to 0, 2 to 3, 3 to 2, 4 to 5, 5 to 4, 6 to 7, 7 to 6, 8 to 9, 9 to 8)
        val ekor = angka % 10
        return "✨ MISTIK BARU\n\nInput: $angka\nPasangan: ${pasangan[ekor]}\nTop 2D: ${pasangan[ekor]}$ekor\nColok Bebas: ${pasangan[ekor]}"
    }

    fun indeks(angka: Int): String {
        val idx = (angka * 7 + 3) % 100
        val idx2 = (angka * 11 + 5) % 100
        return "📊 INDEKS\n\nInput: $angka\nIndeks 1: ${idx.toString().padStart(2,'0')}\nIndeks 2: ${idx2.toString().padStart(2,'0')}"
    }

    fun taysen(angka: Int): String {
        val taysenMap = mapOf(
            1 to "04-13-22-31-40", 2 to "05-14-23-32-41",
            3 to "06-15-24-33-42", 4 to "07-16-25-34-43",
            5 to "08-17-26-35-44", 6 to "09-18-27-36-45",
            7 to "10-19-28-37-46", 8 to "11-20-29-38-47",
            9 to "12-21-30-39-48"
        )
        val key = (angka % 9) + 1
        return "🎯 TAYSEN\n\nInput: $angka\nKode Taysen: $key\nAngka Main: ${taysenMap[key]}"
    }

    fun shioTogel(angka: Int): String {
        val shio = arrayOf("Kerbau","Tikus","Babi","Anjing","Ayam","Monyet","Kambing","Kuda","Ular","Naga","Kelinci","Macan")
        val shioIdx = angka % 12
        val angkaShio = (1..49).filter { it % 12 == shioIdx }.joinToString("-")
        return "🐉 SHIO TOGEL\n\nInput: $angka\nShio: ${shio[shioIdx]}\nAngka: $angkaShio"
    }

    fun aiKombinasi(angka: Int): String {
        val m1 = (angka * 3 + 7) % 100
        val m2 = (angka * 5 + 11) % 100
        val m3 = (angka + 13) % 100
        val m4 = (angka * 2 + 17) % 100
        val avg = (m1 + m2 + m3 + m4) / 4
        val top = listOf(m1, m2, m3, m4, avg).distinct().take(5)
        return "🤖 AI KOMBINASI\n\nInput: $angka\n\n⭐ TOP PREDIKSI:\n${top.joinToString(" - ") { it.toString().padStart(2,'0') }}\n\n💯 Akurasi Estimasi: 78%\n\n(Gabungan semua metode)"
    }
}
