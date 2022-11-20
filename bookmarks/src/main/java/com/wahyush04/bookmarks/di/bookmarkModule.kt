package com.wahyush04.bookmarks.di

import com.wahyush04.bookmarks.ui.BookmarkViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookmarkModule = module{
    viewModel {
        BookmarkViewModel(get())
    }
}