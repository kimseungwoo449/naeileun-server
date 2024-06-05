package user.controller.action.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDao;
import org.json.JSONObject;

import user.controller.Action;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;
import utill.KeyManager;

public class DeleteAction implements Action{

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject resObj = new JSONObject();

		int status = 0;
		String message = "";

		if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
			status = 400;
			message = "User delete is failed.";
		} else {
			InputStream in = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String data = "";
			while (br.ready()) {
				data += br.readLine() + "\n";
			}
			JSONObject object = new JSONObject(data);


			request.setCharacterEncoding("UTF-8");

			UserDao userDao = UserDao.getInstance();


			String id = object.getString("id");
			int userCode = Integer.parseInt(object.getString("userCode"));
			BoardDao boardDao = BoardDao.getInstance();
			UserRequestDto userDto = new UserRequestDto();

			userDto.setId(id);

			boolean result = userDao.deleteUser(userDto);

			 status = result ? 200 : 400;
			 message = result ? "User delete is success." : "User delete is failed.";

			if (status == 200) {
				boardDao.deletePostByUserCode(userCode);
			}
		}
		resObj.put("status", status);
		resObj.put("message", message);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(resObj.toString());
	}

}
