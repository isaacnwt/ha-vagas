package br.edu.ifsp.scl.ads.prdm.sc3014789.havagas

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3014789.havagas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(activityMainBinding) {
            setContentView(activityMainBinding.root)
            setupListeners()
        }
    }

    private fun setupListeners() = with(activityMainBinding) {
        setupCelularToggle()
    }

    private fun setupCelularToggle() = with(activityMainBinding) {
        adicionarCelularCb.setOnClickListener { view ->
            if ((view as CheckBox).isChecked) {
                celularEt.visibility = View.VISIBLE
            } else {
                celularEt.visibility = View.GONE
                celularEt.setText("")
            }
        }
    }
}