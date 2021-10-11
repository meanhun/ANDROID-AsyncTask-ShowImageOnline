package com.share4happy;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btn;
    ImageView img;
//    String link1 = "";
//    String link2 = "";
    String link1 = "https://2.bp.blogspot.com/-fjf5yU5r1Jk/WE1VD1BBKpI/AAAAAAAAjgI/bXwGoigAPJYvScMPtzJtzbOJfoGQO2C_ACEw/s1600/15349541_533868826819201_3350340522319981193_n.jpg";
    String link2 = "hhttps://phunugioi.com/wp-content/uploads/2020/03/hinh-anh-hot-girl-toc-ngan-de-thuong.jpg";
    String link3 = "https://anhchien.vn/nhung-hinh-anh-hot-girl-dep-nhat/imager_1_37774_700.jpg";
    String link4 = "https://keomoi.com/wp-content/uploads/2019/05/anh-gai-dep-de-thuong-hinh-6.jpg";
    String link5 = "https://anhdep123.com/wp-content/uploads/2021/02/anh-girl-cute-768x464.jpg";
    String link6 = "https://1.bp.blogspot.com/-cbtutZaDcyE/WY5fKruPBBI/AAAAAAAAC9I/NvmmiI6n6EU6Wnmqg6FSGVYfq6yq8ByPACEwYBhgL/s1600/girl-xinh-facebook13__50683_zoom-768x768.jpg";
    String link7 = "https://cuoixastress.com/wp-content/uploads/2021/06/anh-hot-girl-lanh-lung-32.gif";
    String link8 = "https://1.bp.blogspot.com/-RFJj37ND0MY/WE1VC8wA8CI/AAAAAAAAjf4/F8G1C1bl9YYe5vlrGeTtWoDJbS6z1zrQACEw/s1600/15319063_1870502259827840_8901988026005563692_n.jpg";
    String link9 = "https://cuoixastress.com/wp-content/uploads/2021/06/anh-hot-girl-lanh-lung-38.gif";
    Random r = new Random();
    ArrayList<String> ds_hinh = new ArrayList<>();
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        //Sự kiện tải ảnh
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = r.nextInt(9);
                //Gọi lệnh tải hình
                ImageTask imageTask = new ImageTask();
                imageTask.execute(ds_hinh.get(num));
            }
        });
    }

    private void addControls() {
        btn = findViewById(R.id.button);
        img = findViewById(R.id.imageView);
        ds_hinh.add(link1);
        ds_hinh.add(link2);
        ds_hinh.add(link3);
        ds_hinh.add(link4);
        ds_hinh.add(link5);
        ds_hinh.add(link6);
        ds_hinh.add(link7);
        ds_hinh.add(link8);
        ds_hinh.add(link9);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Thông báo");
        progressDialog.setMessage("Đang tải hình ảnh, vui long chờ chút!");
        //Nhấn ra ngoài thì không tắt dialog
        progressDialog.setCanceledOnTouchOutside(false);
    }
    // Vì không biết tiến trình chạy khi nào xong nên khai báo dối số thứ 2 là void
    class ImageTask extends AsyncTask<String, Void, Bitmap>{
        //Bắt đầu tiến trình

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String link = strings[0];
            try {
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(link).getContent());
                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
        //kết thúc tiến trình

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            img.setImageBitmap(bitmap);
            // Ẩn dialog, có thể tái sử dụng lại dialog này
            // Nếu dùng cancel thì không thể tái sử dụng
            progressDialog.dismiss();
        }
    }
}