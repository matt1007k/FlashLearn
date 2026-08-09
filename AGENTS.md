# AGENTS.md — FlashLearn

## Project type

Kotlin Multiplatform (KMP) + Compose Multiplatform app targeting **Android**.
Package: `dev.maxmeza.flashlearn`

## Build

```sh
./gradlew :composeApp:assembleDebug
```

Single module build: `./gradlew :<module>:<task>` (e.g. `:coreDatabase:assembleDebug`).

## Modules

| Module | Role |
|---|---|
| `:composeApp` | Android app entry, DI setup, Navigation3 root |
| `:coreDatabase` | Room DB (KSP codegen), DAO, entities, DI module |
| `:common:data` | Shared data layer |
| `:common:domain` | Shared domain layer |
| `:common:ui` | Theme, navigation primitives, shared composables |
| `:home:ui` | Home feature screen |

Dependency direction: `composeApp` → feature modules → `common:*` → `coreDatabase`.

## Key libraries

- **Compose Multiplatform** 1.10.1 (Material 3)
- **AndroidX Navigation3** (not the old Navigation Compose) — `NavKey`, `NavDisplay`, `EntryProviderScope`
- **Room** 2.8.4 with KSP — entities in `coreDatabase`, schema export at `coreDatabase/schemas/`
- **Koin** 4.1.1 for DI — modules wired in `AppDiSetup.kt`, loaded in `FlashLearnBaseApp`
- **Kotlinx Serialization** for `NavKey` route serialization

## Navigation architecture

Uses **Navigation3** (new, not the classic `NavController`):
- Routes are `@Serializable` sealed interface members implementing `NavKey` → `common/ui/.../NavKeys.kt`
- `Navigator` class wraps `NavigationState` (back stacks per top-level route) → `common/ui/.../Navigator.kt`
- Feature modules expose `EntryProviderScope<T>.XxxEntry(navigator)` functions (e.g. `HomeEntry`)
- `NavigationRoot` in `composeApp` assembles entries via `navigationState.toEntries(entryProvider { ... })`
- Adaptive layout: bottom bar vs. rail based on `WindowSizeClass`

## DI pattern

- `coreDatabase` exposes `getCoreDatabaseModule()` (expect/actual per platform)
- `composeApp` calls `initKoin { ... }` in `FlashLearnBaseApp.onCreate()`
- Add new modules to the `modules(...)` list in `AppDiSetup.kt`

## Room

- Entities: `DeckEntity`, `FlashcardEntity`, `TagEntity` in `coreDatabase/.../model/`
- DAO: `FlashcardDao`
- DB class: `AppDatabase` with `expect object AppDatabaseConstructor` (KSP generates actual)
- Schema JSON exported to `coreDatabase/schemas/` — commit schema changes with entity migrations

## Conventions

- Typesafe project accessors enabled (`projects.common.ui`, not `:common:ui`)
- `compose.resources.publicResClass = false` — resources accessed via generated class in `dev.maxmeza.flashlearn.common.ui.resources`
- JVM target: 11 across all modules
- Gradle configuration cache and caching enabled
- Android compileSdk/minSdk/targetSdk managed in `libs.versions.toml` — do not hardcode in build files

## Gotchas

- `composeApp/src` has **no `commonMain`** yet — shared code is in `androidMain`. If moving to true multiplatform, create `commonMain` source sets.
- `common:domain` uses `androidApplication` plugin instead of `androidLibrary` — likely a mistake, but do not change without verifying.
- Navigation3 is experimental — check `@OptIn` annotations when modifying navigation code.
