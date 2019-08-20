package br.com.redcode.easyreftrofit.sample.view

import android.annotation.SuppressLint
import android.view.View
import br.com.redcode.base.extensions.extract
import br.com.redcode.base.extensions.setSubtituloToolbar
import br.com.redcode.base.extensions.to__yyyy_MM_dd__HH_mm_ss
import br.com.redcode.easyreftrofit.sample.R
import br.com.redcode.easyreftrofit.sample.data.model.DogImage
import br.com.redcode.easyreftrofit.sample.data.payloads.ResponseGETDogImage
import br.com.redcode.easyreftrofit.sample.domain.CustomActivity
import br.com.redcode.easyreftrofit.sample.rest.abstracts.NetworkAndErrorHandler
import br.com.redcode.easyreftrofit.sample.rest.interactors.DogImageRemoteInteractor
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : CustomActivity() {

    override val layout: Int = R.layout.activity_main
    private val interactor by lazy { DogImageRemoteInteractor(this) }

    override fun afterOnCreate() {
        button.setOnClickListener {
            doRequest()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun doRequest() {
        launch(main()) {
            showProgress()

            val asyncDog = async(io()) { interactor.receiveRemovals() }
            val dog = asyncDog.await()

            when {
                dog != null -> {
                    showMessage(dog.status)
                    setupTextView(dog)
                }
                else -> textView.text = "Shit happens"
            }

            hideProgress()
        }
    }

    private fun setupTextView(dogImage: DogImage) {
        launch(main()) {
            val asyncJSON = async(io()) {
                val recreatedResponse = dogImage.toPayload()
                val adapter = NetworkAndErrorHandler.moshi.adapter(ResponseGETDogImage::class.java)
                val json: String? = adapter.toJson(recreatedResponse)
                return@async extract safe json
            }

            val asyncTimestamp = async(io()) {
                val calendar = Calendar.getInstance()
                return@async calendar.to__yyyy_MM_dd__HH_mm_ss()
            }

            setSubtituloToolbar(asyncTimestamp.await())
            textView.text = asyncJSON.await()
        }
    }

    override fun getContentView(): View? = button
}
