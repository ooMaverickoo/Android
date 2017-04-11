package com.example.android.quizapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //statics for strings
    final static String ANSWER_ONE = "mAnswerOne";
    final static String ANSWER_ONE_ONE = "mAnswerOneOne";
    final static String ANSWER_ONE_TWO = "mAnswerOneTwo";
    final static String ANSWER_ONE_THREE = "mAnswerOneThree";
    final static String ANSWER_ONE_FOUR = "mAnswerOneFour";
    final static String ANSWER_TWO = "mAnswerTwo";
    final static String ANSWER_THREE = "mAnswerThree";
    final static String ANSWER_FOUR = "mAnswerFour";
    final static String SCORE_COUNTER ="mScoreCounter";
    final static String QUESTION_COUNTER= "mQuestionCounter";
    final static String STATISTIC_TEXT = "mStatisticText";

    //Important variables for showing the points of the value
    private int mQuestionCounter;//counter the stat of the question 0 = welcome 4= last question 5 = statistics
    private int mScoreCounter;
    private String mStatisticText;

    private CardView mCardWelcomeWindow;
    private CardView mCardQuestionOne;
    private CardView mCardQuestionTwo;
    private CardView mCardQuestionThree;
    private CardView mCardQuestionFour;
    private CardView mCardStatistics;
    //Check Boxes
    private CheckBox mCheckBoxAnswerOneOne;
    private CheckBox mCheckBoxAnswerOneTwo;
    private CheckBox mCheckBoxAnswerOneThree;
    private CheckBox mCheckBoxAnswerOneFour;

    private TextView mStatisticView;

    //RadioButtons
    private RadioGroup mRadioGroupAnswerTwo, mRadioGroupAnswerFour;
    private RadioButton mRadioButtonTwoOne, mRadioButtonTwoTwo,
            mRadioButtonTwoThree, mRadioButtonTwoFour;

    private RadioButton mRadioButtonFourOne, mRadioButtonFourTwo,
            mRadioButtonFourThree, mRadioButtonFourFour;

    //Edit Text fields
    EditText mEditTextInputUserName;
    EditText mEditTextInputAnswerThree;

    //User answer
    private String mUserName = "";
    private String mAnswerInputThree = "";
    //question one
    private boolean mAnswerOne = false;
    private boolean mAnswerOneOne = false;
    private boolean mAnswerOneTwo = false;
    private boolean mAnswerOneThree = false;
    private boolean mAnswerOneFour = false;

    //question two
    private boolean mAnswerTwo = false;

    //question three
    private boolean mAnswerThree = false;

    //question four
    private boolean mAnswerFour = false;


    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //question windows
        mCardWelcomeWindow = (CardView) findViewById(R.id.welcome);
        mCardQuestionOne = (CardView) findViewById(R.id.questionOne);
        mCardQuestionTwo = (CardView) findViewById(R.id.questionTwo);
        mCardQuestionThree = (CardView) findViewById(R.id.questionThree);
        mCardQuestionFour = (CardView) findViewById(R.id.questionFour);
        mCardStatistics = (CardView) findViewById(R.id.statistic_card);

        mStatisticView = (TextView) findViewById(R.id.statistic_view);

        //answers
        mEditTextInputUserName = (EditText) findViewById(R.id.user_name);
        mEditTextInputAnswerThree = (EditText) findViewById(R.id.answer_edit_text_three);


        //CheckBoxes
        mCheckBoxAnswerOneOne = (CheckBox) findViewById(R.id.checkbox_one_one);
        mCheckBoxAnswerOneTwo = (CheckBox) findViewById(R.id.checkbox_one_two);
        mCheckBoxAnswerOneThree = (CheckBox) findViewById(R.id.checkbox_one_three);
        mCheckBoxAnswerOneFour = (CheckBox) findViewById(R.id.checkbox_one_four);


        //RadioButtons question two
        mRadioGroupAnswerTwo = (RadioGroup) findViewById(R.id.radio_group_two);
        mRadioButtonTwoOne = (RadioButton) findViewById(R.id.radio_button_two_one);
        mRadioButtonTwoTwo = (RadioButton) findViewById(R.id.radio_button_two_two);
        mRadioButtonTwoThree = (RadioButton) findViewById(R.id.radio_button_two_three);
        mRadioButtonTwoFour = (RadioButton) findViewById(R.id.radio_button_two_four);

        //RadioButtons question four
        mRadioGroupAnswerFour = (RadioGroup) findViewById(R.id.radio_group_four);
        mRadioButtonFourOne = (RadioButton) findViewById(R.id.radio_button_four_one);
        mRadioButtonFourTwo = (RadioButton) findViewById(R.id.radio_button_four_two);
        mRadioButtonFourThree = (RadioButton) findViewById(R.id.radio_button_four_three);
        mRadioButtonFourFour = (RadioButton) findViewById(R.id.radio_button_four_four);

        //sets only the welcome window visible if app starts
        mCardWelcomeWindow.setVisibility(View.VISIBLE);
        mCardQuestionOne.setVisibility(View.GONE);
        mCardQuestionTwo.setVisibility(View.GONE);
        mCardQuestionThree.setVisibility(View.GONE);
        mCardQuestionFour.setVisibility(View.GONE);
        mCardStatistics.setVisibility(View.GONE);

        //gets the variables if the orientation is changed
        if (savedInstanceState != null) {
            mAnswerOne = savedInstanceState.getBoolean(ANSWER_ONE);
            mAnswerOneOne = savedInstanceState.getBoolean(ANSWER_ONE_ONE);
            mAnswerOneTwo = savedInstanceState.getBoolean(ANSWER_ONE_TWO);
            mAnswerOneThree = savedInstanceState.getBoolean(ANSWER_ONE_THREE);
            mAnswerOneFour = savedInstanceState.getBoolean(ANSWER_ONE_FOUR);
            mAnswerTwo = savedInstanceState.getBoolean(ANSWER_TWO);
            mAnswerThree = savedInstanceState.getBoolean(ANSWER_THREE);
            mAnswerFour = savedInstanceState.getBoolean(ANSWER_FOUR);
            mScoreCounter = savedInstanceState.getInt(SCORE_COUNTER);
            mQuestionCounter = savedInstanceState.getInt(QUESTION_COUNTER);
            mStatisticText = savedInstanceState.getString(STATISTIC_TEXT);


            //clear all windows
            mCardWelcomeWindow.setVisibility(View.GONE);
            mCardQuestionOne.setVisibility(View.GONE);
            mCardQuestionTwo.setVisibility(View.GONE);
            mCardQuestionThree.setVisibility(View.GONE);
            mCardQuestionFour.setVisibility(View.GONE);
            mCardStatistics.setVisibility(View.GONE);

            //creates the actual window
            switch (mQuestionCounter) {
                case 0:
                    mCardWelcomeWindow.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    mCardQuestionOne.setVisibility(View.VISIBLE);
                    mCheckBoxAnswerOneOne.setChecked(mAnswerOneOne);
                    mCheckBoxAnswerOneTwo.setChecked(mAnswerOneTwo);
                    mCheckBoxAnswerOneThree.setChecked(mAnswerOneThree);
                    mCheckBoxAnswerOneFour.setChecked(mAnswerOneFour);
                    break;
                case 2:
                    mCardQuestionTwo.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    mCardQuestionThree.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    mCardQuestionFour.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    mCardStatistics.setVisibility(View.VISIBLE);
                    mStatisticView.setText(mStatisticText);
                    break;
                default:
            }


        }


    }

    //save status of variables in case of orientation change
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(ANSWER_ONE, mAnswerOne);
        savedInstanceState.putBoolean(ANSWER_ONE_ONE, mAnswerOneOne);
        savedInstanceState.putBoolean(ANSWER_ONE_TWO, mAnswerOneTwo);
        savedInstanceState.putBoolean(ANSWER_ONE_THREE, mAnswerOneThree);
        savedInstanceState.putBoolean(ANSWER_ONE_FOUR, mAnswerOneFour);
        savedInstanceState.putBoolean(ANSWER_TWO, mAnswerTwo);
        savedInstanceState.putBoolean(ANSWER_THREE, mAnswerThree);
        savedInstanceState.putBoolean(ANSWER_FOUR, mAnswerFour);
        savedInstanceState.putInt(SCORE_COUNTER, mScoreCounter);
        savedInstanceState.putInt(QUESTION_COUNTER, mQuestionCounter);
        savedInstanceState.putString(STATISTIC_TEXT, mStatisticText);

        super.onSaveInstanceState(savedInstanceState);
    }


    public void buttonSubmitWelcome(View v) {
        //Welcome window will start
        mUserName = mEditTextInputUserName.getText().toString();
        Log.e("inputText: ", mUserName);

        if (!mUserName.equals("")) {

            mCardWelcomeWindow.setVisibility(View.GONE);
            mCardQuestionOne.setVisibility(View.VISIBLE);
            mQuestionCounter++;
            toastMessage(getString(R.string.toast_mother_thanks_you, mUserName));


        } else {
            toastMessage(getString(R.string.toast_mother_needs_your_name));
        }

    }

    public void buttonSubmitOne(View v) {

        //Checkboxes 1,2,4 are the correct answer cause only the cat is on board of the nostromo
        checkBoxAnswerOne();
        mCardQuestionOne.setVisibility(View.GONE);
        mCardQuestionTwo.setVisibility(View.VISIBLE);

    }


    public void buttonSubmitTwo(View v) {
        //radio buttons check
        checkAnswerTwo();
        mCardQuestionTwo.setVisibility(View.GONE);
        mCardQuestionThree.setVisibility(View.VISIBLE);
        mQuestionCounter++;

    }

    public void buttonSubmitThree(View v) {
        //checks answer three
        mAnswerInputThree = mEditTextInputAnswerThree.getText().toString();
        Log.e("inputText: ", mAnswerInputThree);

        if (!mAnswerInputThree.equals("")) {
            checkAnswerThree();
            mCardQuestionThree.setVisibility(View.GONE);
            mCardQuestionFour.setVisibility(View.VISIBLE);
            mQuestionCounter++;

        } else {
            toastMessage(getString(R.string.toast_mother_needs_sign));
        }


    }


    public void buttonSubmitFour(View v) {
        //last question radio buttons
        checkAnswerFour();
        mCardQuestionFour.setVisibility(View.GONE);
        mQuestionCounter++;
        //gets the statistics of the game
        mStatisticView.setText(makeStatistic());
        mCardStatistics.setVisibility(View.VISIBLE);
        toastMessage(makeStatistic());


    }

    public void buttonReset(View v) {
        //resets and starts new
        mCardStatistics.setVisibility(View.GONE);
        mCardWelcomeWindow.setVisibility(View.VISIBLE);
        clearAllVariables();
    }


    public void onCheckboxClicked(View v) {

        mAnswerOneOne = mCheckBoxAnswerOneOne.isChecked();
        mAnswerOneTwo = mCheckBoxAnswerOneTwo.isChecked();
        mAnswerOneThree = mCheckBoxAnswerOneThree.isChecked();
        mAnswerOneFour = mCheckBoxAnswerOneFour.isChecked();

    }


    public void checkBoxAnswerOne() {


        if (!mCheckBoxAnswerOneThree.isChecked()) {
            if (mCheckBoxAnswerOneOne.isChecked() &&
                    mCheckBoxAnswerOneTwo.isChecked() && mCheckBoxAnswerOneFour.isChecked()) {
                mScoreCounter++;
                mAnswerOne = true;
                toastMessage(getString(R.string.mother_says, mScoreCounter));
            } else {
                toastMessage(getString(R.string.mother_is_angry, mScoreCounter));
            }
        } else {
            toastMessage(getString(R.string.mother_is_angry, mScoreCounter));
        }

        mQuestionCounter++;


    }


    public void checkAnswerTwo() {

        RadioButton checkedRadioButton = (RadioButton) findViewById(mRadioGroupAnswerTwo.getCheckedRadioButtonId());
        if (checkedRadioButton == mRadioButtonTwoOne) {

            mScoreCounter++;
            mAnswerTwo = true;
            toastMessage(getString(R.string.mother_says, mScoreCounter));

        } else {
            toastMessage(getString(R.string.mother_is_angry, mScoreCounter));
        }
    }


    public void checkAnswerThree() {

        if (mAnswerInputThree.equalsIgnoreCase(getString(R.string.mother))) {
            mScoreCounter++;
            mAnswerThree = true;
            toastMessage(getString(R.string.mother_says, mScoreCounter));
        } else {
            toastMessage(getString(R.string.mother_is_angry, mScoreCounter));
        }

    }

    public void checkAnswerFour() {

        RadioButton checkedRadioButton = (RadioButton) findViewById(mRadioGroupAnswerFour.getCheckedRadioButtonId());
        if (checkedRadioButton == mRadioButtonFourThree) {

            mScoreCounter++;
            mAnswerFour = true;
            toastMessage(getString(R.string.mother_says, mScoreCounter));

        } else {
            toastMessage(getString(R.string.mother_is_angry, mScoreCounter));
        }
    }


    public void clearAllVariables() {
        mUserName = "";
        mScoreCounter = 0;
        mQuestionCounter = 0;
        mAnswerOne = false;
        mAnswerOneOne = false;
        mAnswerOneTwo = false;
        mAnswerOneThree = false;
        mAnswerOneFour = false;
        mAnswerTwo = false;
        mAnswerThree = false;
        mAnswerFour = false;
        mCheckBoxAnswerOneOne.setChecked(false);
        mCheckBoxAnswerOneTwo.setChecked(false);
        mCheckBoxAnswerOneThree.setChecked(false);
        mCheckBoxAnswerOneFour.setChecked(false);
        mRadioGroupAnswerTwo.clearCheck();
        mRadioGroupAnswerFour.clearCheck();
        mEditTextInputUserName.setText("");
        mEditTextInputAnswerThree.setText("");


    }

    public String makeStatistic() {

        mStatisticText = getString(R.string.congratulation, mUserName);
        mStatisticText += getString(R.string.question1, mAnswerOne, computeScore(mAnswerOne));
        mStatisticText += getString(R.string.question2, mAnswerTwo, computeScore(mAnswerTwo));
        mStatisticText += getString(R.string.question3, mAnswerThree, computeScore(mAnswerThree));
        mStatisticText += getString(R.string.question4, mAnswerFour, computeScore(mAnswerFour));
        mStatisticText += getString(R.string.result, mScoreCounter);
        mStatisticText += getString(R.string.mother_is_proud);

        return mStatisticText;
    }

    public int computeScore(boolean answer) {

        int score;
        if (answer) {
            score = 1;
        } else {
            score = 0;
        }
        return score;
    }

    public void toastMessage(String message) {
        //Creates a individual toast message with its own style
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.tost_message,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(message);


        Context c = this;
        Toast toast = Toast.makeText(c,
                message,
                Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

    }


}
