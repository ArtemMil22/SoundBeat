package com.example.modbeatbox

private const val WAV = ".wav"

class Sound (val assetPath:String,var soundId: Int? = null) {
                                           // для загрузки звуков в Soundpool
    val name = assetPath.split("/")
        .last()
        .removeSuffix(WAV)
}