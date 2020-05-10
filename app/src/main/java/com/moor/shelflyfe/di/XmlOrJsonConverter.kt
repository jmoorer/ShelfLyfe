package com.moor.shelflyfe.di


import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


class AnnotatedConverter(private val factoryMap: Map<Class<*>, Converter.Factory>) :
    Converter.Factory() {


    override fun responseBodyConverter(
        type: Type?,
        annotations: Array<Annotation>,
        retrofit: Retrofit?
    ): Converter<ResponseBody?, *>? {
        for (annotation in annotations) {
            val factory = factoryMap[annotation.annotationClass.java]
            if (factory != null) {
                return factory.responseBodyConverter(type, annotations, retrofit)
            }
        }
        return null
    }
}
