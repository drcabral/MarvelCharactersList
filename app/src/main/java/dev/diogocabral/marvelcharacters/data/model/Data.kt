package dev.diogocabral.marvelcharacters.data.model

import com.google.gson.annotations.SerializedName

data class Data (
	@SerializedName("offset") val offset : Int,
	@SerializedName("limit") val limit : Int,
	@SerializedName("total") val total : Int,
	@SerializedName("count") val count : Int,
	@SerializedName("results") val results : List<Results>
)