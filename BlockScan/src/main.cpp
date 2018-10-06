//============================================================================
// Name        : Mytools.cpp
// Author      : garfield
// Version     :
// Copyright   : cn.garfield
// Description : Hello World in C, Ansi-style
//============================================================================

#include <stdio.h>
#include <stdlib.h>

#include "ut_byte.h"
#include "ut_file.h"
#include "ut_vector.h"
#include "hash.h"
#include "abcmint.h"

void CBlock::toString() {
	printf("  Block:( version=%d, prev=[%s], merkle=[%s], nTime=%u, nBits=%u, nNonce=[%s]\n", nVersion, hashPrevBlock.ToString().c_str(),
			hashMerkleRoot.ToString().c_str(), nTime, nBits, nNonce.ToString().c_str());
	for(int i=0; i<vtx.size(); i++) {
		vtx[i].toString();
	}
}


int main(void) {

//	GarfieldVector v;
//	readFiles(v.datas);
//
//	unsigned char*result = (unsigned char*)malloc(sizeof(unsigned char*));
//	vByteToHexStr(v, 0, v.size(), &result);
//
//	printf("flag = %d index = %d\n", v.isBlockStart(), v.index);
//	printf("size = %d index = %d\n", v.getInt(), v.index);
//	printf("blockHash = %s index = %d\n", v.getBlockHash().ToString().c_str(), v.index);
//	printf("version = %d index = %d\n", v.getInt(), v.index);
//	printf("prehash = %s index = %d\n", v.get256().ToString().c_str(), v.index);
//	printf("merklehash = %s index = %d\n", v.get256().ToString().c_str(), v.index);
//	printf("time = %d index = %d\n", v.getInt(), v.index);
//	printf("nbit = %d index = %d\n", v.getInt(), v.index);
//	printf("nNonce = %s index = %d\n", v.get256().ToString().c_str(), v.index);
	ReadBlocks readBlocks;
	readBlocks.tryReadBlock();

	return EXIT_SUCCESS;
}
