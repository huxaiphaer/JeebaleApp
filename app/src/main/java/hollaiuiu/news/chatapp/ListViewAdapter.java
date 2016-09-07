package hollaiuiu.news.chatapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import huzykamz.haj.laty.jeebaleapp.R;


public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ImageLoader imageLoader;
    private List<WorldPopulation> worldpopulationlist = null;
    private ArrayList<WorldPopulation> arraylist;

    public ListViewAdapter(Context context,
                           List<WorldPopulation> worldpopulationlist) {
        this.context = context;
        this.worldpopulationlist = worldpopulationlist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<WorldPopulation>();
        this.arraylist.addAll(worldpopulationlist);
        imageLoader = new ImageLoader(context);
    }

    public class ViewHolder {
        TextView rank;
        TextView headlines;
        TextView details;
        ImageView flag;
        TextView date;
        String data;
    }

    @Override
    public int getCount() {
        return worldpopulationlist.size();
    }

    @Override
    public Object getItem(int position) {
        return worldpopulationlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
// Locate the TextViews in listview_item.xml


            holder.headlines = (TextView) view.findViewById(R.id.headlines);
            holder.details = (TextView) view.findViewById(
                    R.id.details);
            holder.date =(TextView) view.findViewById(R.id.date);
// Locate the ImageView in listview_item.xml
            holder.flag = (ImageView) view.findViewById(R.id.flag);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
// Set the results into TextViews

        holder.headlines.setText(worldpopulationlist.get(position).getHeadlines());
        holder.details.setText(worldpopulationlist.get(position).getDetails());
        holder.date.setText(worldpopulationlist.get(position).getDate());

// Set the results into ImageView
        imageLoader.DisplayImage(worldpopulationlist.get(position).getFlag(),
                holder.flag);
// Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
// Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, SingleItemView.class);
// Pass all data rank

                intent.putExtra("HeadLines",
                        (worldpopulationlist.get(position).getHeadlines()));
// Pass all data population
                intent.putExtra("Details",
                        (worldpopulationlist.get(position).getDetails()));
// Pass all data flag
                intent.putExtra("flag",
                        (worldpopulationlist.get(position).getFlag()));
// Start SingleItemView Class
                context.startActivity(intent);

                intent.putExtra("date",
                        (worldpopulationlist.get(position).getFlag()));
// Start SingleItemView Class
                context.startActivity(intent);



            }
        });



        return view;
    }

}