package com.kapcb.ccc.helper;

import com.google.common.base.Preconditions;
import com.google.common.hash.Funnel;
import com.google.common.hash.Hashing;

/**
 * <a>Title: BloomFilterHelper </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/20 12:09
 */
public class BloomFilterHelper<T> {

    private final int numHashFunctions;

    private final int bitSize;

    private final Funnel<T> funnel;

    public BloomFilterHelper(Funnel<T> funnel, int expectedInsertions, double fpp) {
        Preconditions.checkArgument(funnel != null, "funnel can not be null!");
        this.funnel = funnel;
        // bit length
        this.bitSize = optimalNumOfBits(expectedInsertions, fpp);
        // hash calculate counts
        this.numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, bitSize);
    }

    /**
     * calculate the length of the bit[]
     *
     * @param n long
     * @param p double
     * @return int
     */
    private static int optimalNumOfBits(long n, double p) {
        if (p == 0) {
            // default min expected length
            p = Double.MIN_VALUE;
        }
        int sizeOfBitArray = (int) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
        return sizeOfBitArray;
    }

    /**
     * calculate hash counts
     *
     * @param n long
     * @param m long
     * @return int
     */
    private static int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }

    public int[] murmurHashOffset(T value) {
        int[] offset = new int[numHashFunctions];

        long hash64 = Hashing.murmur3_128().hashObject(value, funnel).asLong();
        int hashOne = (int) hash64;
        int hashTwo = (int) (hash64 >>> 32);
        for (int i = 1; i < numHashFunctions; i++) {
            int nextHash = hashOne + i * hashTwo;
            if (nextHash < 0) {
                nextHash = ~nextHash;
            }
            offset[i - 1] = nextHash % bitSize;
        }
        return offset;
    }
}
