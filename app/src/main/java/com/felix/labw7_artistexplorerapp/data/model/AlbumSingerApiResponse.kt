package com.felix.labw7_artistexplorerapp.data.model

import com.google.gson.annotations.SerializedName

data class AlbumSingerApiResponse(
    @SerializedName("album") val albumList: List<Album>?
)

/**
 * This class represents a single album's data.
 * It maps all the fields inside the "album" array object.
 *
 * Most fields are nullable (String?) to prevent crashes
 * when the API sends 'null' or empty values.
 */
data class Album(
    @SerializedName("idAlbum") val albumId: String,
    @SerializedName("idArtist") val artistId: String,
    @SerializedName("strAlbum") val albumName: String,
    @SerializedName("strArtist") val artistName: String,
    @SerializedName("intYearReleased") val yearReleased: String?,
    @SerializedName("strGenre") val genre: String?,
    @SerializedName("strStyle") val style: String?,
    @SerializedName("strLabel") val label: String?,
    @SerializedName("strReleaseFormat") val releaseFormat: String?,
    @SerializedName("strAlbumThumb") val albumThumbUrl: String?,
    @SerializedName("strAlbumThumbHQ") val albumThumbHqUrl: String?,
    @SerializedName("strDescriptionEN") val descriptionEN: String?,
    @SerializedName("intScore") val score: String?,
    @SerializedName("idLabel") val labelId: String?,
    @SerializedName("strAlbumStripped") val albumStripped: String?,
    @SerializedName("strArtistStripped") val artistStripped: String?,
    @SerializedName("intSales") val sales: String?,
    @SerializedName("strAlbumBack") val albumBackUrl: String?,
    @SerializedName("strAlbumCDart") val albumCdArtUrl: String?,
    @SerializedName("strAlbumSpine") val albumSpineUrl: String?,
    @SerializedName("strAlbum3DCase") val album3dCaseUrl: String?,
    @SerializedName("strAlbum3DFlat") val album3dFlatUrl: String?,
    @SerializedName("strAlbum3DFace") val album3dFaceUrl: String?,
    @SerializedName("strAlbum3DThumb") val album3dThumbUrl: String?,
    @SerializedName("strDescription") val description: String?,
    @SerializedName("strDescriptionDE") val descriptionDE: String?,
    @SerializedName("strDescriptionFR") val descriptionFR: String?,
    @SerializedName("strDescriptionCN") val descriptionCN: String?,
    @SerializedName("strDescriptionIT") val descriptionIT: String?,
    @SerializedName("strDescriptionJP") val descriptionJP: String?,
    @SerializedName("strDescriptionRU") val descriptionRU: String?,
    @SerializedName("strDescriptionES") val descriptionES: String?,
    @SerializedName("strDescriptionPT") val descriptionPT: String?,
    @SerializedName("strDescriptionSE") val descriptionSE: String?,
    @SerializedName("strDescriptionNL") val descriptionNL: String?,
    @SerializedName("strDescriptionHU") val descriptionHU: String?,
    @SerializedName("strDescriptionNO") val descriptionNO: String?,
    @SerializedName("strDescriptionIL") val descriptionIL: String?,
    @SerializedName("strDescriptionPL") val descriptionPL: String?,
    @SerializedName("intLoved") val loved: String?,
    @SerializedName("intScoreVotes") val scoreVotes: String?,
    @SerializedName("strReview") val review: String?,
    @SerializedName("strMood") val mood: String?,
    @SerializedName("strTheme") val theme: String?,
    @SerializedName("strSpeed") val speed: String?,
    @SerializedName("strLocation") val location: String?,
    @SerializedName("strMusicBrainzID") val musicBrainzId: String?,
    @SerializedName("strMusicBrainzArtistID") val musicBrainzArtistId: String?,
    @SerializedName("strAllMusicID") val allMusicId: String?,
    @SerializedName("strBBCReviewID") val bbcReviewId: String?,
    @SerializedName("strRateYourMusicID") val rateYourMusicId: String?,
    @SerializedName("strDiscogsID") val discogsId: String?,
    @SerializedName("strWikidataID") val wikidataId: String?,
    @SerializedName("strWikipediaID") val wikipediaId: String?,
    @SerializedName("strGeniusID") val geniusId: String?,
    @SerializedName("strLyricWikiID") val lyricWikiId: String?,
    @SerializedName("strMusicMozID") val musicMozId: String?,
    @SerializedName("strItunesID") val itunesId: String?,
    @SerializedName("strAmazonID") val amazonId: String?,
    @SerializedName("strLocked") val locked: String?
)