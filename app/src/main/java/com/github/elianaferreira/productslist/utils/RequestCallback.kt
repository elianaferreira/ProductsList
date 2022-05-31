package com.github.elianaferreira.productslist.utils

interface RequestCallback {
    fun onSuccess(response: Any)
    fun onError(errorMessage: String)
}