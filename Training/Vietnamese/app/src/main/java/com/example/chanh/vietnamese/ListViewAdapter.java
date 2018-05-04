package com.example.chanh.vietnamese;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.chanh.vietnamese.data.Question;
import java.util.ArrayList;
public class ListViewAdapter extends ArrayAdapter<Question> {
    public static ArrayList<String> selectedAnswers;
    private Context context;
    private int resource;
    private ArrayList<Question> objects;

    public ListViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Question> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        selectedAnswers = new ArrayList<>();
        for (int i = 0; i < objects.size(); i++) {
            selectedAnswers.add("Not Attempted");
        }
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent,false);

        TextView tvQuestion;
        RadioButton rbA;
        RadioButton rbB;
        RadioButton rbC;
        RadioGroup rg;
        TextView tvAnswer;
        tvQuestion = (TextView) convertView.findViewById(R.id.tvquestion);
        rbA = (RadioButton) convertView.findViewById(R.id.rbA);
        rbB = (RadioButton) convertView.findViewById(R.id.rbB);
        rbC = (RadioButton) convertView.findViewById(R.id.rbC);
        rg =(RadioGroup) convertView.findViewById(R.id.rgAnswer);
        tvAnswer = (TextView) convertView.findViewById(R.id.tvAnswer);
        Question q = objects.get(position);
        tvQuestion.setText(String.valueOf(position+1) + ". " + q.getQuestion());

        rbA.setText("A. " + q.getA());
        rbB.setText("B. " +q.getB());
        rbC.setText("C. " +q.getC());
//        tvAnswer.setText("Correct answer is "+q.getCorrectAnswer());

        String test = (q.selectedAnswer!=null)?q.selectedAnswer.toString().substring(0,1):"D";

        if (test.equals("A")){
            rbA.setChecked(true);
            selectedAnswers.set(Integer.valueOf(position),"A");
        }
        else if (test.equals("B")){
            rbB.setChecked(true);
            selectedAnswers.set(Integer.valueOf(position),"B");
        }
        else if (test.equals("C")){
            rbC.setChecked(true);
            selectedAnswers.set(Integer.valueOf(position),"C");
        }
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Question question1=objects.get(position);
                RadioButton radioButton= (RadioButton) group.findViewById(checkedId);
                objects.get(position).selectedAnswer = radioButton.getText().toString();
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
