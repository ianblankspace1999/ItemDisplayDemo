package com.sample.bonial_examination.data.model

data class Resource<out T> constructor(
    val status: ResourceState,
    val data: T? = null,
    val message: String? = null,
    val throwable: Throwable? = null,
    val errorCode: Int? = null
)

enum class ResourceState {
    LOADING, SUCCESS, ERROR, CANCEL
}