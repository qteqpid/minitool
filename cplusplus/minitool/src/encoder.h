#ifndef MINITOOL_ENCODER_H
#define MINITOOL_ENCODER_H

#ifdef WIN32
/*
 * 这个类只能在Windows系统下使用
 */
#include <string>

namespace minitool {

class Encoder
{

public:
	//使用时请确认str是使用UTF8编码的
	static std::wstring UTF8ToWideChar(const char *str);
	static std::wstring UTF8ToWideChar(const std::string &str);

	//得到的string是使用UTF8编码的
	static std::string WideCharToUTF8(const wchar_t *wstr);
	static std::string WideCharToUTF8(const std::wstring &wstr);

private:
	static wchar_t *MultiByteToWideChar(unsigned int codePage, const char *str);
	static char *WideCharToMultiByte(unsigned int codePage, const wchar_t *str);

};

} // namespace minitool

#endif // WIN32

#endif // MINITOOL_ENCODER_H
