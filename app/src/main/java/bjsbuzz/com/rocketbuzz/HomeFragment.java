package bjsbuzz.com.rocketbuzz;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.toolbox.JsonObjectRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import bjsbuzz.com.rocketbuzz.volly.AppController;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.ProgressDialog;

public class HomeFragment extends Fragment {

    private static String TAG = MainActivity.class.getSimpleName();
    private String jsonResponse;
    private ProgressDialog pDialog;
    private CardRecyclerView mRecyclerView = null;

    public HomeFragment() {
        // Required empty public constructor
    }

    private String urlJsonObj = "http://vast-coast-8555.herokuapp.com/";

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        makeJsonObjectRequest();
        mCardArrayAdapter.notifyDataSetChanged();
    }

    private void makeJsonObjectRequest() {


        // showpDialog();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
                urlJsonObj, (String) null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    String items = response.getString("items");
                    String titles = response.getString("title");
                    String descriptions = response.getString("description");
                    String author_names = response.getString("author_name");

                    String[] title;
                    String[] description;
                    String[] author_name;
                    title = titles.split(",");
                    description = descriptions.split(",");
                    author_name = author_names.split(",");


                    jsonResponse = "";
                    jsonResponse += "Titles: " + title.toString() + "\n\n";
                    jsonResponse += "Descriptions : " + description.toString() + "\n\n";
                    jsonResponse += "Author Names : " + author_name.toString() + "\n\n";
                    jsonResponse += "Itesm : " + items + "\n\n";


                    cards = new ArrayList<Card>();
                    //Create a Card
                    Card card = null;
                    CardThumbnail thumb = null;
                    for (int i = 0; i < title.length; i++) {
                        card = new Card(getActivity().getApplication().getApplicationContext());

                        card.setTitle(title[i]);
                        card.setCardElevation(10);
                        card.setShadow(true);
                        cards.add(card);
                    }

                    mCardArrayAdapter.setCards(cards);

                    Log.d(TAG, jsonResponse);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                //  hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                //  hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //  Log.d("BC", cards.toString());
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);


        cards = new ArrayList<Card>();
        //Create a Card
        Card card = null;
        CardThumbnail thumb = null;
        for (int i = 0; i < 10; i++) {
            card = new Card(getActivity().getApplication().getApplicationContext());

            card.setTitle("" + i);
            card.setCardElevation(10);
            card.setShadow(true);
            cards.add(card);
        }


    }

   /* private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }*/

   /* private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }*/

    CardArrayRecyclerViewAdapter mCardArrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        mCardArrayAdapter = new CardArrayRecyclerViewAdapter(getActivity(), cards);


        //Staggered grid view
        mRecyclerView = (CardRecyclerView) rootView.findViewById(R.id.carddemo_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        //Set the empty view
        if (mRecyclerView != null) {

            mRecyclerView.setAdapter(mCardArrayAdapter);

        }


        // Inflate the layout for this fragment
        return rootView;
    }

    ArrayList<Card> cards;

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}