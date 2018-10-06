#ifndef INCLUDE_ABCMINT_H_
#define INCLUDE_ABCMINT_H_

#include "uint256.h"

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

    void toString();
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
};

class CTxIn
{
public:
    COutPoint prevout;
    CScript scriptSig;
    unsigned int nSequence;
};

class CTransaction
{
public:
    int nVersion;
    std::vector<CTxIn> vin;
    std::vector<CTxOut> vout;
    unsigned int nLockTime;

    void toString() {
    	printf("  Transcation: (nVersion=%d vin=%u vout=%u, nlockTime=%u)\n", nVersion, vin.size(), vout.size(), nLockTime);
    }
};

#endif
