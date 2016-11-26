#include <string.h>
#include <jni.h>

extern "C"
{
	JNIEXPORT jstring JNICALL Java_com_MCAL_NativeAddonTools_MainActivity_demangle(JNIEnv* env, jobject thiz,jstring name)
	{
		
		return env->NewStringUTF("Hello from JNI !");
	}
}
