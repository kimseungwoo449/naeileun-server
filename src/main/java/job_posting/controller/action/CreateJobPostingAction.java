package job_posting.controller.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import job_posting.controller.Action;
import job_posting.model.JobPostingDao;
import job_posting.model.JobPostingRequestDto;
import job_posting.model.JobPostingResponseDto;
import org.json.JSONObject;
import utill.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class CreateJobPostingAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        InputStream in = request.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String data = "";
        while(br.ready()){
            data += br.readLine() + "\n";
        }

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();

        JobPostingRequestDto jobPost = gson.fromJson(data, JobPostingRequestDto.class);
        JobPostingDao jobDao = JobPostingDao.getInstance();
        JobPostingResponseDto jobDto = jobDao.createJobPosting(jobPost);
        JSONObject resObj = new JSONObject();
        int status = (jobDto != null) ? 200 : 400;
        String message = (jobDto != null) ? "JobPosting registration is success." : "JobPosting registration is failed.";

        resObj.put("status", status);
        resObj.put("message", message);


        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(resObj.toString());


    }

}
