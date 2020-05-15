package com.example.contactthrymrsystemtest.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactthrymrsystemtest.Contact;
import com.example.contactthrymrsystemtest.R;
import com.example.contactthrymrsystemtest.ui.main.model.PageViewModel;
import com.example.contactthrymrsystemtest.ui.main.adapter.CustomAdapterForRecyclerView;
import com.example.contactthrymrsystemtest.ui.main.database.DBHelper;

import java.util.ArrayList;

public class ContactListFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    private RecyclerView recyclerView;

    private DBHelper dbHelper;

    private ArrayList<Contact> contacts = new ArrayList<>();

    private RecyclerView.LayoutManager mLayoutManager;

    public static ContactListFragment newInstance(int index) {
        ContactListFragment fragment = new ContactListFragment();
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
        int index = 0;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contact_list, container, false);
        recyclerView = root.findViewById(R.id.contact_recycler_view);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        contacts = dbHelper.getAllContacts();
        CustomAdapterForRecyclerView customAdapterForRecyclerView = new CustomAdapterForRecyclerView(contacts, getActivity());
        recyclerView.setAdapter(customAdapterForRecyclerView);
        return root;
    }

    //TODO for retrieving contacts from device
//    private ArrayList<Contact> getAllContacts() {
//        ArrayList<Contact> contactsFromPhone = new ArrayList<Contact>();
//        ContentResolver cr = getActivity().getContentResolver();
//        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
//                null, null, null, null);
//        if ((cur != null ? cur.getCount() : 0) > 0) {
//            String contactId = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
//
//            while (cur != null && cur.moveToNext()) {
//                Contact contact = new Contact();
//                String id = cur.getString(
//                        cur.getColumnIndex(ContactsContract.Contacts._ID));
//                String name = cur.getString(cur.getColumnIndex(
//                        ContactsContract.Contacts.DISPLAY_NAME));
//                contact.setFirstName(name);
//
//                String hasNumber = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
//                String num = "";
//                if (Integer.valueOf(hasNumber) == 1) {
//                    Cursor numbers = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
//                    while (numbers.moveToNext()) {
//                        num = numbers.getString(numbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                    }
//
//                    if (cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
//                        Cursor pCur = cr.query(
//                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                                null,
//                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
//                                new String[]{id}, null);
//                        while (pCur.moveToNext()) {
//                            String phoneNo = pCur.getString(pCur.getColumnIndex(
//                                    ContactsContract.CommonDataKinds.Phone.NUMBER));
//                            contact.setPhone(phoneNo);
//                        }
//                        pCur.close();
//                        contactsFromPhone.add(contact);
//                    }
//                }
//                if (cur != null) {
//                    cur.close();
//                }
//            }
//        }
//        return contactsFromPhone;
//    }
}

