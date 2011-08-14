
#ifdef WIN32

#include "encoder.h"
#include <gtest/gtest.h>


TEST(EncoderTest, UTF8ToWideChar) {
	char str[]={(char)0x61,(char)0x62,(char)0xe4,(char)0xb8,(char)0xad,(char)0xe6,(char)0x96,(char)0x87,0x0};//"ab中文"的UTF8编码
	wchar_t wstr[]={0x0061,0x0062,0x4e2d,0x6587,0x0};//"ab中文"的UTF16编码

	std::wstring to_wstr=minitool::Encoder::UTF8ToWideChar(str);
	ASSERT_TRUE(std::wstring(wstr) == to_wstr);
}

TEST(EncoderTest, WideCharToUTF8) {
	char str[]={(char)0x61,(char)0x62,(char)0xe4,(char)0xb8,(char)0xad,(char)0xe6,(char)0x96,(char)0x87,0x0};//"ab中文"的UTF8编码
	wchar_t wstr[]={0x0061,0x0062,0x4e2d,0x6587,0x0};//"ab中文"的UTF16编码

	std::string to_str=minitool::Encoder::WideCharToUTF8(wstr);
	ASSERT_TRUE(std::string(str) == to_str);
}

#endif // WIN32