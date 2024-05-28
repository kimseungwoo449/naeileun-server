package introduction.model;

import utill.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntroductionDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private static IntroductionDao instance = new IntroductionDao();

    private IntroductionDao() {
    }

    public static IntroductionDao getInstance() {
        return instance;
    }

    public boolean addIntroduction(List<IntroductionRequestDto> dtoArr) {
        int documentCode = getMaxDocumentCode() + 1;
        try {
            conn = DBManager.getConnection();
            String sql = "INSERT INTO self_introduction(title,head,body,document_code,user_code) VALUES ";

            for (int i = 0; i < dtoArr.size(); i++) {
                IntroductionRequestDto dto = dtoArr.get(i);
                sql += String.format("('%s','%s','%s','%d','%d')", dto.getTitle(), dto.getHead(), dto.getBody(), documentCode, dto.getUserCode());
                if (i < dtoArr.size() - 1) {
                    sql += ",";
                }
            }
            pstmt = conn.prepareStatement(sql);

            pstmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }

        return false;
    }

    private int getMaxDocumentCode() {
        int lastCode = -1;
        try {
            conn = DBManager.getConnection();
            String sql = "SELECT MAX(document_code) FROM `self_introduction`";
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                lastCode = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return lastCode;
    }

//    public List<IntroductionResponseDto> getAllIntroductionByUserCode(IntroductionRequestDto dto) {
//        List<IntroductionResponseDto> response = null;
//        Map<Integer, List<IntroductionResponseDto>> docMap = null;
//        try {
//            conn = DBManager.getConnection();
//
//            String sql = "SELECT * FROM self_introduction WHERE `user_code` = ?";
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, dto.getUserCode());
//            rs = pstmt.executeQuery();
//
//            response = new ArrayList<>();
//            while (rs.next()) {
//                int documentNumber = rs.getInt(1);
//                String title = rs.getString(2);
//                String head = rs.getString(3);
//                String body = rs.getString(4);
//                int documentCode = rs.getInt(5);
//                int userCode = rs.getInt(6);
//                Timestamp writeDate = rs.getTimestamp(7);
//                Timestamp updateDate = rs.getTimestamp(8);
//
//                IntroductionResponseDto introduction = new IntroductionResponseDto(documentNumber, title, head, body, documentCode, userCode, writeDate, updateDate);
//                response.add(introduction);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            DBManager.close(conn, pstmt, rs);
//        }
//
//        if (response.size() == 0) {
//            response = null;
//        }
//        return response;
//    }

    public Map<Integer, List<IntroductionResponseDto>> getAllIntroductionByUserCode(IntroductionRequestDto dto) {
        List<Integer> docCodes = getDocumentCodes(dto);
        Map<Integer, List<IntroductionResponseDto>> docMap = createMap(docCodes);

        if (docMap.size() == 0) {
            docMap = null;
        }
        return docMap;
    }

    private Map<Integer, List<IntroductionResponseDto>> createMap(List<Integer> docCodes) {
        Map<Integer, List<IntroductionResponseDto>> docMap = new HashMap<>();

        for (int i = 0; i < docCodes.size(); i++) {
            int docCode = docCodes.get(i);
            try {
                conn = DBManager.getConnection();
                String sql = "SELECT * FROM self_introduction WHERE document_code = ?";

                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, docCode);

                List<IntroductionResponseDto> temp = new ArrayList<>();
                rs = pstmt.executeQuery();  // Ensure ResultSet is assigned here
                while (rs.next()) {
                    int documentNumber = rs.getInt(1);
                    String title = rs.getString(2);
                    String head = rs.getString(3);
                    String body = rs.getString(4);
                    int documentCode = rs.getInt(5);
                    int userCode = rs.getInt(6);
                    Timestamp writeDate = rs.getTimestamp(7);
                    Timestamp updateDate = rs.getTimestamp(8);

                    IntroductionResponseDto introduction = new IntroductionResponseDto(documentNumber, title, head, body, documentCode, userCode, writeDate, updateDate);
                    temp.add(introduction);
                }
                docMap.put(docCodes.get(i), temp);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DBManager.close(conn, pstmt, rs);
            }
        }
        return docMap;
    }

    private List<Integer> getDocumentCodes(IntroductionRequestDto dto) {
        List<Integer> docCodes = null;
        try {
            conn = DBManager.getConnection();

            String sql = "SELECT document_code FROM self_introduction GROUP BY document_code,user_code HAVING COUNT(document_code)>=1 AND `user_code`=?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, dto.getUserCode());
            rs = pstmt.executeQuery();

            docCodes = new ArrayList<>();
            while (rs.next()) {
                int documentCode = rs.getInt(1);
                docCodes.add(documentCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return docCodes;
    }
}