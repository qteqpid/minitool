/*
 * file.cc
 *
 *  Created on: 2011-8-15
 *      Author: shawnzhu
 */
#include "file.h"

#include <stdio.h>
#include <string.h>

namespace minitool
{

const int kBufferSize = 4 * 1024; //4k

bool File::ReadLine(std::string *line)
{
	line->clear();
	char buffer[kBufferSize];
	while (fgets(buffer, kBufferSize, file) != NULL)
	{
		int len = strnlen(buffer, kBufferSize);
		if (len > 0)
		{
			if (buf[len - 1] == '\n')
			{
				buf[len - 1] = '\0';
				if (len - 1 > 0)
				{
					*line += buf;
				}
				return true;
			}
			else
			{
				*line += buf;
			}
		}
	}
	return false;
}

} // namespace minitool
