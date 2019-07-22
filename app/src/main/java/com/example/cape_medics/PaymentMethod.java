package com.example.cape_medics;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

public class PaymentMethod extends Fragment {
    JSONObject paymentMethod;
    TextView receipt, name, plan, number, member;
    EditText receiptEdit, nameEdit, planEdit, numberEdit, memberEdit;
    CheckBox medicalaid, account, cash, credit, gop;

    public PaymentMethod(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_payment_method,container,false);

        paymentMethod = new JSONObject();

        medicalaid = view.findViewById(R.id.medicalaidChk);
        account = view.findViewById(R.id.accountChk);
        cash = view.findViewById(R.id.cashChk);
        credit = view.findViewById(R.id.creditChk);
        gop = view.findViewById(R.id.gopChk);

        receipt = view.findViewById(R.id.receipt);
        name = view.findViewById(R.id.name);
        plan = view.findViewById(R.id.plan);
        number = view.findViewById(R.id.number);
        member = view.findViewById(R.id.member);

        receiptEdit = view.findViewById(R.id.receiptEdit);
        nameEdit = view.findViewById(R.id.nameEdit);
        planEdit = view.findViewById(R.id.planEdit);
        numberEdit = view.findViewById(R.id.numberEdit);
        memberEdit = view.findViewById(R.id.memberEdit);

        medicalaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cash.isChecked()){
                    cash.setChecked(false);
                }
                if(credit.isChecked()){
                    credit.setChecked(false);
                }
                if(account.isChecked()){
                    account.setChecked(false);
                }
                if(gop.isChecked()){
                    gop.setChecked(false);
                }

                if(medicalaid.isChecked()){
                    name.setVisibility(View.VISIBLE);
                    nameEdit.setVisibility(View.VISIBLE);

                    member.setVisibility(View.VISIBLE);
                    memberEdit.setVisibility(View.VISIBLE);

                    number.setVisibility(View.VISIBLE);
                    numberEdit.setVisibility(View.VISIBLE);

                    plan.setVisibility(View.VISIBLE);
                    planEdit.setVisibility(View.VISIBLE);

                }
                else{
                    name.setVisibility(View.INVISIBLE);
                    nameEdit.setVisibility(View.INVISIBLE);

                    member.setVisibility(View.INVISIBLE);
                    memberEdit.setVisibility(View.INVISIBLE);

                    number.setVisibility(View.INVISIBLE);
                    numberEdit.setVisibility(View.INVISIBLE);

                    plan.setVisibility(View.INVISIBLE);
                    planEdit.setVisibility(View.INVISIBLE);
                }
            }
        });

        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (medicalaid.isChecked()){
                    medicalaid.setChecked(false);
                }
                if(credit.isChecked()){
                    credit.setChecked(false);
                }
                if(account.isChecked()){
                    account.setChecked(false);
                }
                if(gop.isChecked()){
                    gop.setChecked(false);
                }

                if(cash.isChecked()){
                    receipt.setVisibility(View.VISIBLE);
                    receiptEdit.setVisibility(View.VISIBLE);
                }
                else{
                    receipt.setVisibility(View.INVISIBLE);
                    receiptEdit.setVisibility(View.INVISIBLE);
                }
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cash.isChecked()){
                    cash.setChecked(false);
                }
                if(credit.isChecked()){
                    credit.setChecked(false);
                }
                if(medicalaid.isChecked()){
                    medicalaid.setChecked(false);
                }
                if(gop.isChecked()){
                    gop.setChecked(false);
                }

                if(account.isChecked()){
                    name.setVisibility(View.VISIBLE);
                    nameEdit.setVisibility(View.VISIBLE);

                    member.setVisibility(View.VISIBLE);
                    memberEdit.setVisibility(View.VISIBLE);

                    number.setVisibility(View.VISIBLE);
                    numberEdit.setVisibility(View.VISIBLE);

                    plan.setVisibility(View.VISIBLE);
                    planEdit.setVisibility(View.VISIBLE);

                }
                else{
                    name.setVisibility(View.INVISIBLE);
                    nameEdit.setVisibility(View.INVISIBLE);

                    member.setVisibility(View.INVISIBLE);
                    memberEdit.setVisibility(View.INVISIBLE);

                    number.setVisibility(View.INVISIBLE);
                    numberEdit.setVisibility(View.INVISIBLE);

                    plan.setVisibility(View.INVISIBLE);
                    planEdit.setVisibility(View.INVISIBLE);
                }
            }
        });



        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cash.isChecked()){
                    cash.setChecked(false);
                }
                if(account.isChecked()){
                    account.setChecked(false);
                }
                if(medicalaid.isChecked()){
                    medicalaid.setChecked(false);
                }
                if(gop.isChecked()){
                    gop.setChecked(false);
                }

                if(credit.isChecked()){
                    receipt.setVisibility(View.VISIBLE);
                    receiptEdit.setVisibility(View.VISIBLE);
                }
                else{
                    receipt.setVisibility(View.INVISIBLE);
                    receiptEdit.setVisibility(View.INVISIBLE);
                }
            }
        });

        gop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cash.isChecked()){
                    cash.setChecked(false);
                }
                if(credit.isChecked()){
                    credit.setChecked(false);
                }
                if(medicalaid.isChecked()){
                    medicalaid.setChecked(false);
                }

                if(account.isChecked()){
                    account.setChecked(false);
                }

                if(gop.isChecked()){
                    name.setVisibility(View.VISIBLE);
                    nameEdit.setVisibility(View.VISIBLE);

                    member.setVisibility(View.VISIBLE);
                    memberEdit.setVisibility(View.VISIBLE);

                    number.setVisibility(View.VISIBLE);
                    numberEdit.setVisibility(View.VISIBLE);

                    plan.setVisibility(View.VISIBLE);
                    planEdit.setVisibility(View.VISIBLE);

                }
                else{
                    name.setVisibility(View.INVISIBLE);
                    nameEdit.setVisibility(View.INVISIBLE);

                    member.setVisibility(View.INVISIBLE);
                    memberEdit.setVisibility(View.INVISIBLE);

                    number.setVisibility(View.INVISIBLE);
                    numberEdit.setVisibility(View.INVISIBLE);

                    plan.setVisibility(View.INVISIBLE);
                    planEdit.setVisibility(View.INVISIBLE);
                }
            }
        });
        return view;
    }

    public JSONObject Send(View v){
        paymentMethod = new JSONObject();

        if (medicalaid.isChecked()){
            try{
                paymentMethod.put("Medical Aid",  medicalaid.getText().toString());
                paymentMethod.put("Name",  nameEdit);
                paymentMethod.put("Plan",  planEdit);
                paymentMethod.put("Receipt Number",  receiptEdit);
                paymentMethod.put("Main Member",  memberEdit);
            }catch(Exception e){}
        }
        if (account.isChecked()) {
            try{
                paymentMethod.put("Account",  account.getText().toString());
                paymentMethod.put("Name",  nameEdit);
                paymentMethod.put("Plan",  planEdit);
                paymentMethod.put("Receipt Number",  receiptEdit);
                paymentMethod.put("Main Member",  memberEdit);
            }catch(Exception e){}
        }
        if (cash.isChecked()){
            try{
                paymentMethod.put("Cash",  cash.getText().toString());
                paymentMethod.put("Receipt Number",  receiptEdit);
            }catch(Exception e){}
        }
        if (credit.isChecked()){
            try{
                paymentMethod.put("Credit Card/EFT",  credit.getText().toString());
                paymentMethod.put("Receipt Number",  receiptEdit);
            }catch(Exception e){}
        }
        if (gop.isChecked()){
            try{
                paymentMethod.put("G.O.P",  gop.getText().toString());
                paymentMethod.put("Name",  nameEdit);
                paymentMethod.put("Plan",  planEdit);
                paymentMethod.put("Receipt Number",  receiptEdit);
                paymentMethod.put("Main Member",  memberEdit);
            }catch(Exception e){}
        }

        return paymentMethod;
    }






}
