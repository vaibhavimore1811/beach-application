package com.sanika.beachapplication.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.sanika.beachapplication.R;
import com.sanika.beachapplication.activity.OnBoardingscreen;
import com.sanika.beachapplication.constance.AppLocaleLanguage;
import com.sanika.beachapplication.constance.SharedPref;

public class LanguageFragment extends Fragment implements View.OnClickListener {

    private RadioButton radioEnglish;
    private RadioButton radioHindi;
    private RadioButton radioMarathi;
    private RadioButton radioArabic;
    private RadioButton radioSpanish;
    private RadioButton radioBengali;
    private RadioButton radioTamil;
    private RadioButton radioMalayalam;
    AppCompatButton btnContinue;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_language, container, false);
        // Initialize RadioButtons
        sharedPreferences = SharedPref.getSharedPref(requireActivity());

        radioEnglish = view.findViewById(R.id.radioEnglish);
        radioHindi = view.findViewById(R.id.radioHindi);
        radioMarathi = view.findViewById(R.id.radioMarathi);
        radioArabic = view.findViewById(R.id.radioArabic);
        radioSpanish = view.findViewById(R.id.radioSpanish);
        radioBengali = view.findViewById(R.id.radioBengali);
        radioTamil = view.findViewById(R.id.radioTamil);
        radioMalayalam = view.findViewById(R.id.radioMalayalam);
        btnContinue = view.findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(this);
        radioEnglish.setOnClickListener(this);
        radioHindi.setOnClickListener(this);
        radioMarathi.setOnClickListener(this);
        radioArabic.setOnClickListener(this);
        radioSpanish.setOnClickListener(this);
        radioBengali.setOnClickListener(this);
        radioTamil.setOnClickListener(this);
        radioMalayalam.setOnClickListener(this);
        String language = SharedPref.getString(sharedPreferences, SharedPref.locale);
        if(!language.isEmpty())
        {
           if(language.contains("ml"))
           {
               SelectedLanguageMalayalam();
           }else if(language.contains("ta"))
           {
               SelectedLanguageTamil();
           }else if(language.contains("bn"))
           {
               SelectedLanguageBengali();
           }else if(language.contains("es"))
           {
               SelectedLanguageSpanish();
           }else if(language.contains("ar"))
           {
               SelectedLanguageArabic();
           }else if(language.contains("mr"))
           {
               SelectedLanguageMarathi();
           }else if(language.contains("hi"))
           {
               SelectedLanguageHindi();
           }else if(language.contains("en"))
           {
               SelectedLanguageEnglish();
           }else{
               SelectedLanguageEnglish();

        }
        }
        return view;


    }

    @Override
    public void onClick(View v) {
        if (v == btnContinue) {
            CheckIsLanguageSelected();
        }else   if (v == radioMalayalam) {
            SelectedLanguageMalayalam();
        }else   if (v == radioTamil) {
            SelectedLanguageTamil();
        }else   if (v == radioBengali) {
            SelectedLanguageBengali();
        }else   if (v == radioSpanish) {
            SelectedLanguageSpanish();
        }else   if (v == radioArabic) {
            SelectedLanguageArabic();
        }else   if (v == radioMarathi) {
            SelectedLanguageMarathi();
        }else   if (v == radioHindi) {
            SelectedLanguageHindi();
        }else   if (v == radioEnglish) {
            SelectedLanguageEnglish();
        }
    }
    private void SelectedLanguageEnglish() {

        radioMalayalam.setChecked(false);
        radioTamil.setChecked(false);
        radioBengali.setChecked(false);
        radioSpanish.setChecked(false);
        radioArabic.setChecked(false);
        radioMarathi.setChecked(false);
        radioHindi.setChecked(false);
        radioEnglish.setChecked(true);
        AppLocaleLanguage.setApplicationLocale(requireActivity(),"en");
        String text = requireContext().getString(R.string.continue_);
        btnContinue.setText(text);
        btnContinue.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_green));
    }
    private void SelectedLanguageHindi() {

        radioMalayalam.setChecked(false);
        radioTamil.setChecked(false);
        radioBengali.setChecked(false);
        radioSpanish.setChecked(false);
        radioArabic.setChecked(false);
        radioMarathi.setChecked(false);
        radioHindi.setChecked(true);
        radioEnglish.setChecked(false);
        AppLocaleLanguage.setApplicationLocale(requireActivity(),"hi");
        String text = requireContext().getString(R.string.continue_);
        btnContinue.setText(text);
        btnContinue.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_green));
    }
    private void SelectedLanguageMarathi() {

        radioMalayalam.setChecked(false);
        radioTamil.setChecked(false);
        radioBengali.setChecked(false);
        radioSpanish.setChecked(false);
        radioArabic.setChecked(false);
        radioMarathi.setChecked(true);
        radioHindi.setChecked(false);
        radioEnglish.setChecked(false);
        AppLocaleLanguage.setApplicationLocale(requireActivity(),"mr");
        String text = requireContext().getString(R.string.continue_);
        btnContinue.setText(text);
        btnContinue.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_green));
    }
    private void SelectedLanguageArabic() {

        radioMalayalam.setChecked(false);
        radioTamil.setChecked(false);
        radioBengali.setChecked(false);
        radioSpanish.setChecked(false);
        radioArabic.setChecked(true);
        radioMarathi.setChecked(false);
        radioHindi.setChecked(false);
        radioEnglish.setChecked(false);
        AppLocaleLanguage.setApplicationLocale(requireActivity(),"ar");
        String text = requireContext().getString(R.string.continue_);
        btnContinue.setText(text);
        btnContinue.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_green));
    }
    private void SelectedLanguageSpanish() {

        radioMalayalam.setChecked(false);
        radioTamil.setChecked(false);
        radioBengali.setChecked(false);
        radioSpanish.setChecked(true);
        radioArabic.setChecked(false);
        radioMarathi.setChecked(false);
        radioHindi.setChecked(false);
        radioEnglish.setChecked(false);
        AppLocaleLanguage.setApplicationLocale(requireActivity(),"es");
        String text = requireContext().getString(R.string.continue_);
        btnContinue.setText(text);
        btnContinue.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_green));
    }
    private void SelectedLanguageBengali() {

        radioMalayalam.setChecked(false);
        radioTamil.setChecked(false);
        radioBengali.setChecked(true);
        radioSpanish.setChecked(false);
        radioArabic.setChecked(false);
        radioMarathi.setChecked(false);
        radioHindi.setChecked(false);
        radioEnglish.setChecked(false);
        AppLocaleLanguage.setApplicationLocale(requireActivity(),"bn");
        String text = requireContext().getString(R.string.continue_);
        btnContinue.setText(text);
        btnContinue.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_green));
    }

    private void SelectedLanguageTamil() {

        radioMalayalam.setChecked(false);
        radioTamil.setChecked(true);
        radioBengali.setChecked(false);
        radioSpanish.setChecked(false);
        radioArabic.setChecked(false);
        radioMarathi.setChecked(false);
        radioHindi.setChecked(false);
        radioEnglish.setChecked(false);
        AppLocaleLanguage.setApplicationLocale(requireActivity(),"ta");
        String text = requireContext().getString(R.string.continue_);
        btnContinue.setText(text);
        btnContinue.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_green));
    }
    private void SelectedLanguageMalayalam() {

        radioMalayalam.setChecked(true);
        radioTamil.setChecked(false);
        radioBengali.setChecked(false);
        radioSpanish.setChecked(false);
        radioArabic.setChecked(false);
        radioMarathi.setChecked(false);
        radioHindi.setChecked(false);
        radioEnglish.setChecked(false);
        AppLocaleLanguage.setApplicationLocale(requireActivity(),"ml");
        String text = requireContext().getString(R.string.continue_);
        btnContinue.setText(text);
        btnContinue.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_green));
    }


    private void CheckIsLanguageSelected() {
        String language = SharedPref.getString(sharedPreferences, SharedPref.locale);
        if(language.isEmpty())
        {
            Toast.makeText(getActivity(), getResources().getString(R.string.selected_language_error), Toast.LENGTH_SHORT).show();
        }else {
            SharedPreferences pref = requireActivity().getSharedPreferences("myPrefs",MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isIntroOpnend",false);
            editor.commit();

            Intent i = new Intent(requireActivity(), OnBoardingscreen.class);
            startActivity(i);
            // finish();
        }
    }
}