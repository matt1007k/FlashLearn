package dev.maxmeza.flashlearn.di

import dev.maxmeza.coreDatabase.di.getCoreDatabaseModule
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            getCoreDatabaseModule()
        )
    }
}