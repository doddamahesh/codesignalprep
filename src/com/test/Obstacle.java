package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Obstacle {
	private static class SegmentTree {

        private final int[] tree;
        private final int n;

        SegmentTree(int n) {
            this.n = n;
            this.tree = new int[4 * n];
        }

        // Point update: tree[index] = value
        void update(int node, int left, int right,int index, int value) {
            if (left == right) {
                tree[node] = value;
                return;
            }
            int mid = left + (right - left) / 2;
            if (index <= mid) {
                update(node * 2, left, mid, index, value);
            } else {
                update(node * 2 + 1, mid + 1, right, index, value);
            }
            tree[node] = Math.max(tree[node * 2],tree[node * 2 + 1] );
        }

        void update(int index, int value) {
            update(1, 0, n - 1, index, value);
        }

        // Range maximum query
        int query(int node, int left, int right,int queryLeft, int queryRight) {
            if (queryLeft > right || queryRight < left) {
                return 0;
            }
            if (queryLeft <= left && right <= queryRight) {
                return tree[node];
            }
            int mid = left + (right - left) / 2;
            int leftMax = query(node * 2,left, mid,queryLeft,queryRight);
            int rightMax = query(node * 2 + 1,mid + 1,right,queryLeft,queryRight);
            return Math.max(leftMax, rightMax);
        }

        int query(int left, int right) {
            if (left > right) {
                return 0;
            }
            return query(1, 0, n - 1, left, right);
        }
    }

    public boolean[] getResults(int[][] queries) {
        TreeSet<Integer> coordinates = new TreeSet<>();
        for (int[] query : queries) {
            coordinates.add(query[1]);
        }
        List<Integer> sortedCoordinates = new ArrayList<>(coordinates);
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < sortedCoordinates.size(); i++) {
            indexMap.put(sortedCoordinates.get(i), i);
        }
        SegmentTree segmentTree = new SegmentTree(sortedCoordinates.size());
        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);
        if (indexMap.containsKey(0)) {
            segmentTree.update(indexMap.get(0), 0);
        }
        List<Boolean> answer = new ArrayList<>();
         for (int[] query : queries) {
            int type = query[0];
            int x = query[1];
            if (type == 1) {
                Integer previous = obstacles.lower(x);
                Integer next = obstacles.higher(x);
                segmentTree.update(indexMap.get(x),x - previous);
                if (next != null) {
                    segmentTree.update(indexMap.get(next),next - x);
                }
                obstacles.add(x);
            } else {
                int size = query[2];
                Integer previous = obstacles.floor(x);
                int spaceToX = x - previous;
                int xIndex = indexMap.get(x);
                int maxGap = segmentTree.query(0, xIndex);
                boolean canPlace =
                        Math.max(maxGap, spaceToX) >= size;

                answer.add(canPlace);
            }
        }

        boolean[] result = new boolean[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    public static void main(String[] args) {

        Obstacle solution = new Obstacle();

        int[][] queries = {{1, 2},{2, 3, 3},{2, 3, 1},{2, 2, 2}};
        //int[][] queries = {{1, 7},{2, 7, 6},{1,2},{2, 7, 5},{2, 7, 6}};
        boolean[] result = solution.getResults(queries);

        System.out.println(Arrays.toString(result));
    }
}
