package com.test;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxRectangler{
	public int largestRectangleArea(int[] heights) {
		int maxArea=0;
		int n= heights.length;
		Deque<Integer> stack = new ArrayDeque<Integer>(); 
		
		for(int i=0;i <=n ;i++) {
			int h = (i == n) ? 0 : heights[i];
			while(!stack.isEmpty() && heights[stack.peek()] > h) {
				int height = heights[stack.pop()];
				int width = stack.isEmpty() ? i : i- stack.peek()- 1;
				maxArea = Math.max(maxArea, height * width);
			}
			stack.push(i);
		}
		return maxArea;
	}
}