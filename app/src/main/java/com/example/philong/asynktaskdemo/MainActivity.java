package com.example.philong.asynktaskdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button buttonXuLy;
    TextView txtThongTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
    }

    void AnhXa() {
        buttonXuLy = findViewById(R.id.button);
        txtThongTin = findViewById(R.id.textView1);
        buttonXuLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new QlCongViec().execute();
            }
        });
    }


    private class QlCongViec extends AsyncTask<Void, String, String> {
        //3 tham số : 1 params kiểu dữ liệu , 2 progress trong quá trình sử lý , 3 khi hoàn thành tác vụ


        @Override
        protected void onPreExecute() { //công việc đầu tiên asynktask
            super.onPreExecute();
            txtThongTin.setText("Bắt đầu .  \n");
        }

        @Override
        protected String doInBackground(Void... voids) {  //hàm này không thay đổi được giao diện
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress("Xong viec " + i + "\n");  //thằng này default dùng để hiển thị doInBackGround ra onProgress

            }
            return "Xong rồi. \n"; //trả về kế quả cuối cùng background thực hiện gọi hiển thị ở onPost
        }

        @Override
        protected void onPostExecute(String s) {//nhận về giá trị khi xong tất cả các việc của AsynkTask
            super.onPostExecute(s);
            txtThongTin.append(s);  //gọi thằng trả về cuối cùng từ doInBackground
            // append để giữ biến ban đầu + hiển thị thêm
        }

        @Override
        protected void onProgressUpdate(String... values) {  //gọi quá trình doInBackground làm việc và xuất ra màn hình
            super.onProgressUpdate(values);
            txtThongTin.append(values[0]);
        }
    }

}
