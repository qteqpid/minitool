/*
 * common_test.cc
 *
 *  Created on: 2011-8-15
 *      Author: shawnzhu
 */
#include <gtest/gtest.h>

#include "common.h"

namespace minitool
{

struct ABC
{
	char x;
	int y;
	double z;
};

TEST(Common, ARRAY_SIZE)
{
	char x[10];
	ASSERT_EQ(10, ARRAY_SIZE(x));

	int y[10];
	ASSERT_EQ(10, ARRAY_SIZE(y));

	struct ABC z[10];
	ASSERT_EQ(10, ARRAY_SIZE(z));
}

} // namespace minitool
