package com.example.cape_medics.TableLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.cape_medics.R;

public class Row implements AdapterView.OnItemSelectedListener {

    private String description;
    private String qty;
    private String checked;
    private String comment;

    private String commentHint;
    private final Context context;
    private final String[] qtySpinnerItems;
    private int position;
    private int parentWidth;
    private TableRow row;

    public Row(Context context, String description, String qty, String[] qtySpinnerItems, String checked, String comment, String commentHint, int position, int parentWidth)
    {
        this.context = context;
        this.description = description;
        this.qty = qty;
        this.qtySpinnerItems = qtySpinnerItems;
        this.checked = checked;
        this.comment = comment;
        this.commentHint = commentHint;
        this.position = position;
        this.parentWidth = parentWidth;
        createView();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentHint() {
        return commentHint;
    }

    public void setCommentHint(String commentHint) {
        this.commentHint = commentHint;
    }

    private static final int WrapContent = ViewGroup.LayoutParams.WRAP_CONTENT;
    private static final int MatchParent = ViewGroup.LayoutParams.MATCH_PARENT;


    private void createView()
    {
        row = new TableRow(context);

        // create heading LayoutParams
        TableRow.LayoutParams rowParams = new TableRow.LayoutParams(MatchParent, dpToPixels(40,context));
        //rowParams.weight = 1;

        row.setLayoutParams(rowParams);
        row.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);

        //int color = position % 2 == 0 ? Color.parseColor("#ffff8800") : Color.parseColor("#ff0045");
        //row.setBackgroundColor(color);

        // create response TextView

        TextView response = new TextView(context);
        response.setGravity(Gravity.CENTER_VERTICAL);
        TableRow.LayoutParams responseParams = new TableRow.LayoutParams(parentWidth * 7/20, MatchParent);

        response.setText(this.description);
        response.setTextColor(Color.WHITE);
        response.setAllCaps(true);
        response.setBackgroundResource(R.drawable.cell_shape);
        response.setTypeface(Typeface.DEFAULT_BOLD);
        row.addView(response, responseParams);

        // create qty TextView

        EditText qty = new EditText(context);
        //TextView qty = new TextView(context);
        qty.setGravity(Gravity.CENTER_VERTICAL);

        TableRow.LayoutParams qtyParams = new TableRow.LayoutParams(parentWidth * 2/20, MatchParent);

        //qty.setInputType(InputType.TYPE_CLASS_NUMBER);
        qty.setText(this.qty);
        qty.setTextColor(Color.WHITE);
        qty.setAllCaps(true);
        qty.setGravity(Gravity.CENTER);
        qty.setBackgroundResource(R.drawable.cell_shape);
        qty.setTypeface(Typeface.DEFAULT_BOLD);
        qty.setFocusable(true);
        qty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setQty(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        row.addView(qty,qtyParams);

        // create checked Spinner

        Spinner spinner = new Spinner(context);
        spinner.setGravity(Gravity.CENTER_VERTICAL);

        TableRow.LayoutParams spinnerParams = new TableRow.LayoutParams(parentWidth * 4/20, MatchParent);
        spinner.setBackgroundResource(R.drawable.cell_shape);


        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context,R.layout.custom_spinner, qtySpinnerItems);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);
        //spinner.setPadding(3,0,3,0);
        row.addView(spinner, spinnerParams);


        // create qty EditText

        final TextView comment = new TextView(context);
        comment.setGravity(Gravity.CENTER_VERTICAL);

        TableRow.LayoutParams commentParams = new TableRow.LayoutParams(parentWidth * 7/20, MatchParent);
        comment.setText(this.comment);
        comment.setTextColor(Color.WHITE);
        comment.setAllCaps(true);
        comment.setEllipsize(TextUtils.TruncateAt.END);
        comment.setBackgroundResource(R.drawable.cell_shape);
        comment.setFocusable(true);
        //comment.setPadding(3,0,3,0);
        comment.setTypeface(Typeface.DEFAULT_BOLD);

        comment.setOnClickListener(v -> {
            commentDialog(this.comment,"Ok", (dialog, textView, which) -> {
                setComment(textView.getText().toString());
                comment.setText(textView.getText().toString());
            }, "Cancel", (dialog, which) -> dialog.cancel()).show();
        });

        row.addView(comment, commentParams);
    }

    private AlertDialog.Builder commentDialog(String initialComment, String positiveButtonText, CommentClickListener positiveButton, String negativeButtonText, DialogInterface.OnClickListener negativeButton)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add Comment");

        // Set up the input
        final EditText input = new EditText(context);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setText(initialComment);
        input.setHint(this.commentHint);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton(positiveButtonText, (dialog, which) -> positiveButton.onClick(dialog, input, which));
        builder.setNegativeButton(negativeButtonText, negativeButton);

        return builder;

    }

    public TableRow getRow()
    {
        return row;
    }

    public static int dpToPixels(float dp, Context context)
    {
         DisplayMetrics DisplayMetrics = context.getResources().getDisplayMetrics();

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, DisplayMetrics);
    }

    public static DisplayMetrics getDisplayMatrix(Context context)
    {
        return  context.getResources().getDisplayMetrics();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        checked = (String)parent.getItemAtPosition(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private interface CommentClickListener
    {
        void onClick(final DialogInterface dialog, final TextView textView, final int which);
    }
}
