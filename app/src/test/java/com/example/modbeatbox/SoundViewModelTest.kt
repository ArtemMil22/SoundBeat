package com.example.modbeatbox

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@Suppress("DEPRECATION")
class SoundViewModelTest {

    private lateinit var beatBox: BeatBox
    private lateinit var sound: Sound
    private lateinit var subject:SoundViewModel

    @Before
    fun setUp() {
        beatBox = mock(BeatBox::class.java) //вы вызываете статическую функцию mock(Class), передавая класс, который хотите сымитировать
        sound = Sound("assetPath")
        subject = SoundViewModel(beatBox)
        subject.sound = sound
    }

    @Test
    fun exposesSoundNameAsTitle(){
        assertThat(subject.title,`is`(sound.name))
    }
    @Test
    fun callsBeatBoxPlayOnButtonClicked(){
        subject.onButtonClicked()
        verify(beatBox).play(sound) //Функция verify(Object) объекта Mockito проверяет, вызывались ли эти функции так, как вы ожидали
    }
}