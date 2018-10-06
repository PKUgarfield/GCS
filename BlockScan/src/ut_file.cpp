#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#include <vector>
#include "ut_file.h"
#include "ut_byte.h"

using namespace std;

//static char* dataDir = "blocks";
//
//void readFiles(vector<unsigned char> &v) {
//	char filepath[30];
//	filepath[0] = '\0';
//	int i = 0, req = 1024;
//	sprintf(filepath, "%s/blk%.8u.dat", dataDir, i);
//	printf("read file path = %s\n", filepath);
//	FILE * fd = fopen(filepath, "r");
//	while( fd != NULL) {
//		unsigned char buf[1024];
//		int len = 0;
//		len = fread(buf, sizeof(unsigned char), 1024, fd);
//		while(len > 0) {
//			for(int i=0; i<len; i++) {
//				v.push_back(buf[i]);
//				if(v.size() >= 1188) break;
//			}
//			len = fread(buf, sizeof(unsigned char), 1024, fd);
//		}
//		fclose(fd);
//
//		printf("read file size = %u\n", v.size());
//
//		i++;
//		filepath[0] = '\0';
//		sprintf(filepath, "%s\\blk%.8u.dat", dataDir, i);
//		fd = fopen(filepath, "r");
//	}
//}
static int count = 0;

void ReadBlocks::tryReadBlock() {

	FILE * fd = NULL;
	char filepath[40];
	do {
		if(fd != NULL) {
			fclose (fd);
			fd = NULL;
		}

		filepath[0] = '\0';
		sprintf(filepath, "blocks/blk%.8u.dat", currentFile);
		fd = fopen(filepath, "r");
		printf("open file [%s]\n", filepath);
		if(fd != NULL) {
			unsigned char buf[2*1024*1024];
			int len = 0;
			while(true) {
				len = fread(buf, sizeof(unsigned char), 8, fd);
				if(len != 8) {
					printf("[Error] block flag is not 8. \n");
					break;
				}

				for(int i=0; i<len; i++) {
					vt.datas.push_back(buf[i]);
				}

				if(!vt.isBlockStart()) {
					printf("[Error] block flag is wrong. \n");
					break;
				}

				int blockSize = vt.getInt();

				len = fread(buf, sizeof(unsigned char), blockSize, fd);
				if(len != blockSize) {
					printf("[Error] block size is wrong. len[%d] != [%d]\n", len , blockSize);
					break;
				}
				for(int i=0; i<blockSize; i++) {
					vt.datas.push_back(buf[i]);
				}

				count++;
//				printf("Read NO.[%d] block with blocksize [%d] -- total [%d] , index [%d]\n", count, blockSize, vt.datas.size(), vt.index);
				if(count==14502 || count==14958 || count==16403 || count==23838 || count==23841) {
					CBlock block;
					vt.decodeBlock(block);
					vt.decodeTransactions(block);
					block.toString();
				}else{
//					unsigned char result[233736*2 + 10];
//					vByteToHexStr(vt.datas, 0, vt.datas.size(), result);
//					printf("\n\n%s\n\n", result);

//					CBlock block;
//					vt.decodeBlock(block);
//					vt.decodeTransactions(block);
//					block.toString();
				}
				vt.reset();
			}
		}else {
			printf("[Error] cannot open file \n");
		}

		currentFile++;
	}while(fd != NULL);
}
