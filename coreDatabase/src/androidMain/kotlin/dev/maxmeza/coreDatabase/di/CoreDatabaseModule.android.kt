package dev.maxmeza.coreDatabase.di

import dev.maxmeza.coreDatabase.data.getDatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun getNativeCoreDatabaseModule(): Module = module {
    single { getDatabaseBuilder(context = androidContext()) }
}
