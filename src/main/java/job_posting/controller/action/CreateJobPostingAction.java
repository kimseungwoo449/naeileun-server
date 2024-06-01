package job_posting.controller.action;

import com.google.gson.Gson;
import job_posting.controller.Action;
import job_posting.model.JobPostingDao;
import job_posting.model.JobPostingRequestDto;
import job_posting.model.JobPostingResponseDto;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CreateJobPostingAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream in = request.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String data = "";
        while(br.ready()){
            data += br.readLine() + "\n";
        }

        Gson gson = new Gson();
        JobPostingRequestDto jobPost = gson.fromJson(data.toString(), JobPostingRequestDto.class);

        // Create user
        JobPostingDao userDao = JobPostingDao.getInstance();
        JobPostingResponseDto userDto = userDao.createJobPosting(jobPost);

        // Prepare response JSON object
        JSONObject resObj = new JSONObject();
        int status = (userDto != null) ? 200 : 400;
        String message = (userDto != null) ? "User registration is success." : "User registration is failed.";
        System.out.println("status: " + status);
        System.out.println("message: " + message);
        resObj.put("status", status);
        resObj.put("message", message);

        // Send response
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(resObj.toString());

        System.out.println(message);
    }
}
