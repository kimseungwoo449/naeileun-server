package utill;

import javax.servlet.http.Part;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class ImageHandler {

    private final static String boundary = "===" + UUID.randomUUID() + "===";

    public static String upload(String type, long size, byte[] fileContents) {
        String imageUrl = null;

        if(type.equals("image")) {
            imageUrl = "https://ucarecdn.com/";

            // 1) 이미지 사이즈가 100mb 미만인지 확인
            if(size < 104857600) {
                // 2) Upload API 파일 업로드 요청
                String path = "https://upload.uploadcare.com/base/";
                String apiKey = KeyManager.getUploadcareKey();

                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                    conn.setRequestProperty("UPLOADCARE_PUB_KEY", apiKey);

                    conn.setDoOutput(true);
                    DataOutput out = (DataOutput) conn.getOutputStream();

                    out.writeBytes(boundary);
                    out.writeBytes("Content-Disposition: form-data; filename=");
                    out.write(fileContents);

                    // 3) 응답으로 저장된 이미지의 UUID 받고
                    int status = conn.getResponseCode();
                    System.out.println("status : " + status);
                    // 4) url 완성

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return imageUrl;
    }

}
