/*
 * permutation.cc
 *
 *  Created on: May 1, 2017
 *      Author: garfield
 */

#include <stdio.h>

using namespace std;
int A[] = {0,0,0,0,0};

void permutation(int n ,int s, int* array) {
	if(s==n){
		for(int i=0;i<n;i++) {
			printf("%d ", array[i]);
		}
		printf("\n");
	}
	for(int i=0;i<n;i++){
		if(A[i] != 1) {
			A[i]=1;
			array[s]=i+1;
			permutation(n ,s+1, array);
			A[i] = 0;
		}
	}
}

void combination (int n, int d, int s) {
	if(d==n)return;
	for(int i=s;i<n;i++){
		A[d] = i+1;
		for(int j=0;j<=d;j++)printf("%d ",A[j]);
		printf("\n");
		combination(n ,d+1, i+1);
	}
}

int main() {
	int array[5];
//	permutation(5, 0, array);
	combination (5, 0, 0);
	return 0;
}
