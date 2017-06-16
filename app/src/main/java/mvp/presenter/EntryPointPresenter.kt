package mvpdemo.mvp.presenter

import franjam.mvpdemo.mvp.view.contract.EntryPointView
import franjam.mvpdemo.networking.request.RestClient
import mvp.model.GiphyData
import networking.service.GiphyService
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class EntryPointPresenter(var view: EntryPointView?, val giphyService: GiphyService = GiphyInteractor()) {

    private var compositeRxSubscription: CompositeSubscription = CompositeSubscription()

    fun onCreate() {
        initializeRequest()
    }

    fun onStop() {
        unSubscribeRequest()
        view = null
    }

    private fun initializeRequest() {
        val request = giphyService.getGiphy(QUERY_TEXT).subscribe(object : Subscriber<GiphyData>() {
            override fun onCompleted() {}

            override fun onError(e: Throwable) {
                view?.displayInvalidDataMessage(e.message!!)
            }

            override fun onNext(giphyData: GiphyData) {
                view?.updateRecyclerViewData(giphyData)
            }
        })
        compositeRxSubscription.add(request)
    }

    private fun unSubscribeRequest() {
        compositeRxSubscription.unsubscribe()
    }

    companion object {
        private val QUERY_TEXT = "cats"
    }
}

class GiphyInteractor : GiphyService {
    val client: RestClient = RestClient()

    override fun getGiphy(query: String): Observable<GiphyData> {

        return client.create(GiphyService::class.java)
                .getGiphy(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}


