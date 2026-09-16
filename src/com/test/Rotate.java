package com.test;

import java.util.Arrays;

public class Rotate{

	public static void main(String[] a) {
		System.out.println("test");
		char[][] input = {{'#','.','#'}};
		char[][] result = new Rotate().rotate(input);
		 for (char[] row : result) {
	            System.out.println(Arrays.toString(row));
	        }
	}
	
	private char[][] rotate(char[][] input){
		int m= input.length;
		int n = input[0].length;
		
		char[][] finalResult = new char[n][m];
		for(int i=0;i <n;i++) {
			for(int j=0;j<m;j++) {
				finalResult[i][j] = '.';
			}
		}
		
		for(int i=0;i<m;i++) {
			int write = n-1;
			for( int j=n-1;j >=0; j--) {
				if(input[i][j] == '*') {
					finalResult[j][m-1-i] = '*';
					write = j-1;
				} else if(input[i][j] == '#') {
					finalResult[write][m-1-i]='#';
					write--;
				}
			}
		}
		return finalResult;
	}
}