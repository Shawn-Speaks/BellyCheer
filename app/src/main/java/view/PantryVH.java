package view;

import android.support.v7.widget.RecyclerView;
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

    public PantryVH(final View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
        address = (TextView) itemView.findViewById(R.id.address);
        cityStateZip = (TextView) itemView.findViewById(R.id.city_state_zip);
        phone = (TextView) itemView.findViewById(R.id.phone);
        hoursOfOperation = (TextView) itemView.findViewById(R.id.hours_of_operation);
    }

    public void onBind(Rows rows) {
        name.setText(rows.getName());
        address.setText(rows.getStreetaddress());
        cityStateZip.setText(rows.getCity() + ", " + rows.getState() + " " + Integer.toString(rows.getZipcode()));
        phone.setText(rows.getPhonenumber());
        hoursOfOperation.setText(rows.getHoursofoperation());
    }

}
