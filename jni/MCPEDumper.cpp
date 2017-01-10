#include <jni.h>
#include <fstream>
#include <string>
#include <vector>
#include <cxxabi.h>
#include <sys/stat.h>

#include "elfio/elfio_dump.hpp"

using namespace ELFIO;

struct MCPESymbol
{
	std::string   name;
	Elf64_Addr    value;
    Elf_Xword     size;
    unsigned char bind;
    unsigned char type;
    Elf_Half      section;
    unsigned char other;
};

std::vector<MCPESymbol>mcpeSymbolsList;

void loadSymbols(const elfio& reader )
{
	mcpeSymbolsList.clear();
	Elf_Half n = reader.sections.size();
    for ( Elf_Half i = 0; i < n; ++i )
	{
		section* sec = reader.sections[i];
		if ( SHT_SYMTAB == sec->get_type() || SHT_DYNSYM == sec->get_type() ) 
		{
			symbol_section_accessor symbols( reader, sec );
			Elf_Xword     sym_no = symbols.get_symbols_num();
			if ( sym_no > 0 )
			{
				for ( Elf_Half i = 0; i < sym_no; ++i ) 
				{
					MCPESymbol symbol_mcpe;
                    symbols.get_symbol( i, symbol_mcpe.name, symbol_mcpe.value, symbol_mcpe.size,symbol_mcpe. bind,symbol_mcpe. type, symbol_mcpe.section, symbol_mcpe.other );
					mcpeSymbolsList.push_back(symbol_mcpe);
                }
            }
        }
    }
}

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
JNIEXPORT jboolean JNICALL Java_com_mcal_MCPEDumper_nativeapi_MCPEDumper_hasFile(JNIEnv* env, jobject thiz,jstring path)
{
	std::ifstream istream(jstringTostring(env,path).c_str());
	return istream.is_open();
}
JNIEXPORT jstring JNICALL Java_com_mcal_MCPEDumper_nativeapi_MCPEDumper_getNameAt(JNIEnv* env, jobject thiz,jint pos)
{
	return env->NewStringUTF(mcpeSymbolsList[pos].name.c_str());
}
JNIEXPORT jstring JNICALL Java_com_mcal_MCPEDumper_nativeapi_MCPEDumper_getDemangledNameAt(JNIEnv* env, jobject thiz,jint pos)
{
	char*name=abi::__cxa_demangle(mcpeSymbolsList[pos].name.c_str(),0,0,0);
	return env->NewStringUTF(name?name:"");
}
JNIEXPORT jint JNICALL Java_com_mcal_MCPEDumper_nativeapi_MCPEDumper_getSize(JNIEnv* env, jobject thiz)
{
	return mcpeSymbolsList.size();
}
JNIEXPORT jshort JNICALL Java_com_mcal_MCPEDumper_nativeapi_MCPEDumper_getTypeAt(JNIEnv* env, jobject thiz,jint pos)
{
	return (jshort)((short)mcpeSymbolsList[pos].type);
}
JNIEXPORT jshort JNICALL Java_com_mcal_MCPEDumper_nativeapi_MCPEDumper_getBindAt(JNIEnv* env, jobject thiz,jint pos)
{
	return (jshort)((short)mcpeSymbolsList[pos].bind);
}
JNIEXPORT void JNICALL Java_com_mcal_MCPEDumper_nativeapi_MCPEDumper_load(JNIEnv* env, jobject thiz,jstring path)
{
	elfio reader;
	reader.load(jstringTostring(env,path));
	loadSymbols(reader);
}
JNIEXPORT jstring JNICALL Java_com_mcal_MCPEDumper_nativeapi_MCPEDumper_demangleOnly(JNIEnv* env, jobject thiz,jstring jname)
{
	char*name=abi::__cxa_demangle(jstringTostring(env,jname).c_str(),0,0,0);
	return env->NewStringUTF(name?name:"");
}
JNIEXPORT jstring JNICALL Java_com_mcal_MCPEDumper_nativeapi_MCPEDumper_demangle(JNIEnv* env, jobject thiz,jstring name)
{
	std::string methodsName=jstringTostring(env,name);
	
	std::string bridgeString;
	std::vector<std::string>strings;
	std::string result;
	
	for(char letter:methodsName)
	{
		if(letter=='\n'&&letter!=' '&&!bridgeString.empty())
		{
			strings.push_back(bridgeString);
			bridgeString="";
		}
		else bridgeString+=letter;
	}
	if(!bridgeString.empty())
		strings.push_back(bridgeString);
	
	for(std::string string:strings)
	{
		if(abi::__cxa_demangle(string.c_str(),0,0,0))
		{
			result+=abi::__cxa_demangle(string.c_str(),0,0,0);
			result+="\n";
		}
		else if(!string.empty())
		{
			result+=string;
			result+="\n";
		}
	}
	return env->NewStringUTF(result.c_str());
}

}
