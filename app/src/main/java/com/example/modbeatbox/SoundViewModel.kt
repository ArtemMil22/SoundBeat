package com.example.modbeatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class SoundViewModel(private val beatBox: BeatBox): BaseObservable() {
    fun onButtonClicked() {
        sound?.let {
            beatBox.play(it)
        }
    }

    var sound:Sound? = null
    set(sound){
        field = sound
        notifyChange() //оповещает класс привязки о том, что все Bindale - свойсва ваших объъектов были обновлены
    }
    @get: Bindable
    val title:String?
    get() = sound?.name

}