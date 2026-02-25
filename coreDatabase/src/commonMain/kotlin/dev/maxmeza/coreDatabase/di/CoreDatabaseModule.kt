package dev.maxmeza.coreDatabase.di

import dev.maxmeza.coreDatabase.data.getRoomDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun getNativeCoreDatabaseModule(): Module

fun getCoreDatabaseModule(): Module = module {
    single { getRoomDatabase(builder = get()) }
}