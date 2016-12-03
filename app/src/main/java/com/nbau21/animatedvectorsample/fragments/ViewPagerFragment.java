package com.nbau21.animatedvectorsample.fragments;

import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.nbau21.animatedvectorsample.R;

public class ViewPagerFragment extends Fragment {

    public ViewPagerFragment() {
    }

    public static ViewPagerFragment newInstance() {
        return new ViewPagerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        final ImageView ivSpider = (ImageView) view.findViewById(R.id.iv_spider);

        ivSpider.bringToFront();
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

            private int counter = 0;
            private final String[] COLORS = {"#C5E1A5", "#FFCC80", "#BCAAA4", "#B39DDB", "#EEEEEE"};
            private final String[] TEXTS = {"Vector Animations in a ViewPager", "Welcome! I am a spider.", "I can move while I teach you how to use this app!", "See ya!", "I'm watching you."};

            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public Fragment getItem(int position) {
                DummyFragment dummyFragment = new DummyFragment();
                dummyFragment.setBackground(Color.parseColor(COLORS[counter]));
                dummyFragment.setDummyText(TEXTS[counter]);
                counter++;
                return dummyFragment;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (position == 0) {
                    Animation animZipAwaySpider = AnimationUtils.loadAnimation(getActivity(), R.anim.spider_zip_away);
                    ivSpider.startAnimation(animZipAwaySpider);
                } else if (position == 1) {
                    Animation animDropSpider = AnimationUtils.loadAnimation(getActivity(), R.anim.spider_drop);
                    animDropSpider.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            ((Animatable) (ivSpider.getDrawable())).start();
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Animation animInitialSwingSpider = AnimationUtils.loadAnimation(getActivity(), R.anim.spider_initial_swing);
                            animInitialSwingSpider.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    Animation animSwingSpider = AnimationUtils.loadAnimation(getActivity(), R.anim.spider_swing);
                                    ivSpider.startAnimation(animSwingSpider);

                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                            ivSpider.startAnimation(animInitialSwingSpider);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    ivSpider.startAnimation(animDropSpider);
                } else if (position == 2) {
                    Animation animZipBackSpider = AnimationUtils.loadAnimation(getActivity(), R.anim.spider_zip_halfway);
                    ivSpider.startAnimation(animZipBackSpider);
                } else if (position == 3) {
                    Animation animSwingToSideSpider = AnimationUtils.loadAnimation(getActivity(), R.anim.spider_swing_to_side);
                    ivSpider.startAnimation(animSwingToSideSpider);
                } else if (position == 4) {
                    ((Animatable) (ivSpider.getDrawable())).start();
                    Animation animCrawlAwaySpider = AnimationUtils.loadAnimation(getActivity(), R.anim.spider_crawl_away);
                    ivSpider.startAnimation(animCrawlAwaySpider);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        return view;
    }
}
