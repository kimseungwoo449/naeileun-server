package board.controller.action;

import board.controller.Action;
import org.json.JSONObject;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CreatePostAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject resObj = new JSONObject();

        boolean status = true;

        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            status = false;
        } else {
            InputStream in = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String data = "";
            int count = 0;

            while (br.ready()) {
                data += br.readLine() + "\n";
                count++;
            }

            JSONObject reqObj = new JSONObject(data);
        }
    }
}
