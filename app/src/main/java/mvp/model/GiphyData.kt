package mvp.model

import com.google.gson.annotations.SerializedName

data class GiphyData(val data: List<Nested>)

data class Nested(val images: Images)

data class Images(@SerializedName("fixed_height") val fixedHeightImage: FixedHeightImage)

data class FixedHeightImage(val url: String)