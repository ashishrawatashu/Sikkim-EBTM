package in.nic.snt.starbus.ebtm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import in.nic.snt.starbus.ebtm.R;


public class SliderAdapter extends PagerAdapter{
        Context context;
        LayoutInflater layoutInflater;

        public SliderAdapter(Context context) {
            this.context = context;
        }

        public int[] slideImage={
                R.drawable.snt_logo,
                R.drawable.snt_logo,
                R.drawable.snt_logo
        };

        public  String[] heading={
                "Assign Waybill",
                "Close Trip",
                "Content"
        };

        public String[] desc={
                "You can assign way to conductor after that conductor can start there trip ",
                "You can also close pending trip of waybill",
                "Content"
        };

        @Override
        public int getCount() {
            return heading.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=layoutInflater.inflate(R.layout.slide_view_layout_for_onboarding,container,false);
            ImageView slideImageView=view.findViewById(R.id.slideimg);
            TextView headingText=view.findViewById(R.id.slidehed);
            TextView descText=view.findViewById(R.id.slidedec);
            slideImageView.setImageResource(slideImage[position]);
            headingText.setText(heading[position]);
            descText.setText(desc[position]);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((ConstraintLayout) object);
        }

}
