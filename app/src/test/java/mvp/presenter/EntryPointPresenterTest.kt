package mvp.presenter

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import franjam.mvpdemo.mvp.view.contract.EntryPointView
import mvp.model.GiphyData
import mvpdemo.mvp.presenter.EntryPointPresenter
import networking.service.GiphyService
import org.junit.Before
import org.junit.Test
import rx.Observable

class EntryPointPresenterTest {
    val mockView = mock<EntryPointView>()
    val mockService = mock<GiphyService>()
    var presenter: EntryPointPresenter? = null

    @Before
    fun setup() {
        presenter = EntryPointPresenter(mockView, mockService)
    }

    @Test
    fun testOnCreateOnSuccess() {
        val presenter = EntryPointPresenter(mockView, mockService)
        val fakeGiphy = GiphyData(emptyList())
        whenever(mockService.getGiphy("cats")).thenReturn(Observable.just(fakeGiphy))

        presenter.onCreate()

        verify(mockView).updateRecyclerViewData(fakeGiphy)
        verifyNoMoreInteractions(mockView)
    }

    @Test
    fun testOnCreateOnError() {
        val presenter = EntryPointPresenter(mockView, mockService)
        whenever(mockService.getGiphy("cats")).thenReturn(Observable.error(Throwable("error string")))

        presenter.onCreate()

        verify(mockView).displayInvalidDataMessage("error string")
        verifyNoMoreInteractions(mockView)
    }
}