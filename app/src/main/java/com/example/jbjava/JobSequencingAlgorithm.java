package com.example.jbjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JobSequencingAlgorithm {
    public static List<Character> getJobSequence(List<Job> jobs) {
        Collections.sort(jobs); // Sort jobs by profit in decreasing order

        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        char[] sequence = new char[maxDeadline];
        Arrays.fill(sequence, '-');

        for (Job job : jobs) {
            for (int i = job.deadline - 1; i >= 0; i--) {
                if (sequence[i] == '-') {
                    sequence[i] = job.id;
                    break;
                }
            }
        }

        List<Character> sequenceList = new ArrayList<>();
        for (char jobId : sequence) {
            if (jobId != '-') {
                sequenceList.add(jobId);
            }
        }
        return sequenceList;
    }

    public static int getTotalProfit(List<Job> jobs, List<Character> sequence) {
        int totalProfit = 0;
        for (Job job : jobs) {
            if (sequence.contains(job.id)) {
                totalProfit += job.profit;
            }
        }
        return totalProfit;
    }
}
