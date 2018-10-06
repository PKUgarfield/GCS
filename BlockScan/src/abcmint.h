#ifndef INCLUDE_ABCMINT_H_
#define INCLUDE_ABCMINT_H_

#include "uint256.h"
#include <map>

class CTransaction;

class CScript : public std::vector<unsigned char>
{

};

class CBlockHeader
{
public:
    // header
    int nVersion;
    uint256 hashPrevBlock;
    uint256 hashMerkleRoot;
    unsigned int nTime;
    unsigned int nBits;
    uint256 nNonce;
};

class CBlock : public CBlockHeader
{
public:
    // network and disk
    std::vector<CTransaction> vtx;
    unsigned int nLockTime;

    //only in memory
    CBlock* prev;
    CBlock* next;
    unsigned int height;
    uint256 hash;
    unsigned int chainwork;

    void toString();

    CBlock() {
    	prev = NULL;
    	next = NULL;
    	height = 0;
    	nLockTime = 0;
    	chainwork = 0;
    }
};

class COutPoint
{
public:
    uint256 hash;
    unsigned int n;
};

class CInPoint
{
public:
    CTransaction* ptx;
    unsigned int n;
};

class CTxOut
{
public:
    int64 nValue;
    CScript scriptPubKey;

    uint256 getAddress() {
    	if(scriptPubKey.size() == 37) {
    		CScript keyId;
    		for(int i=3; i<35; i++)
    			keyId.push_back(scriptPubKey[i]);
    		return uint256(keyId);
    	}else
    		return uint256(0);
    }

    //only in memory
    uint256 address;
};

class CTxIn
{
public:
    COutPoint prevout;
    CScript scriptSig;
    unsigned int nSequence;

    uint256 getAddress();

    //only in memory
    uint256 address;
};

class CTransaction
{
public:
    int nVersion;
    std::vector<CTxIn> vin;
    std::vector<CTxOut> vout;
    unsigned int nLockTime;

    //only in memory
    uint256 txId;

    void toString() {
    	printf("  Transcation: (nVersion=%d vin=%u vout=%u, nlockTime=%u)\n", nVersion, vin.size(), vout.size(), nLockTime);
    }
};

void updateHeight(uint256 key, CBlock* prevBlock, unsigned int preHeight);
void buildBestChain();



#endif
