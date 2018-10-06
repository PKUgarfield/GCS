// Copyright (c) 2009-2010 Satoshi Nakamoto
// Copyright (c) 2009-2012 The Bitcoin developers
// Copyright (c) 2018 The Abcmint developers

#ifndef ABCMINT_HASH_H
#define ABCMINT_HASH_H

#include "uint256.h"
#include "pqcrypto/pqcrypto.h"

#include <vector>

template<typename T1>
inline uint256 Hash(const T1 pbegin, const T1 pend)
{
    static unsigned char pblank[1];
    uint256 hash1;
	pqcSha256(pbegin == pend ? pblank : (unsigned char*)&pbegin[0], (pend - pbegin) * sizeof(pbegin[0]),(unsigned char*)&hash1);
    uint256 hash2;
	pqcSha256((unsigned char*)&hash1, sizeof(hash1), (unsigned char*)&hash2);
    return hash2;
}

inline uint256 Hash(const std::vector<unsigned char>& vch) {
    uint256 hash1;
    pqcSha256(&vch[0], vch.size(), (unsigned char*)&hash1);
    uint256 hash2;
    pqcSha256((unsigned char*)&hash1, sizeof(hash1), (unsigned char*)&hash2);
    return hash2;
}

#endif
