package com.example.githubclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.githubclient.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private var vb: ActivityMainBinding? = null
    var presenter = MainPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        initPresenter()
        initClicks()
    }

 private fun initClicks() = with(vb){
     this!!.btnCounter1.setOnClickListener {
        presenter.onFirstBtnClicked()
     }
     this!!.btnCounter2.setOnClickListener {
         presenter.onSecondBtnClicked()
     }
     this!!.btnCounter3.setOnClickListener {
         presenter.onThirdBtnClicked()
     }
 }

    override fun setDigitOne(counter: String) {
        vb?.btnCounter1?.text = counter
    }

    override fun setDigitTwo(counter: String) {
        vb?.btnCounter2?.text = counter
    }

    override fun setDigitThree(counter: String) {
        vb?.btnCounter3?.text = counter
    }

    private fun initPresenter() {
        presenter = MainPresenter(this)
    }
}

