#include <stdio.h>
#include <string.h>

#include <vector>

using namespace std;

static unsigned char flag[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

void vByteToHexStr(vector<unsigned char> &v, int start, int len, unsigned char**result) {
	if(v.size() == 0 || start > v.size()) {
		len = 0;
	}else if((start + len) > v.size()) {
		len = v.size() - start;
	}

	unsigned char* p = new unsigned char[len*2 + 3];
	*result = p;

	int pos = 2;
	if(len > 0) {
		p[0] = '0';
		p[1] = 'x';
	}
	for(int i=start; i<start+len; i++) {
		p[pos++] = flag[v[i]>>4];
		p[pos++] = flag[v[i] & 0x0f];
	}
	p[pos] = '\0';
}

void vByteToHexStr(vector<unsigned char> &v, int start, int len, unsigned char*result) {
	if(v.size() == 0 || start > v.size()) {
		len = 0;
	}else if((start + len) > v.size()) {
		len = v.size() - start;
	}

	unsigned char* p = result;

	int pos = 2;
	if(len > 0) {
		p[0] = '0';
		p[1] = 'x';
	}
	for(int i=start; i<start+len; i++) {
		p[pos++] = flag[v[i]>>4];
		p[pos++] = flag[v[i] & 0x0f];
	}
	p[pos] = '\0';
}


