package com.dmitrysukhov.pseudonymgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etNameSurname;
    TextView resultTextView;
    TextView countOfVowels;
    TextView countOfConsonants;
    String nameAndSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNameSurname = findViewById(R.id.etNameSurname);
        resultTextView = findViewById(R.id.resultTextView);
        countOfVowels = findViewById(R.id.countOfVowels);
        countOfConsonants = findViewById(R.id.countOfConsonants);
    }

    public void onClick(View view) {
        nameAndSurname = etNameSurname.getText().toString();
        if (!nameAndSurname.isEmpty()) {
            generatePseudonym(nameAndSurname);
        }
    }

    public void generatePseudonym(String nameAndSurname) {
        if (nameAndSurname.trim().length() > 0) {
            boolean nextIsVowel = false;
            int randomPosition;
            StringBuilder allOurVowels = new StringBuilder();
            StringBuilder allOurConsonants = new StringBuilder();
            StringBuilder resultPseudonym = new StringBuilder();
            String numberOfVowels;
            String numberOfConsonants;

            String[] allNameWords = nameAndSurname.split(" ");
            char[] allChars = nameAndSurname.toCharArray();

            for (char currentChar : allChars) {
                if (isVowel(currentChar)) allOurVowels.append(currentChar);
                else if (currentChar != ' ') allOurConsonants.append(currentChar);
            }

            numberOfVowels = "Кол-во гласных букв: " + allOurVowels.length();
            numberOfConsonants = "Кол-во согласных букв: " + allOurConsonants.length();

            while (allOurVowels.toString().length() != 0 | allOurConsonants.toString().length() != 0) {

                if (nextIsVowel) {
                    if (allOurVowels.toString().length() != 0) {
                        randomPosition = (int) (Math.random() * allOurVowels.toString().length());
                        resultPseudonym.append(allOurVowels.charAt(randomPosition));
                        allOurVowels.deleteCharAt(randomPosition);
                    }
                    nextIsVowel = false;
                } else {
                    if (allOurConsonants.toString().length() != 0) {
                        randomPosition = (int) (Math.random() * allOurConsonants.toString().length());
                        resultPseudonym.append(allOurConsonants.charAt(randomPosition));
                        allOurConsonants.deleteCharAt(randomPosition);
                    }
                    nextIsVowel = true;
                }
            }
            resultPseudonym.setCharAt(0, Character.toUpperCase(resultPseudonym.charAt(0)));
            for (int i = 1; i < resultPseudonym.toString().length(); i++) {
                resultPseudonym.setCharAt(i, Character.toLowerCase(resultPseudonym.charAt(i)));
            }
            if (allNameWords.length > 1) {
                resultPseudonym.insert(allNameWords[0].length(), ' ');
                resultPseudonym.setCharAt(allNameWords[0].length() + 1,
                        Character.toUpperCase(resultPseudonym.charAt(allNameWords[0].length() + 1)));
            }
            resultTextView.setText(resultPseudonym);
            countOfVowels.setText(numberOfVowels);
            countOfConsonants.setText(numberOfConsonants);
        }
    }

    public static boolean isVowel(char c) {
        return "AEIOUaeiouАОУЫИЕЭЯЮЁаоуыиеэяюё".indexOf(c) != -1;
    }
}

