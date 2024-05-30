package utill;

import org.json.JSONObject;

import javax.servlet.http.Part;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class ImageHandler {

    private final static String boundary = "===" + UUID.randomUUID() + "===";
    private final static String imageEndpoint = "https://ucarecdn.com/";

    public static String upload(Part part) throws IOException {
        String imageUrl = null;

        long size = part.getSize();
        String type = part.getContentType().split("/")[0];

        if(size < 10485760 && type.equals("image")) {   // 10mb 미만 (Demo plan Limit)
            // Request
            String apiKey = KeyManager.getUploadcareKey();

            String path = "https://upload.uploadcare.com/base/";
            URL url = new URL(path);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            conn.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());

            try {
                // API key
                out.writeBytes("--" + boundary + "\r\n");
                out.writeBytes("Content-Disposition: form-data; name=\"UPLOADCARE_PUB_KEY\"\r\n\r\n");
                out.writeBytes(apiKey + "\r\n");

                // File data
                out.writeBytes("--" + boundary + "\r\n");
                out.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + part.getSubmittedFileName() + "\"\r\n");
                out.writeBytes("Content-Type: " + part.getContentType() + "\r\n\r\n");

                InputStream in = part.getInputStream();
                out.write(in.readAllBytes());
                in.close();

                out.writeBytes("\r\n--" + boundary + "--\r\n");
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Response
            int status = conn.getResponseCode();

            if(status == 200) {
                InputStream response = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(response));

                String source = "";
                while(br.ready()) {
                    source += br.readLine() + "\n";
                }
                JSONObject object = new JSONObject(source);
                String uuid = object.getString("file");

                imageUrl = imageEndpoint + uuid + "/";
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
