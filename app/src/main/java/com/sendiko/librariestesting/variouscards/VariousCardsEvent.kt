package com.sendiko.librariestesting.variouscards

sealed class VariousCardsEvent {
    data class OnSelectCard(val card: Int): VariousCardsEvent()
}
