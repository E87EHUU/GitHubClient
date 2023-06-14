package com.example.githubclient

import android.os.Bundle
import com.example.githubclient.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var vb: ActivityMainBinding
    private val presenter by moxyPresenter { MainPresenter(CountersModel()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        initClicks()
    }

 private fun initClicks() = with(vb){
     this.btnCounter1.setOnClickListener {
        presenter.onFirstBtnClicked()
     }
     this.btnCounter2.setOnClickListener {
         presenter.onSecondBtnClicked()
     }
     this.btnCounter3.setOnClickListener {
         presenter.onThirdBtnClicked()
     }
 }

    override fun setDigitOne(counter: String) {
        vb.btnCounter1.text = counter
    }

    override fun setDigitTwo(counter: String) {
        vb.btnCounter2.text = counter
    }

    override fun setDigitThree(counter: String) {
        vb.btnCounter3.text = counter
    }
}

