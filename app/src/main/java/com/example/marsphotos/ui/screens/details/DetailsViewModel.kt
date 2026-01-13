package com.example.marsphotos.ui.screens.details

import com.example.marsphotos.ui.BaseViewModel

class DetailsViewModel
    : BaseViewModel<DetailsContract.DetailsAction, DetailsContract.DetailsState, DetailsContract.DetailsEffect>() {

    override fun setInitialState() =
        DetailsContract.DetailsState()

    override fun handleViewAction(action: DetailsContract.DetailsAction) {
        when (action) {
            is DetailsContract.DetailsAction.IncrementCounter -> incrementCounter()
            is DetailsContract.DetailsAction.NavigateBack -> setEffect { DetailsContract.DetailsEffect.NavigateBack }
        }
    }

    private fun incrementCounter() {
        setState { copy(counter = counter + 1) }
    }
}
