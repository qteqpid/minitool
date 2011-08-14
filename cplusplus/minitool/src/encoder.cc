
#ifdef WIN32

#include "Encoder.h"
#include <windows.h>
#include <iostream>

namespace minitool {

using namespace std;

wstring Encoder::UTF8ToWideChar(const char *str) {
	wchar_t *wstr=MultiByteToWideChar(CP_UTF8, str);
	wstring r=wstring(wstr);
	delete[] wstr;
	return r;
}

wstring Encoder::UTF8ToWideChar(const string &str) {
	return UTF8ToWideChar(str.c_str());
}


string Encoder::WideCharToUTF8(const wchar_t *wstr) {
	char *str=WideCharToMultiByte(CP_UTF8, wstr);
	string r=string(str);
	delete[] str;
	return r;
}

string Encoder::WideCharToUTF8(const wstring &wstr) {
	return WideCharToUTF8(wstr.c_str());
}

wchar_t * Encoder::MultiByteToWideChar(unsigned int codePage, const char *str) {
	int len =::MultiByteToWideChar(codePage, 0, str,-1, NULL, 0);
	wchar_t *wstr=new wchar_t[len];
	::MultiByteToWideChar(codePage, 0, str, -1, wstr, len);
	return wstr;
}

char * Encoder::WideCharToMultiByte(unsigned int codePage, const wchar_t *wstr) {
	int len =::WideCharToMultiByte(codePage, 0, wstr,-1, NULL, 0, NULL, NULL);
	char *str=new char[len];
	::WideCharToMultiByte(codePage, 0, wstr,-1, str, len, NULL, NULL);
	return str;
}

} // namespace minitool

#endif // WIN32