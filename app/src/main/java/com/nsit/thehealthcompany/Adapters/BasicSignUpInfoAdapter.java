package com.nsit.thehealthcompany.Adapters;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nsit.thehealthcompany.DTO.UserBasicInformationDTO;
import com.nsit.thehealthcompany.R;
import com.nsit.thehealthcompany.Retrofit.Calls.SignUpWithBasicInfo;

public class BasicSignUpInfoAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ViewPager basicInfoViewPager;
    private LinearLayout maleLinearLayout;
    private LinearLayout femaleLinearLayout;
    private TextView nextTextView;
    private String toogleFlag;
    private TextView previousTextView;
    private String gender;
    private WeightClass weightClass;
    private HeightClass heightClass;
    private AgeClass ageClass;
    private GoalClass goalClass;
    private int id;
    private String email;

    private int getCurrentPage(){
        return basicInfoViewPager.getCurrentItem();
    }

    private void setUpBasicInformation(){
        UserBasicInformationDTO userBasicInformationDTO = new UserBasicInformationDTO();
        if (toogleFlag.contentEquals("male")){
            userBasicInformationDTO.setGender("M");
        }else if(toogleFlag.contentEquals("female")){
            userBasicInformationDTO.setGender("F");
        }

        userBasicInformationDTO.setID(id);
        userBasicInformationDTO.setAge(Integer.parseInt(ageClass.getAge()));
        userBasicInformationDTO.setHeight(Integer.parseInt(heightClass.getHeight()));
        userBasicInformationDTO.setWeight(Integer.parseInt(weightClass.getWeight()));
        userBasicInformationDTO.setGoal(goalClass.getGoal());

        System.out.println("Object to be send : "+ userBasicInformationDTO.toString());

        SignUpWithBasicInfo signUpWithBasicInfo = new SignUpWithBasicInfo(context, email, userBasicInformationDTO);
        signUpWithBasicInfo.execute();

    }

    private View createPreviousTextView(){
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParams.setMarginStart(30);
        TextView previousTextView = new TextView(context);
        previousTextView.setText("Previous");
        previousTextView.setTypeface(ResourcesCompat.getFont(context, R.font.encode_sans_expanded_medium));
        previousTextView.setLayoutParams(layoutParams);
        previousTextView.setTextSize(20);
        previousTextView.setTextColor(Color.WHITE);
        previousTextView.setGravity(Gravity.CENTER_VERTICAL);
        previousTextView.setPadding(10, 10, 10, 10);
        return previousTextView;
    }

    private void setClickListenerOnNext(){
        nextTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RelativeLayout bottomLayout = (RelativeLayout) nextTextView.getParent();
                basicInfoViewPager.setCurrentItem(getCurrentPage() + 1, true);
                if (getCurrentPage() == 1) {
                    previousTextView = (TextView) createPreviousTextView();
                    previousTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            basicInfoViewPager.setCurrentItem(getCurrentPage() - 1, true);
                            if (getCurrentPage() == 0){
                                bottomLayout.removeView(previousTextView);
                            }
                            if (getCurrentPage() ==1 || getCurrentPage()==0){
                                nextTextView.setText("Next");
                            }
                        }
                    });
                    bottomLayout.addView(previousTextView);
                }
                else{
                    nextTextView.setText("Next");
                }
            }
        });
    }

    private void setClickListenerOnImageView(){

        maleLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toogleFlag.contentEquals("female") || toogleFlag.isEmpty()){
                    maleLinearLayout.setAlpha(1);
                    femaleLinearLayout.setAlpha(0.3F);
                    toogleFlag = "male";

                    if(!nextTextView.isClickable()){
                        setClickListenerOnNext();
                    }
                }

            }
        });

        femaleLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toogleFlag.contentEquals("male") || toogleFlag.isEmpty()){
                    femaleLinearLayout.setAlpha(1);
                    maleLinearLayout.setAlpha(0.3F);
                    toogleFlag = "female";

                    if(!nextTextView.isClickable()){
                        setClickListenerOnNext();
                    }
                }
            }
        });
    }

    public BasicSignUpInfoAdapter(Context context, ViewPager basicInfoViewPager, TextView nextTextView, int id, String email){
        this.context  = context;
        this.basicInfoViewPager = basicInfoViewPager;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.nextTextView = nextTextView;
        toogleFlag = "";
        this.id = id;
        this.email=email;
        nextTextView.setClickable(false);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View itemView = layoutInflater.inflate(R.layout.gender_selection_screen,container,false);

        switch (position){
            case 0 :
                itemView = layoutInflater.inflate(R.layout.gender_selection_screen,container,false);

                maleLinearLayout = itemView.findViewById(R.id.maleLinearLayout);
                femaleLinearLayout = itemView.findViewById(R.id.femaleLinearLayout);
                maleLinearLayout.setAlpha(0.3F);
                femaleLinearLayout.setAlpha(0.3F);
                setClickListenerOnImageView();

                break;

            case 1 :
                itemView = layoutInflater.inflate(R.layout.basic_info_sign_up,container,false);
                weightClass = new WeightClass(itemView);
                heightClass = new HeightClass(itemView);
                ageClass = new AgeClass(itemView);
                break;

            case 2 :
                itemView = layoutInflater.inflate(R.layout.activity_goal_screen,container,false);

                LinearLayout healthierOptionLayout = itemView.findViewById(R.id.healthierOptionLayout);
                LinearLayout loseWeightOptionLayout = itemView.findViewById(R.id.loseWeightOptionLayout);
                LinearLayout gainWeightOptionLayout = itemView.findViewById(R.id.gainWeightOptionLayout);

                goalClass = new GoalClass(null);

                healthierOptionLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goalClass.setGoal("BH");
                        setUpBasicInformation();

                    }
                });

                loseWeightOptionLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goalClass.setGoal("LW");
                        setUpBasicInformation();
                    }
                });

                gainWeightOptionLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goalClass.setGoal("GW");
                        setUpBasicInformation();
                    }
                });

                break;

        }
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public String setGender(){
        return gender;
    }

    class WeightClass{

        View itemView;
        private long totalWeight;
        TextView weightTextView;

        WeightClass(View itemView){
            this.itemView = itemView;
            ImageView addWeightImageViewIcon = itemView.findViewById(R.id.addWeightImageViewIcon);
            ImageView minusWeightImageViewIcon = itemView.findViewById(R.id.minusWeightImageViewIcon);
            weightTextView = itemView.findViewById(R.id.weightTextView);
            totalWeight = 60;
            weightTextView.setText(String.valueOf(totalWeight));

            addWeightImageViewIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    totalWeight = Integer.parseInt(weightTextView.getText().toString());
                    totalWeight += 1;
                    weightTextView.setText(String.valueOf(totalWeight));
                }
            });

            minusWeightImageViewIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    totalWeight = Integer.parseInt(weightTextView.getText().toString());
                    if (totalWeight>10){
                        totalWeight -= 1;
                        weightTextView.setText(String.valueOf(totalWeight));
                    }
                }
            });
        }

        public String getWeight(){
            return weightTextView.getText().toString();
        }


    }

    class HeightClass{

        View itemView;
        private long height;
        TextView heightTextView;

        HeightClass(View itemView){
            this.itemView = itemView;
            ImageView addHeightImageViewIcon = itemView.findViewById(R.id.addHeightImageViewIcon);
            ImageView minusHeightImageViewIcon = itemView.findViewById(R.id.minusHeightImageViewIcon);
            heightTextView = itemView.findViewById(R.id.heightTextView);
            height = 160;
            heightTextView.setText(String.valueOf(height));

            addHeightImageViewIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    height = Integer.parseInt(heightTextView.getText().toString());
                    height += 1;
                    heightTextView.setText(String.valueOf(height));
                }
            });

            minusHeightImageViewIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    height = Integer.parseInt(heightTextView.getText().toString());
                    if (height>40){
                        height -= 1;
                        heightTextView.setText(String.valueOf(height));
                    }
                }
            });
        }

        public String getHeight(){
            return heightTextView.getText().toString();
        }


    }

    class AgeClass{

        View itemView;
        private int age;
        TextView ageTextView;

        AgeClass(View itemView){
            this.itemView = itemView;
            ImageView addAgeImageViewIcon = itemView.findViewById(R.id.addAgeImageViewIcon);
            ImageView minusAgeImageViewIcon = itemView.findViewById(R.id.minusAgeImageViewIcon);
            ageTextView = itemView.findViewById(R.id.ageTextView);
            age = 30;
            ageTextView.setText(String.valueOf(age));

            addAgeImageViewIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    age = Integer.parseInt(ageTextView.getText().toString());
                    age += 1;
                    ageTextView.setText(String.valueOf(age));
                }
            });

            minusAgeImageViewIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    age = Integer.parseInt(ageTextView.getText().toString());
                    if (age>1){
                        age -= 1;
                        ageTextView.setText(String.valueOf(age));
                    }
                }
            });
        }

        public String getAge(){
            return ageTextView.getText().toString();
        }


    }

    class GoalClass{

        String goal;

        GoalClass(String goal){
            this.goal = goal;
        }

        public String getGoal() {
            return goal;
        }

        public void setGoal(String goal) {
            this.goal = goal;
        }
    }

}
