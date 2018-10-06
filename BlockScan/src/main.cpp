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

std::map<uint256, CBlock*> mapBlocks;
std::multimap<uint256, CBlock*> mapPrevBlocks;
CBlock* pBestChain = NULL;
uint256 hashGenesisBlock("0xcea89aa6adb81572f8b9e5f9b5d0184cbbc25208164cb1547decf3655da9dc77");

void CBlock::toString() {
	printf("  Block:( version=%d, prev=[%s], merkle=[%s], nTime=%u, nBits=%u, nNonce=[%s]\n", nVersion, hashPrevBlock.ToString().c_str(),
			hashMerkleRoot.ToString().c_str(), nTime, nBits, nNonce.ToString().c_str());
	for(int i=0; i<vtx.size(); i++) {
		vtx[i].toString();
	}
}

void updateHeight(uint256 key, CBlock* prevBlock, unsigned int preHeight) {
	std::multimap<uint256, CBlock*>::iterator it;
	for (it=mapPrevBlocks.equal_range(key).first; it!=mapPrevBlocks.equal_range(key).second; ++it) {
		CBlock* block = it->second;
		if(block->chainwork == 0 || block->chainwork < prevBlock->chainwork + block->nBits) {
			block->height = preHeight + 1;
			block->prev = prevBlock;
			block->chainwork = prevBlock->chainwork + block->nBits;
			if(block->chainwork > pBestChain->chainwork)
				pBestChain = block;

		}
//		printf("set block[%s] with height = %u best=%u\n", block->hash.ToString().c_str(), block->height, pBestChain->height);
		updateHeight(block->hash, block, block->height);
	}
}

void finishedChain() {
	if(pBestChain == NULL)
		return;
	CBlock* after = pBestChain;
	CBlock* prev = pBestChain->prev;
	while(prev != NULL) {
		prev->next = after;
		after = prev;
		prev = prev->prev;
	}
}


void buildBestChain() {
	std::map<uint256, CBlock*>::iterator mi = mapBlocks.find(hashGenesisBlock);
	if(mi != mapBlocks.end()) {
		CBlock* block = mi->second;
		block->chainwork += block->nBits;
		pBestChain = block;
		updateHeight(hashGenesisBlock, block, 0);
	}
	finishedChain();
}

void scanBestChain() {
	CBlock* start = mapBlocks.find(hashGenesisBlock)->second;
	while(start != NULL) {
		printf("scan height=%u\n", start->height);
		start = start->next;
	}
}


int main(void) {

	ReadBlocks readBlocks;
	readBlocks.tryReadBlock();

	buildBestChain();
//	scanBestChain();

	return EXIT_SUCCESS;
}
