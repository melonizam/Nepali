package com.tutorials.hp.navviewrecyclerview.Recycleradapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tutorials.hp.navviewrecyclerview.Datacontainer;
import com.tutorials.hp.navviewrecyclerview.R;

import java.util.ArrayList;

    public class Recycleradapter extends RecyclerView.Adapter<Recycleradapter.Holder> {
        public Clicklistener clicklistener;
        Context context;
        LayoutInflater inflater;
        ArrayList<Datacontainer> mylist;

        public interface Clicklistener {
            void itemclick(View view, String link);
        }

        public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageView image;
            TextView title;
            TextView linkText;

            public Holder(View itemView) {
                super(itemView);
                this.title = (TextView) itemView.findViewById(R.id.textView);
                this.image = (ImageView) itemView.findViewById(R.id.imageView);
                this.linkText = (TextView) itemView. findViewById(R.id.linkText);
                itemView.setOnClickListener(this);
            }

            public void onClick(View v) {
                if (Recycleradapter.this.clicklistener != null) {
                    Recycleradapter.this.clicklistener.itemclick(v,mylist.get(getAdapterPosition()).link);
                }
            }
        }

        public Recycleradapter(Context context, ArrayList<Datacontainer> mylist) {
            this.context = context;
            this.mylist = mylist;
            this.inflater = LayoutInflater.from(context);
        }

        public Holder onCreateViewHolder(ViewGroup parent, int i) {
            return new Holder(this.inflater.inflate(R.layout.list, parent, false));
        }

        public void onBindViewHolder(Holder holder, int i) {
            Datacontainer datacontainer = (Datacontainer) this.mylist.get(i);
            holder.title.setText(datacontainer.title);
            holder.image.setImageResource(datacontainer.image);
            holder.linkText.setText(datacontainer.link);
        }

        public void setClicklistener(Clicklistener clicklistener) {
            this.clicklistener = clicklistener;
        }

        public int getItemCount() {
            return this.mylist.size();
        }


}
