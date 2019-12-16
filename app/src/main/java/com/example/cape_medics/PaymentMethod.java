package com.example.cape_medics;

import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentMethod extends Fragment {
    JSONObject paymentMethod;
    TextView receipt, name, plan, number, member;
    EditText receiptEdit, nameEdit, planEdit, numberEdit, memberEdit;
    CheckBox medicalaid, account, cash, credit, gop,chkNA;

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

        chkNA = view.findViewById( R.id.notApplicableCheckBox);
        chkNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkNA.isChecked()){
                    medicalTabbedView.viewPager.setCurrentItem(medicalTabbedView.current+1, true);
                }
            }
        });


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

                    receipt.setVisibility(View.INVISIBLE);
                    receiptEdit.setVisibility(View.INVISIBLE);

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

                    name.setVisibility(View.INVISIBLE);
                    nameEdit.setVisibility(View.INVISIBLE);

                    member.setVisibility(View.INVISIBLE);
                    memberEdit.setVisibility(View.INVISIBLE);

                    number.setVisibility(View.INVISIBLE);
                    numberEdit.setVisibility(View.INVISIBLE);

                    plan.setVisibility(View.INVISIBLE);
                    planEdit.setVisibility(View.INVISIBLE);
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
                    receipt.setVisibility(View.INVISIBLE);
                    receiptEdit.setVisibility(View.INVISIBLE);

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

                    name.setVisibility(View.INVISIBLE);
                    nameEdit.setVisibility(View.INVISIBLE);

                    member.setVisibility(View.INVISIBLE);
                    memberEdit.setVisibility(View.INVISIBLE);

                    number.setVisibility(View.INVISIBLE);
                    numberEdit.setVisibility(View.INVISIBLE);

                    plan.setVisibility(View.INVISIBLE);
                    planEdit.setVisibility(View.INVISIBLE);
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

                    receipt.setVisibility(View.INVISIBLE);
                    receiptEdit.setVisibility(View.INVISIBLE);

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

    public JSONObject createJson(){
        paymentMethod = new JSONObject();

        if(!chkNA.isChecked() && validate()) {
            if (medicalaid.isChecked()) {
                try {
                    paymentMethod.put("Medical Aid", medicalaid.getText().toString());
                    paymentMethod.put("Name", nameEdit.getText().toString());
                    paymentMethod.put("Plan", planEdit.getText().toString());
                    paymentMethod.put("Receipt Number", numberEdit.getText().toString());
                    paymentMethod.put("Main Member", memberEdit.getText().toString());
                } catch (Exception e) {
                }
            }
            if (account.isChecked()) {
                try {
                    paymentMethod.put("Account", account.getText().toString());
                    paymentMethod.put("Name", nameEdit.getText().toString());
                    paymentMethod.put("Plan", planEdit.getText().toString());
                    paymentMethod.put("Receipt Number", numberEdit.getText().toString());
                    paymentMethod.put("Main Member", memberEdit.getText().toString());
                } catch (Exception e) {
                }
            }
            if (cash.isChecked()) {
                try {
                    paymentMethod.put("Cash", cash.getText().toString());
                    paymentMethod.put("Receipt Number", receiptEdit.getText().toString());
                } catch (Exception e) {
                }
            }
            if (credit.isChecked()) {
                try {
                    paymentMethod.put("Credit Card/EFT", credit.getText().toString());
                    paymentMethod.put("Receipt Number", receiptEdit.getText().toString());
                } catch (Exception e) {
                }
            }
            if (gop.isChecked()) {
                try {
                    paymentMethod.put("G.O.P", gop.getText().toString());
                    paymentMethod.put("Name", nameEdit.getText().toString());
                    paymentMethod.put("Plan", planEdit.getText().toString());
                    paymentMethod.put("Receipt Number", numberEdit.getText().toString());
                    paymentMethod.put("Main Member", memberEdit.getText().toString());
                } catch (Exception e) {
                }
            }
        }else if(chkNA.isChecked()){
            try {
                paymentMethod.put("Status","Not applicable");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return paymentMethod;
    }


    public boolean validate(){
        boolean valid = true;
        Looper.prepare();
        if(medicalaid.isChecked() || gop.isChecked() ||account.isChecked()){

            if(nameEdit.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Please enter payment method details",Toast.LENGTH_SHORT).show();
            }

            if (valid){
                if(planEdit.getText().toString().isEmpty()){
                    valid = false;
                    Toast.makeText(getContext(),"Please enter payment method details",Toast.LENGTH_SHORT).show();
                }
            }


            if (valid){
                if(planEdit.getText().toString().isEmpty()){
                    valid = false;
                    Toast.makeText(getContext(),"Please enter payment method details",Toast.LENGTH_SHORT).show();
                }
            }


            if (valid){
                if(numberEdit.getText().toString().isEmpty()){
                    valid = false;
                    Toast.makeText(getContext(),"Please enter payment method details",Toast.LENGTH_SHORT).show();
                }

                if (valid){
                    if(memberEdit.getText().toString().isEmpty()){
                        valid = false;
                        Toast.makeText(getContext(),"Please enter payment method details",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

        if(cash.isChecked() || credit.isChecked()){
            if(receiptEdit.getText().toString().isEmpty()){
                valid = false;
                Toast.makeText(getContext(),"Please enter payment method details",Toast.LENGTH_SHORT).show();
            }
        }
        Looper.loop();
        return valid;
    }





}
