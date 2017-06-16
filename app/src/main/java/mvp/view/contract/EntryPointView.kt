package franjam.mvpdemo.mvp.view.contract

import mvp.model.GiphyData

interface EntryPointView {
    fun updateRecyclerViewData(giphyData: GiphyData)
    fun displayInvalidDataMessage(message:String)
}
