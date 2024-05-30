package utill;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class ImageHandler {

    private final static String boundary = "===" + UUID.randomUUID() + "===";
    private final static String imageUrl = "https://ucarecdn.com/";

    public static String upload(String type, long size, byte[] fileContents) {

        if(type.equals("image")) {
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

    public static Boolean deleteImage(String imageUrl) {
        Boolean isDelete = false;

        String uuid = imageUrl.split("/")[3];
        System.out.println("uuid : " + uuid);

        if(uuid != null) {
            // 2) Rest API 파일 삭제 요청
            String path = "https://api.uploadcare.com/files/"+uuid+"/storage/?uuid="+uuid;
            String apiKey = KeyManager.getDeletecareKey();

            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("DELETE");
                conn.setRequestProperty("Authorization", apiKey);

                // 3) 응답 받고
                int status = conn.getResponseCode();
                System.out.println("status : " + status);

                if(status == 200) {
                    InputStream in = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));

                    String data ="";
                    while(br.ready()) {
                        data += br.readLine() + "\n";
                    }

                    JSONObject resObj = new JSONObject(data);

                    String result = resObj.getString("datetime_removed");
                    System.out.println("datetime_removed : " + result);

                    isDelete = true;
                } else {

                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isDelete;
    }

}
