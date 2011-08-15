#ifndef MINITOOL_ENCODER_H
#define MINITOOL_ENCODER_H

#ifdef WIN32
/*
 * �����ֻ����Windowsϵͳ��ʹ��
 */
#include <string>

namespace minitool {

class Encoder
{

public:
	//ʹ��ʱ��ȷ��str��ʹ��UTF8�����
	static std::wstring UTF8ToWideChar(const char *str);
	static std::wstring UTF8ToWideChar(const std::string &str);

	//�õ���string��ʹ��UTF8�����
	static std::string WideCharToUTF8(const wchar_t *wstr);
	static std::string WideCharToUTF8(const std::wstring &wstr);

private:
	static wchar_t *MultiByteToWideChar(unsigned int codePage, const char *str);
	static char *WideCharToMultiByte(unsigned int codePage, const wchar_t *str);

};

} // namespace minitool

#endif // WIN32

#endif // MINITOOL_ENCODER_H
