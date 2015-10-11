package bjsbuzz.com.rocketbuzz;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Log.d("BC", cards.toString());



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        CardArrayRecyclerViewAdapter mCardArrayAdapter = new CardArrayRecyclerViewAdapter(getActivity(), cards);

        //Staggered grid view
        CardRecyclerView mRecyclerView = (CardRecyclerView) rootView.findViewById(R.id.carddemo_recyclerview);
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
    public void onAttach(Activity activity) {

        super.onAttach(activity);

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        cards = new ArrayList<Card>();
        //Create a Card
        Card card = null;
        CardThumbnail thumb = null;
        for(int i=0; i < 20;i++) {
            card = new Card(getActivity().getApplication().getApplicationContext());

            card.setTitle(formattedDate + "'s Jobs");
            card.setCardElevation(10);
            card.setShadow(true);
            cards.add(card);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}