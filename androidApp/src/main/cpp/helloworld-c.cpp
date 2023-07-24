//
// Created by Patrick Martin on 1/30/19.
//

#include <jni.h>

extern  "C"
JNIEXPORT jstring JNICALL
Java_app_trian_mvi_NativeLibWrapper_encrypt(
        JNIEnv *env, jobject thiz, jstring key, jstring plain
        ){

    return  plain;
}