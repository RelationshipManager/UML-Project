package com.example.zhang.relationshipManager.fragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhang.relationshipManager.R;
import com.example.zhang.relationshipManager.models.Person;
import com.example.zhang.relationshipManager.models.PersonManager;
import com.example.zhang.relationshipManager.models.RelationshipManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 10040 on 2017/6/12.
 */

public class AddRelationshipFragment extends DialogFragment {

    @BindView(R.id.tv_source_person_name)
    public TextView mSourcePersonName;
    @BindView(R.id.et_target_person_name)
    public EditText mTargetPersonName;
    @BindView(R.id.spinner_source_person_role)
    public Spinner mSourcePersonRole;
    @BindView(R.id.spinner_target_person_role)
    public Spinner mTargetPersonRole;
    @BindView(R.id.bt_confirm)
    public Button mBtConfirm;
;

    Activity mActivity;
    Person mPerson;

    public AddRelationshipFragment setAttri(Activity activity,Person person){
        mActivity=activity;
        mPerson=person;
        return this;
    }

    public void show(String tag) {
        if(mActivity==null||mPerson==null)
            return;
        FragmentManager fm=mActivity.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag(tag);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        super.show(ft, tag);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.add_relationship_dialog,container);
        ButterKnife.bind(this,view);
        init();
        return view;
    }

    private void init(){
        mSourcePersonName.setText(mPerson.getName());
        ArrayList<String> roles= RelationshipManager.getInstance(getActivity()).getAllRelationshipRole();
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),R.layout.support_simple_spinner_dropdown_item,roles);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mSourcePersonRole.setAdapter(adapter);
        mTargetPersonRole.setAdapter(adapter);
        mBtConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person targetPerson=PersonManager.getInstance(getActivity()).getPersonByName(mTargetPersonName.getText().toString());
                if(RelationshipManager.getInstance(getActivity()).addRelationship(mPerson,targetPerson,
                        (String)mSourcePersonRole.getSelectedItem(),
                        (String)mTargetPersonRole.getSelectedItem())){
                    Toast.makeText(getActivity(),"添加成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),"添加失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
