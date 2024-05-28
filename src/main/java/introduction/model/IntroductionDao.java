package introduction.model;

import utill.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class IntroductionDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private static IntroductionDao instance = new IntroductionDao();

    private IntroductionDao(){
    }

    public static IntroductionDao getInstance() {
        return instance;
    }

    public boolean addIntroduction(List<IntroductionRequestDto> dtoArr) {
        int documentCode = getMaxDocumentCode() +1;
        try {
            conn = DBManager.getConnection();
            String sql = "INSERT INTO self_introduction(title,head,body,document_code,user_code) VALUES ";

            for(int i = 0; i < dtoArr.size(); i++) {
                IntroductionRequestDto dto = dtoArr.get(i);
                sql += String.format("('%s','%s','%s','%d','%d')",dto.getTitle(),dto.getHead(),dto.getBody(),documentCode,dto.getUserCode());
                if(i < dtoArr.size()-1) {
                    sql+=",";
                }
            }
            pstmt = conn.prepareStatement(sql);

            pstmt.execute();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn,pstmt);
        }

        return false;
    }

    private int getMaxDocumentCode(){
        int lastCode = -1;
        try {
            conn = DBManager.getConnection();
            String sql = "SELECT MAX(document_code) FROM `self_introduction`";
            pstmt = conn.prepareStatement(sql);

            rs=pstmt.executeQuery();
            while(rs.next()) {
                lastCode = rs.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.close(conn, pstmt, rs);
        }
        return lastCode;
    }
}
