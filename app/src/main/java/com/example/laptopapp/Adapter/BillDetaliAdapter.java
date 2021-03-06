package com.example.laptopapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.laptopapp.Model.Bill;
import com.example.laptopapp.Model.BillDetail;
import com.example.laptopapp.R;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class BillDetaliAdapter extends BaseAdapter {
    private ArrayList<BillDetail> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    public BillDetaliAdapter(Context aContext, ArrayList<BillDetail> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        // ViewHolder heheh;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_productbill, null);
            holder = new ViewHolder();
            holder.product_id = (TextView) convertView.findViewById(R.id.nameproductbill);
            holder.billdetail_price = (TextView) convertView.findViewById(R.id.total_price_billdetali);
            holder.Bill_date = (TextView) convertView.findViewById(R.id.datebilldetail);
            holder.Bill_quanliti = (TextView) convertView.findViewById(R.id.quanlity_billdetali);
            holder.Bill_quanlitiy = (TextView) convertView.findViewById(R.id.quanlity_billdetaliy);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        BillDetail bill = this.listData.get(position);
        Date temp_date = null;
        String Date = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            temp_date = sdf.parse(bill.getCreated_at());
            Date = output.format(temp_date);
        }catch (Exception e){

        }
      holder.product_id.setText("M?? s???n ph???m : " + bill.getProduct_id());
      holder.billdetail_price.setText("T???ng gi?? : " + NumberFormat.getNumberInstance(Locale.US).format(bill.getLast_price()));
      holder.Bill_date.setText("Ng??y : " + Date);
      holder.Bill_quanliti.setText("S??? l?????ng : " + bill.getQuantity());
        return convertView;
    }
    static class ViewHolder {
        TextView product_id,billdetail_price,Bill_date, Bill_quanliti;
    }
}


