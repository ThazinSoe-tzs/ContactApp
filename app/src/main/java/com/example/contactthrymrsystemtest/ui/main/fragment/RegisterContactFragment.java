package com.example.contactthrymrsystemtest.ui.main.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.contactthrymrsystemtest.Contact;
import com.example.contactthrymrsystemtest.R;
import com.example.contactthrymrsystemtest.ui.main.model.PageViewModel;
import com.example.contactthrymrsystemtest.ui.main.database.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A Add_New_Contact fragment is to create new contact
 */
public class RegisterContactFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    private EditText firstNameEditText;

    private EditText lastNameEditText;

    private EditText phoneEditText;

    private EditText phone1EditText;

    private EditText companyEditText;

    private Button saveButton;

    private DBHelper dbHelper;

    private Contact contact = new Contact();

    private EditText dateOfBirth;

    private Calendar myCalendar = Calendar.getInstance();


    public static RegisterContactFragment newInstance(int index) {
        RegisterContactFragment fragment = new RegisterContactFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(getActivity());
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 3;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_new_contact, container, false);
        firstNameEditText = root.findViewById(R.id.et_first_name);
        lastNameEditText = root.findViewById(R.id.et_last_name);
        phoneEditText = root.findViewById(R.id.et_phone);
        phone1EditText = root.findViewById(R.id.et_phone2);
        companyEditText = root.findViewById(R.id.et_company_name);
        saveButton = root.findViewById(R.id.btn_save);
        dateOfBirth = root.findViewById(R.id.et_birthday);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                dateOfBirth.setText(sdf.format(myCalendar.getTime()));
            }

        };


        dateOfBirth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String phone1 = phone1EditText.getText().toString();
                String companyName = companyEditText.getText().toString();

                if(firstName.length() > 0 && phone.length() > 0 /*&&
                        phone.matches("/^\\d{12}$/") && phone.matches("/^\\d{11}$/")*/){
                    contact.setFirstName(firstName);
                    contact.setLastName(lastName);
                    contact.setPhone(phone);
                    contact.setPhone1(phone1);
                    contact.setCompanyName(companyName);
                    contact.setRecent(0); //0 is false
                    contact.setFavourite(0); //0 is false
                    dbHelper.insertData(contact);
                    Toast.makeText(getActivity(), "Create Success", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(firstName.length() == 0)
                        firstNameEditText.setError("Please don't empty");
                    if(phone.length() > 0)
                        phoneEditText.setError("Please input correct phone number");
//                    if(!phone.matches("/^\\d{12}$/"))
//                        phoneEditText.setError("Please input correct phone number");
//                    if(!phone1.matches("/^\\d{12}$/"))
//                        phone1EditText.setError("Please input correct phone number");
                }


            }
        });
        return root;
    }
}