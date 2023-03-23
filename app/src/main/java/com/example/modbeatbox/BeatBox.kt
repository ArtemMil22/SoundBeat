package com.example.modbeatbox

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"
private const val MAX_SOUNDS = 5

class BeatBox(private val assets: AssetManager) {

    val sounds:List<Sound>
    private val soundPool = SoundPool.Builder()
        .setMaxStreams(MAX_SOUNDS)
        .build()

    init{
        sounds = loadSounds()
    }

    fun play(sound:Sound){
        sound.soundId?.let{
            soundPool.play(it,1.0f,1.0f,1,0,1.0f)
        }
    } //Параметры содержат соответственно: идентификатор звука, громкость слева, громкость справа, приоритет (игнорируется), признак циклического воспроизведения и скорость воспроизведения.

    fun release(){
        soundPool.release()  //Корректное приложение должно освободить ресурсы SoundPool вызовом SoundPool.release() после завершения работы
    }
   private fun loadSounds():List<Sound>{
        val soundNames:Array<String>
        try{
            soundNames = assets.list(SOUNDS_FOLDER)!!
        }catch (e:Exception){
            Log.e(TAG,"Не удалось перечислить активы",e)
            return emptyList()
        }
        val sounds = mutableListOf<Sound>()
        soundNames.forEach { filename ->
            val assetPath = "$SOUNDS_FOLDER/$filename"
            val sound = Sound(assetPath)
           // sounds.add(sound)
            try{
                load(sound)
                sounds.add(sound)
            } catch (ioe:IOException){
                Log.e(TAG,"Cound not load sound $filename",ioe)
            }
        }
        return sounds
    }

    //функция load(Sound) для загрузки Sound в SoundPool.
    private fun load(sound:Sound){
        val afd:AssetFileDescriptor = assets.openFd(sound.assetPath)
        val soundId = soundPool.load(afd,1) //soundPool.load(AssetFileDescriptor, Int) загружает файл в SoundPool
        sound.soundId = soundId
    }

}