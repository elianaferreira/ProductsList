package com.github.elianaferreira.productslist.utils

import android.view.View
import com.github.elianaferreira.productslist.R
import com.google.android.material.snackbar.Snackbar

class ErrorUtils {

    companion object {
        fun showErrorMessage(view: View, message: String) {
            Snackbar
                .make(view, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(view.context.getString(R.string.error_close_button)) { }
                .show()
        }
    }
}