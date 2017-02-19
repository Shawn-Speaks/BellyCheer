package view;

import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

import model.Rows;
import shawn.c4q.nyc.bellycheer.R;

public class PantryVH extends RecyclerView.ViewHolder {
    TextView name;
    TextView address;
    TextView cityStateZip;
    TextView phone;
    TextView hoursOfOperation;
    TextView description;


    public PantryVH(final View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
        address = (TextView) itemView.findViewById(R.id.address);
        cityStateZip = (TextView) itemView.findViewById(R.id.city_state_zip);
        phone = (TextView) itemView.findViewById(R.id.phone);
        hoursOfOperation = (TextView) itemView.findViewById(R.id.hours_of_operation);
        description = (TextView) itemView.findViewById(R.id.description);
    }

    public void onBind(Rows rows) {
        description.setText(rows.getBriefdescription());
        name.setText(rows.getName());
        address.setText(rows.getStreetaddress());
        cityStateZip.setText(rows.getCity() + ", " + rows.getState() + " " + Integer.toString(rows.getZipcode()));
        Linkify.addLinks(address, Linkify.ALL);
        Linkify.addLinks(cityStateZip, Linkify.ALL);
        phone.setText(rows.getPhonenumber());
        Linkify.addLinks(phone, Linkify.PHONE_NUMBERS);
        hoursOfOperation.setText(rows.getHoursofoperation());

    }

}
