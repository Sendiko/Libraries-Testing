package com.sendiko.librariestesting.dashboard.components

data class LibraryData(
    val name: Name,
    val moduleName: ModuleName,
    val version: Version,
    val destination: Any,
)

@JvmInline
value class Name(val name: String)

@JvmInline
value class ModuleName(val moduleName: String)

@JvmInline
value class Version(val version: String)
