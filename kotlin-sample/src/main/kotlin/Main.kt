import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import br.com.redcode.easyreftrofit.samplekotlin.network.interactor.DogImageRemoteInteractor
import kotlin.coroutines.CoroutineContext

class Main : CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Unconfined

    fun main() {
        val interactor = DogImageRemoteInteractor(null)

        launch {
            val teste = interactor.receiveRemovals()

            println("teste: ${teste?.image}")
        }
    }

}

fun main() {
    Main().main()
}