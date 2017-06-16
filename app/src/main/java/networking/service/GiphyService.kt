package networking.service

import mvp.model.GiphyData
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/*
 * Uses giphy API, find documentation below:
 *
 * https://github.com/Giphy/GiphyAPI
 *
 * NOTE: PUBLIC_BETA_KEY is the one provided in the sample API
 */
interface GiphyService {

    @GET(targetUrl)
    fun getGiphy(@Query("q") query: String): Observable<GiphyData>

    companion object {
        const val PUBLIC_BETA_KEY = "dc6zaTOxFJmzC"
        const val targetUrl = "v1/gifs/search?api_key=" + PUBLIC_BETA_KEY
    }
}
