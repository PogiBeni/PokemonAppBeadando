package com.example.pokemonapp.nav;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pokemonapp.R;
import com.example.pokemonapp.classes.PokemonQuestion;
import com.example.pokemonapp.classes.PokemonQuiz;
import com.example.pokemonapp.classes.StaticDataOuter;

import java.util.Random;

public class HomeNav extends Fragment {
    public PokemonQuiz pQ;
    Random rnd;
    Button btnAnswer1;
    Button btnAnswer2;
    Button btnAnswer3;
    Button btnAnswer4;
    TextView txtScore;
    ProgressBar pb;
    ImageView imageViewHome;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_nav, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        btnAnswer1 = view.findViewById(R.id.button1);
        btnAnswer2 = view.findViewById(R.id.button2);
        btnAnswer3 = view.findViewById(R.id.button3);
        btnAnswer4 = view.findViewById(R.id.button4);
        txtScore = view.findViewById(R.id.textViewScore);
        imageViewHome = view.findViewById(R.id.imageViewHome);
        textView = view.findViewById(R.id.textViewName);
        pb = view.findViewById(R.id.progressBarHome);
        rnd = new Random();

        Thread timer = new Thread() {
            @Override
            public void run() {
                while(StaticDataOuter.StaticData.pokemonList.size() != 99){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                pQ = new PokemonQuiz(StaticDataOuter.StaticData.pokemonList,StaticDataOuter.StaticData.listFile);

                getActivity().runOnUiThread(() -> setButtons(pQ));
                getActivity().runOnUiThread(() -> pb.setVisibility(View.GONE));
                getActivity().runOnUiThread(() -> imageViewHome.setVisibility(View.VISIBLE));

                btnAnswer1.setOnClickListener(View -> clickEvent(btnAnswer1));
                btnAnswer2.setOnClickListener(View -> clickEvent(btnAnswer2));
                btnAnswer3.setOnClickListener(View -> clickEvent(btnAnswer3));
                btnAnswer4.setOnClickListener(View -> clickEvent(btnAnswer4));
            }
        };
        timer.start();
    }
    public void clickEvent(Button pressedButton)
    {
        boolean result = pQ.checkAnswer(pressedButton.getText().toString());
        Toast.makeText(getView().getContext(), "The given answer is " + result, Toast.LENGTH_SHORT).show();

        if(result) setButtons(pQ);
        else txtScore.setText(pQ.getPointCounter() +"/5");
    }
    public void setButtons(PokemonQuiz pokemonQuiz) {
        pokemonQuiz.getRandomQuestion();
        View view = getView();
        PokemonQuestion question = pokemonQuiz.getCurrentQuestion();


        textView.setText(pokemonQuiz.getCurrentQuestion().question);
        txtScore.setText(pokemonQuiz.getPointCounter() +"/5");

        int goodAnswerPlace = rnd.nextInt(4);
        btnAnswer1.setText(question.wrongAnswers.get(0));
        btnAnswer2.setText(question.wrongAnswers.get(1));
        btnAnswer3.setText(question.wrongAnswers.get(2));
        btnAnswer4.setText(question.wrongAnswers.get(3));

        switch (goodAnswerPlace) {
            case 0:
                btnAnswer1.setText(question.goodAnswer);
                break;
            case 1:
                btnAnswer2.setText(question.goodAnswer);
                break;
            case 2:
                btnAnswer3.setText(question.goodAnswer);
                break;
            case 3:
                btnAnswer4.setText(question.goodAnswer);
                break;
        }
        Glide.with(view).load(question.url).into(imageViewHome);
    }
}