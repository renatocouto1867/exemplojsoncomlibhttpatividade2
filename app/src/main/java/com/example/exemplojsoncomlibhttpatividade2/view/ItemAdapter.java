package com.example.exemplojsoncomlibhttpatividade2.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.exemplojsoncomlibhttpatividade2.R;
import com.example.exemplojsoncomlibhttpatividade2.entity.Aluno;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ItemAdapter extends BaseAdapter {
    private final Context context;
    private final List<Aluno> registroList;

    public ItemAdapter(Context context, List<Aluno> registroList) {
        this.context = context;
        this.registroList = registroList;
    }

    @Override
    public int getCount() {
        return registroList.size();
    }

    @Override
    public Object getItem(int i) {
        return registroList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.aprovados_item, viewGroup, false);
        }

        TextView idTextView = convertView.findViewById(R.id.sequencia);
        TextView nomeTextView = convertView.findViewById(R.id.nomeAluno);
        TextView frequenciaTextView = convertView.findViewById(R.id.frequencia);
        TextView mediaTextView = convertView.findViewById(R.id.media);

        Aluno currentItem = (Aluno) getItem(i);

        idTextView.setText(String.valueOf(i+1));
        nomeTextView.setText(currentItem.getNome());
        String valorFormatado= String.format("%.2f",currentItem.getFrenquenciaMedia());
        frequenciaTextView.setText(valorFormatado);
        mediaTextView.setText(String.format("%.2f",currentItem.getMedia()));


        return convertView;
    }
}
