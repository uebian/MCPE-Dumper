#include <jni.h>
#include <fstream>
#include <string>
#include <vector>
#include <cxxabi.h>

std::string jstringTostring(JNIEnv* env, jstring jstr)
{
	char* rtn = NULL;
	jclass clsstring = env->FindClass("java/lang/String");
	jstring strencode = env->NewStringUTF("GB2312");
	jmethodID mid = env->GetMethodID(clsstring, "getBytes", "(Ljava/lang/String;)[B");
	jbyteArray barr= (jbyteArray)env->CallObjectMethod(jstr,mid,strencode);
	jsize alen = env->GetArrayLength(barr);
	jbyte* ba = env->GetByteArrayElements(barr,JNI_FALSE);
	if(alen > 0)
	{
		rtn = (char*)malloc(alen+1);
		memcpy(rtn,ba,alen);
		rtn[alen]=0;
	}
	env->ReleaseByteArrayElements(barr,ba,0);
	std::string stemp(rtn);
	free(rtn);
	return stemp;
}

extern "C"
{
JNIEXPORT void JNICALL Java_com_MCAL_NativeAddonTools_MainActivity_decompile(JNIEnv* env, jobject thiz,jstring path)
{
	std::string filePath=jstringTostring(env,path);
	
	std::ifstream istream(filePath.c_str());
	std::vector<char> letters;
	std::string bridgeString;
	std::ofstream ostream((filePath+".methods.undemangled.txt").c_str());
	std::ofstream ostream_demangled((filePath+".methods.demangled.txt").c_str());
	
	std::vector<std::string>strings;
	if(!istream.is_open())
		return;
	while(!istream.eof())
	{
		char letter;
		istream.get(letter);
		letters.push_back(letter);
	}
	for(char letter:letters)
	{
		if(letter=='\n'||letter==' ')
			continue;
		if(letter=='_')
		{
			strings.push_back(bridgeString);
			bridgeString="";
		}
		bridgeString+=letter;
	}
	for(std::string string:strings)
	{
		while(!string.empty())
		{
			if(string[0]!='_'||string[1]!='Z')
				goto next;
			if(abi::__cxa_demangle(string.c_str(),0,0,0))
			{
				ostream_demangled<<abi::__cxa_demangle(string.c_str(),0,0,0)<<std::endl;
				ostream<<string<<std::endl;
				goto next;
			}
			else
				string=string.substr(0,string.length()-1);
		}
		next:;
	}
}
}
