/*
 * file.h
 *
 *  Created on: 2011-8-15
 *      Author: shawnzhu
 */

#ifndef MINITOOL_FILE_H_
#define MINITOOL_FILE_H_

#include <string>

namespace minitool
{

class File {
public:
	bool ReadLine(std::string *line);
};

} // namespace minitool

#endif // MINITOOL_FILE_H_
