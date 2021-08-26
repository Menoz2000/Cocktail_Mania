package com.example.cocktailmania.book;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cocktailmania.R;
import com.example.cocktailmania.utility.StepPrep;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class BuildStep extends AppCompatActivity implements View.OnClickListener{

    StepPrep passaggio;
    ChipGroup chipGroupIng;
    ChipGroup chipGroupStrum;
    Button pippoButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_step);

        chipGroupIng=findViewById(R.id.AddIngGroup);
        chipGroupStrum=findViewById(R.id.AddStrumGroup);

        pippoButton=findViewById(R.id.PippoButton);
        pippoButton.setOnClickListener(this);

        for(int i=0;i<MyCocktail.MyIngredients.size();i++){
            Chip chip=new Chip(this);
            chip.setText(MyCocktail.MyIngredients.get(i).getIngName());
            chip.setCheckable(true);
            chipGroupIng.addView(chip);
        }
        chipGroupIng.setVisibility(View.VISIBLE);

        for(int i=0;i<MyCocktail.MyStrums.size();i++){
            Chip chip=new Chip(this);
            chip.setText(MyCocktail.MyStrums.get(i).getNome());
            chip.setCheckable(true);
            chipGroupStrum.addView(chip);
        }
        chipGroupStrum.setVisibility(View.VISIBLE);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.PippoButton:

                System.out.println(chipGroupIng.getCheckedChipIds());

                break;
            default:
                break;
        }
    }
}