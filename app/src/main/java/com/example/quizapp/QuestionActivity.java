package com.example.quizapp;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;


public class QuestionActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView question, qCount, timer;
    private Button option1, option2, option3, option4;
    private List<Question> questionList;
    private int quesNum;
    private CountDownTimer countDown;
    private int score;
    //private int setNo;
    //private Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        question = findViewById(R.id.question);
        qCount = findViewById(R.id.questioncount);
        timer = findViewById(R.id.timer);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);


        questionList = new ArrayList<>();


        getQuestionsList();


    }

    private void getQuestionsList()
    {
        questionList.add(new Question("question 1","a","b","c","d",2));
        questionList.add(new Question("question h1","aa","ba","c","d",2));
        questionList.add(new Question("question j1","a","badd","c","d",2));
        questionList.add(new Question("question k1","a","sdfdb","c","d",2));
        questionList.add(new Question("question jhh1","a","bdfdfa","c","d",2));
        questionList.add(new Question("question 1","a","bdfasd","c","d",2));
        questionList.add(new Question("question 1","a","bsdfsfe","c","d",2));
        questionList.add(new Question("question 1","a","basdfd","c","d",2));
        setQuestion();
    }

    private void setQuestion()
    {
        timer.setText(String.valueOf(10));

        question.setText(questionList.get(0).getQuestion());
        option1.setText(questionList.get(0).getOption1());
        option2.setText(questionList.get(0).getOption2());
        option3.setText(questionList.get(0).getOption3());
        option4.setText(questionList.get(0).getOption4());


        qCount.setText(String.valueOf(1) + "/" + String.valueOf(questionList.size()));

        startTimer();

        quesNum = 0;

    }
//for count the timer
    private void startTimer()
    {
        countDown = new CountDownTimer(12000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished < 10000)
                    timer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                changeQuestion();
            }
        };

        countDown.start();

    }






    @Override
    public void onClick(View v) {

        int selectedOption = 0;

        switch (v.getId())
        {
            case R.id.option1 :
                selectedOption = 1;
                break;

            case R.id.option2:
                selectedOption = 2;
                break;

            case R.id.option3:
                selectedOption = 3;
                break;

            case R.id.option4:
                selectedOption = 4;
                break;

            default:
        }
//when useer has respond then timer stop
        countDown.cancel();
        checkAnswer(selectedOption, v);

    }
//check answer
    private void checkAnswer(int selectedOption, View view)
    {

        if(selectedOption == questionList.get(quesNum).getCorrectAns())
        {
            //Right Answer
            ((Button)view).setBackgroundColor(GREEN);
            score++;

        }
        else
        {
            //Wrong Answer
            ((Button)view).setBackgroundColor(RED);

            switch (questionList.get(quesNum).getCorrectAns())
            {
                case 1:
                    option1.setBackgroundColor(GREEN);
                    break;
                case 2:
                    option2.setBackgroundColor(GREEN);
                    break;
                case 3:
                    option3.setBackgroundColor(GREEN);
                    break;
                case 4:
                    option4.setBackgroundColor(GREEN);
                    break;

            }

        }
        //After answer 2 sec delay produce

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeQuestion();
            }
        }, 2000);



    }

//change the quesion number
    private void changeQuestion()
    {
        //Not 10 question completed then
        if( quesNum < questionList.size() - 1)
        {
            quesNum++;

            playAnim(question,0,0);
            playAnim(option1,0,1);
            playAnim(option2,0,2);
            playAnim(option3,0,3);
            playAnim(option4,0,4);

            qCount.setText(String.valueOf(quesNum+1) + "/" + String.valueOf(questionList.size()));

            timer.setText(String.valueOf(10));
            startTimer();

        }
        //if 10 question completed
        else
        {
            // Go to Score Activity
            Intent intent = new Intent(QuestionActivity.this,ScoreActivity.class);
            intent.putExtra("SCORE", String.valueOf(score) + "/" + String.valueOf(questionList.size()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            QuestionActivity.this.finish();
        }


    }

//after button Click animation produce
    private void playAnim(final View view, final int value, final int viewNum)
    {

        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500)
                .setStartDelay(100).setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if(value == 0)
                        {
                            switch (viewNum)
                            {
                                case 0:
                                    ((TextView)view).setText(questionList.get(quesNum).getQuestion());
                                    break;
                                case 1:
                                    ((Button)view).setText(questionList.get(quesNum).getOption1());
                                    break;
                                case 2:
                                    ((Button)view).setText(questionList.get(quesNum).getOption2());
                                    break;
                                case 3:
                                    ((Button)view).setText(questionList.get(quesNum).getOption3());
                                    break;
                                case 4:
                                    ((Button)view).setText(questionList.get(quesNum).getOption4());
                                    break;

                            }


                            if(viewNum != 0)
                                ((Button)view).setBackgroundColor(GREEN);


                            playAnim(view,1,viewNum);

                        }

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

    }
//when backpressed then timer not run in the background
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        countDown.cancel();
    }
}
