/*
 * ut_vector.cc
 *
 *  Created on: Oct 4, 2018
 *      Author: abcmint
 */
#ifndef INCLUDE_UT_VECTOR_H_
#define INCLUDE_UT_VECTOR_H_

#include "uint256.h"
#include <stdlib.h>
#include "hash.h"
#include "abcmint.h"
using namespace std;

class GarfieldVector {
public:
	vector<unsigned char> datas;
	int index;

	GarfieldVector() {
		index = 0;
	}
	bool isBlockStart() {
		bool flag = false;
		if(datas[index] == 0xc1 && datas[index+1] == 0xf7 &&
				datas[index+2] == 0xfb && datas[index + 3] == 0xfd)
			flag = true;

		index = index + 4;
		return flag;
	}

	unsigned char getByte() {
		unsigned char ch = datas[index];
		index = index + 1;
		return ch;
	}

	unsigned short getShort() {
		unsigned short out = 0;
		unsigned char *p = (unsigned char *)(&out);
		p[0] = datas[index + 0];
		p[1] = datas[index + 1];
		index = index + 2;
		return out;
	}

	unsigned int getInt() {
		unsigned int out = 0;
		unsigned char *p = (unsigned char *)(&out);
		p[0] = datas[index + 0];
		p[1] = datas[index + 1];
		p[2] = datas[index + 2];
		p[3] = datas[index + 3];
		index = index + 4;
		return out;
	}

	uint64 getInt64() {
		uint64 out = 0;
		unsigned char *p = (unsigned char *)(&out);
		p[0] = datas[index + 0];
		p[1] = datas[index + 1];
		p[2] = datas[index + 2];
		p[3] = datas[index + 3];
		p[4] = datas[index + 4];
		p[5] = datas[index + 5];
		p[6] = datas[index + 6];
		p[7] = datas[index + 7];
		index = index + 8;
		return out;
	}

	double getDouble() {
		double out = 0.0;
		unsigned char *p = (unsigned char *)(&out);
		p[0] = datas[index + 0];
		p[1] = datas[index + 1];
		p[2] = datas[index + 2];
		p[3] = datas[index + 3];
		p[4] = datas[index + 4];
		p[5] = datas[index + 5];
		p[6] = datas[index + 6];
		p[7] = datas[index + 7];
		index = index + 8;
		return out;
	}

	uint256 get256() {
		vector<unsigned char> reV;
		for(int i=0; i<32; i++) {
			reV.push_back(datas[index + i]);
		}

		uint256 tmp(reV);
		index = index + 32;
		return tmp;
	}

	uint256 getBlockHash() {
		vector<unsigned char> reV;
		for(int i=0; i<108; i++) {
			reV.push_back(datas[index + i]);
		}

		return Hash(reV.begin(), reV.end());
	}

	void getDatas(vector<unsigned char> &v, int len) {
		for(int i=0; i<len; i++) {
			v.push_back(datas[index + i]);
		}
		index = index + len;
	}

	unsigned int getLength() {
		unsigned int first = (unsigned int)getByte();

		if(first == 253) {
			first = getShort();
		}else if(first == 254) {
			first = getInt();
		}else if(first == 255) {
			first = -1;
		}
		printf("first = %u\n", first);
		return first;
	}

	void decodeBlock(CBlock& block) {
		uint256 blockHash = getBlockHash();
		printf("try to decode Block[%s].\n", blockHash.ToString().c_str());

		block.nVersion = getInt();
		block.hashPrevBlock = get256();
		block.hashMerkleRoot = get256();
		block.nTime = getInt();
		block.nBits = getInt();
		block.nNonce = get256();

	}

	void decodeTransactions(CBlock& block) {
		int nTrans = getLength();

		while(nTrans) {
			CTransaction transaction;
			transaction.nVersion = getInt();
			unsigned int nTxIn = getLength();
			while(nTxIn) {
				CTxIn txin;
				txin.prevout.hash = get256();
				txin.prevout.n = getInt();
				unsigned int scriptSigLength = getLength();
				printf("scriptSigLength=%u currentindex=%d left=%u\n", scriptSigLength, index, nTxIn);
				getDatas(txin.scriptSig, scriptSigLength);
				txin.nSequence = getInt();
				transaction.vin.push_back(txin);
				nTxIn--;
			}

			unsigned int nTxOut = getLength();
			while(nTxOut) {
				CTxOut txout;
				txout.nValue = getDouble();
				unsigned int scriptPubKeyLength = getLength();
				getDatas(txout.scriptPubKey, scriptPubKeyLength);
				transaction.vout.push_back(txout);
				nTxOut--;
			}
			transaction.nLockTime = getInt();
			block.vtx.push_back(transaction);
			nTrans--;
		}
	}

	void reset() {
		datas.clear();
		index = 0;
	}
};


#endif
