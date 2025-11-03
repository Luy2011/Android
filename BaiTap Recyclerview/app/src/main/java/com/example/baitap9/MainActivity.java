package com.example.baitap9;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvContact;
    private EditText etSearch;
    private TextView tvCount;
    private CustomAdapter customAdapter;
    private ArrayList<Contact> arrContact;
    private ArrayList<Contact> arrContactFiltered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo views
        lvContact = findViewById(R.id.lvContact);
        etSearch = findViewById(R.id.etSearch);
        tvCount = findViewById(R.id.tvCount);

        // Tạo dữ liệu
        initData();

        // Setup adapter
        setupAdapter();

        // Setup search
        setupSearch();

        // Setup item click
        setupItemClick();
    }

    /**
     * Khởi tạo dữ liệu sinh viên
     */
    private void initData() {
        arrContact = new ArrayList<>();

        Contact contact1 = new Contact("Nguyễn Văn Luỹ", "23115053122117", Color.RED);
        Contact contact2 = new Contact("Nguyễn Hà Vi ", "23115053122148", Color.GREEN);
        Contact contact3 = new Contact("Võ Đức Phú", "151250533308", Color.GRAY);
        Contact contact4 = new Contact("Bạch Ngọc Mỹ Duyên", "161250533207", Color.YELLOW);
        Contact contact5 = new Contact("Hồ Hữu Huy", "151250533113", Color.BLACK);
        Contact contact6 = new Contact("Nguyễn Trí Trường", "131250532378", Color.BLUE);
        Contact contact7 = new Contact("Nguyễn Hoài Hoai", "151250533116", Color.CYAN);

        arrContact.add(contact1);
        arrContact.add(contact2);
        arrContact.add(contact3);
        arrContact.add(contact4);
        arrContact.add(contact5);
        arrContact.add(contact6);
        arrContact.add(contact7);

        // Khởi tạo danh sách filtered
        arrContactFiltered = new ArrayList<>(arrContact);
    }

    /**
     * Setup CustomAdapter
     */
    private void setupAdapter() {
        customAdapter = new CustomAdapter(this, R.layout.row_listview, arrContactFiltered);
        lvContact.setAdapter(customAdapter);
        updateCount();
    }

    /**
     * Setup chức năng tìm kiếm
     */
    private void setupSearch() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Không cần xử lý
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Không cần xử lý
            }
        });
    }

    /**
     * Lọc dữ liệu theo từ khóa
     */
    private void filterData(String keyword) {
        arrContactFiltered.clear();

        if (keyword.isEmpty()) {
            // Nếu không có từ khóa, hiển thị tất cả
            arrContactFiltered.addAll(arrContact);
        } else {
            // Lọc theo tên hoặc mã sinh viên
            String keywordLower = keyword.toLowerCase();
            for (Contact contact : arrContact) {
                if (contact.getName().toLowerCase().contains(keywordLower) ||
                        contact.getIdStudent().toLowerCase().contains(keywordLower)) {
                    arrContactFiltered.add(contact);
                }
            }
        }

        customAdapter.notifyDataSetChanged();
        updateCount();
    }

    /**
     * Setup sự kiện click item
     */
    private void setupItemClick() {
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selectedContact = arrContactFiltered.get(position);
                showContactDetail(selectedContact);
            }
        });

        // Long click để xóa
        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selectedContact = arrContactFiltered.get(position);
                deleteContact(selectedContact);
                return true;
            }
        });
    }

    /**
     * Hiển thị chi tiết sinh viên
     */
    private void showContactDetail(Contact contact) {
        String message = "Tên: " + contact.getName() + "\nMã SV: " + contact.getIdStudent();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Xóa sinh viên
     */
    private void deleteContact(Contact contact) {
        arrContact.remove(contact);
        arrContactFiltered.remove(contact);
        customAdapter.notifyDataSetChanged();
        updateCount();
        Toast.makeText(this, "Đã xóa: " + contact.getName(), Toast.LENGTH_SHORT).show();
    }

    /**
     * Cập nhật số lượng sinh viên
     */
    private void updateCount() {
        tvCount.setText(String.valueOf(arrContactFiltered.size()));
    }
}