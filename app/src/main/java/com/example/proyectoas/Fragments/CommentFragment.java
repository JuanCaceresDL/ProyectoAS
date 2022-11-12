package com.example.proyectoas.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoas.Adapter.comAdapter;
import com.example.proyectoas.Bean.Comentarios;
import com.example.proyectoas.Presenter.IPresenterCom;
import com.example.proyectoas.Presenter.PresenterCom;
import com.example.proyectoas.R;
import com.example.proyectoas.View.IComentView;
import com.example.proyectoas.databinding.FragmentCommentBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommentFragment extends Fragment implements IComentView, View.OnClickListener {
    Integer mId;
    private FragmentCommentBinding commentBinding;
    private comAdapter adapter;
    private IPresenterCom presenterCom = new PresenterCom(this);

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CommentFragment() {
        // Required empty public constructor
    }

    public CommentFragment(Integer mId) {
        this.mId = mId;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CommentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CommentFragment newInstance(String param1, String param2) {
        CommentFragment fragment = new CommentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        adapter = new comAdapter(new ArrayList<>());
        commentBinding = FragmentCommentBinding.inflate(getLayoutInflater());
        RecyclerView listacom = commentBinding.recyclercomment;
        listacom.setAdapter(adapter);
        listacom.setLayoutManager(new LinearLayoutManager(getContext()));
        presenterCom.getComent(mId);

        commentBinding.comentario.setOnClickListener(this);
        return commentBinding.getRoot();


    }

    @Override
    public void onComentSuccess(List<Comentarios> comentarios) {
        adapter.reloadData(comentarios);
    }

    @Override
    public void onComentError(String msg) {
        Toast.makeText(getContext(), "Aún no hay comentarios", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {

                AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
                alert.setTitle("comentario");
                alert.show();
                EditText comentario = new EditText(getActivity());
                alert.setView(comentario);
                alert.setButton(AlertDialog.BUTTON_NEGATIVE, "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();                    }
                        });

    }
}