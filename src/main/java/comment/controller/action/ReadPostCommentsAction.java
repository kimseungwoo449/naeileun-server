package comment.controller.action;

import board.model.BoardDao;
import board.model.BoardResponseDto;
import comment.controller.Action;
import org.json.JSONArray;
import org.json.JSONObject;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ReadPostCommentsAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject resObj = new JSONObject();

		if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
			boolean status = false;
			String message = "Board is blocked.";
			
			resObj.put("status", status);
			resObj.put("message", message);
		} else {

			InputStream in = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String data = "";
			while (br.ready()) {
				data += br.readLine() + "\n";
			}
			JSONObject object = new JSONObject(data);

		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(resObj.toString());
	}

}
