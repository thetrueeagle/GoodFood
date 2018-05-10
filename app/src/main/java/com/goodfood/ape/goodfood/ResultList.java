package com.goodfood.ape.goodfood;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ResultList extends AppCompatActivity {


    private String TAG = ResultList.class.getSimpleName();

    private ProgressDialog pDialog;
    private ArrayList<Result> recipeList = new ArrayList<>();



    // URL to get recipes JSON

    private String url;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        ImageView left = findViewById(R.id.left_nav);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.arrowScroll(View.FOCUS_LEFT);
            }
        });
        ImageView right = findViewById(R.id.right_nav);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.arrowScroll(View.FOCUS_RIGHT);
            }
        });


        Intent intent = getIntent();
        url = intent.getStringExtra("URL");

        new GetRecipes().execute();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber, ArrayList<Result> resultList) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();

            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putSerializable("recipeList", resultList);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_results, container, false);
            TextView textView = rootView.findViewById(R.id.title);
            ImageView image = rootView.findViewById(R.id.image);
            ListView ingredients = rootView.findViewById(R.id.ingredients);
            Button btnInstructions = rootView.findViewById(R.id.instructions);
            final ArrayList<Result> results =  (ArrayList<Result>) getArguments().getSerializable("recipeList");
            int size = results.size();
            if(size==0){
                image.setImageResource(R.drawable.sadface);
                textView.setText("Sorry, no results found!");
                btnInstructions.setVisibility(View.INVISIBLE);

            }
            else {
                final int number = getArguments().getInt(ARG_SECTION_NUMBER);
                if(number==1){
                    Toast.makeText(getContext(), "Swipe to see more", Toast.LENGTH_LONG).show();
                }
                textView.setText(results.get(number - 1).getTitle());
                String imageURL = results.get(number - 1).getImageUrl();
                Picasso.with(getActivity()).load(imageURL).into(image);
                String[] ingredientsList = results.get(number - 1).getIngredients();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, ingredientsList);
                // Set The Adapter
                ingredients.setAdapter(adapter);

                final Uri uri = Uri.parse(results.get(number - 1).getInstructionUrl()); // missing 'http://' will cause crashed

                btnInstructions.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });

                final MyDBHandler db = new MyDBHandler(getContext(), null, null, 1);
                final FloatingActionButton favouriteBtn = rootView.findViewById(R.id.favouriteButton);
                if (db.checkRecipe(results.get(number - 1), 0)) {
                    favouriteBtn.setImageResource(android.R.drawable.btn_star_big_on);
                }
                favouriteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //add recipe to favourite list

                        Drawable.ConstantState d1 = ContextCompat.getDrawable(getContext(), android.R.drawable.btn_star_big_off).getConstantState();
                        Drawable.ConstantState d2 = ContextCompat.getDrawable(getContext(), android.R.drawable.btn_star_big_on).getConstantState();
                        Drawable actual = favouriteBtn.getDrawable();
                        if (actual.getConstantState().equals(d1)) {

                            db.addRecipe(results.get(number - 1), 0);

                            favouriteBtn.setImageResource(android.R.drawable.btn_star_big_on);
                            Toast.makeText(getContext(), "Recipe added to \"Favourites\"", Toast.LENGTH_LONG).show();
                        }
                        if (actual.getConstantState().equals(d2)) {

                            db.deleteRecipe(results.get(number - 1), 0);

                            favouriteBtn.setImageResource(android.R.drawable.btn_star_big_off);
                            Toast.makeText(getContext(), "Recipe removed from \"Favourites\"", Toast.LENGTH_LONG).show();
                        }

                    }
                });


                final FloatingActionButton doneBtn = rootView.findViewById(R.id.doneButton);
                if (db.checkRecipe(results.get(number - 1), 1)) {
                    doneBtn.setImageResource(android.R.drawable.checkbox_on_background);
                }
                doneBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //add recipe to favourite list

                        Drawable.ConstantState d1 = ContextCompat.getDrawable(getContext(), android.R.drawable.checkbox_off_background).getConstantState();
                        Drawable.ConstantState d2 = ContextCompat.getDrawable(getContext(), android.R.drawable.checkbox_on_background).getConstantState();
                        Drawable actual = doneBtn.getDrawable();
                        if (actual.getConstantState().equals(d1)) {

                            db.addRecipe(results.get(number - 1), 1);
                            db.updateRecipeCount(1);

                            doneBtn.setImageResource(android.R.drawable.checkbox_on_background);
                            Toast.makeText(getContext(), "Recipe added to \"Done\"", Toast.LENGTH_LONG).show();
                        }
                        if (actual.getConstantState().equals(d2)) {

                            db.deleteRecipe(results.get(number - 1), 1);
                            db.updateRecipeCount(1);

                            doneBtn.setImageResource(android.R.drawable.checkbox_off_background);
                            Toast.makeText(getContext(), "Recipe removed from \"Done\"", Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }



            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1, recipeList);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            if(recipeList.size()==0){
                return 1;
            }
            else {
                return recipeList.size();
            }
        }
    }




    /**
     * Async task class to get json by making HTTP call
     */
    private class GetRecipes extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ResultList.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            //Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject result = new JSONObject(jsonStr);
                    JSONArray hits = result.getJSONArray("hits");



                        // looping through contents
                        for (int i = 0; i < hits.length(); i++) {
                            JSONObject contents = hits.getJSONObject(i);
                            JSONObject c = contents.getJSONObject("recipe");
                            String uri = c.getString("uri");
                            String title = c.getString("label");
                            String image = c.getString("image");

                            // Ingredients node is JSON Object
                            JSONArray ingredients = c.getJSONArray("ingredientLines");

                            String[] ingredientsList = new String[ingredients.length()];
                            for (int a = 0; a < ingredientsList.length; a++) {
                                ingredientsList[a] = ingredients.getString(a);
                            }

                            String instructionUrl = c.getString("url");


                            Result recipe = new Result(uri, title, image, ingredientsList, instructionUrl);


                            // adding contact to contact list
                            recipeList.add(recipe);

                    }
                } catch (final JSONException e) {
                    //Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                //Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */

            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);


        }


    }
}
