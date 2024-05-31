package job_posting.controller.action;

import job_posting.controller.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadJobPostingAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream is = request.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
    }
}
