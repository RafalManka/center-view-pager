/***
 Copyright (c) 2012 CommonsWare, LLC
 Licensed under the Apache License, Version 2.0 (the "License"); you may not
 use this file except in compliance with the License. You may obtain a copy
 of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
 by applicable law or agreed to in writing, software distributed under the
 License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 OF ANY KIND, either express or implied. See the License for the specific
 language governing permissions and limitations under the License.

 Covered in detail in the book _The Busy Coder's Guide to Android Development_
 https://commonsware.com/Android
 */

package com.commonsware.android.mvp1;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.commonsware.android.mvp1.centrviewpager.CenterViewPager;
import com.commonsware.android.mvp1.centrviewpager.PagerAdapter;

import java.util.Random;


public class MainActivity extends Activity {

    Random rnd = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CenterViewPager pager = (CenterViewPager) findViewById(R.id.pager);
        pager.setAdapter(new SampleAdapter());

        pager.setPageMargin(50);
        pager.enableCenterLockOfChilds();
        pager.setCurrentItemInCenter(0);
    }

    /*
     * Inspired by
     * https://gist.github.com/8cbe094bb7a783e37ad1
     */
    private class SampleAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View page = getLayoutInflater().inflate(R.layout.page, container, false);
            TextView tv = (TextView) page.findViewById(R.id.text);
            //int blue = position * 25;

            final String msg = String.format(getString(R.string.item), position + 1);

            tv.setText(msg);
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG)
                            .show();
                }
            });

            page.setBackgroundColor(randomColor());
            container.addView(page);

            return (page);
        }

        private int randomColor() {
            return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return (9);
        }

        @Override
        public float getPageWidth(int position) {
            return (0.85f);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }


}
