/*
 * ut_file.h
 *
 *  Created on: 2018年10月4日
 *      Author: Garfield
 */

#ifndef INCLUDE_UT_FILE_H_
#define INCLUDE_UT_FILE_H_

#include <string.h>
#include <vector>
#include "ut_vector.h"
using namespace std;

void readFiles(vector<unsigned char> &v);

class ReadBlocks {
public:
	char blockDir[30];
	int currentFile;
	int currentPos;
	GarfieldVector vt;

	ReadBlocks() {
		sprintf(blockDir, "%s", "blocks");
		currentFile = 0;
		currentPos = 0;
	}

	void tryReadBlock();
};


#endif /* INCLUDE_UT_FILE_H_ */
