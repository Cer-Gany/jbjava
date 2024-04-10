package com.example.jbjava;



import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Job> jobs = new ArrayList<>();
    private EditText editTextId, editTextDeadline, editTextProfit;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextId = findViewById(R.id.editTextId);
        editTextDeadline = findViewById(R.id.editTextDeadline);
        editTextProfit = findViewById(R.id.editTextProfit);
        textViewResult = findViewById(R.id.textViewResult);
    }

    public void addJob(View view) {
        String idStr = editTextId.getText().toString();
        String deadlineStr = editTextDeadline.getText().toString();
        String profitStr = editTextProfit.getText().toString();

        if (!idStr.isEmpty() && !deadlineStr.isEmpty() && !profitStr.isEmpty()) {
            char id = idStr.charAt(0);
            int deadline = Integer.parseInt(deadlineStr);
            int profit = Integer.parseInt(profitStr);

            jobs.add(new Job(id, deadline, profit));
            editTextId.getText().clear();
            editTextDeadline.getText().clear();
            editTextProfit.getText().clear();
        }
    }

    public void solveJobSequencing(View view) {
        if (jobs.isEmpty()) {
            textViewResult.setText("No jobs added.");
            return;
        }

        List<Character> sequence = JobSequencingAlgorithm.getJobSequence(jobs);

        StringBuilder sequenceString = new StringBuilder();
        for (char jobId : sequence) {
        sequenceString.append(jobId).append(" ");
    }

        int totalProfit = JobSequencingAlgorithm.getTotalProfit(jobs, sequence);

        String result = "Sequence of jobs: " + sequenceString.toString() + "\nTotal profit: " + totalProfit;
        textViewResult.setText(result);
    }
}
