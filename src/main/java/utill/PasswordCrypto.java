package utill;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordCrypto {
	  // 암호화
	   public static String encrypt(String password) {
	      // 패싱된 String 패스워드를 반환하도록
	      return BCrypt.hashpw(password, BCrypt.gensalt());
	   }
	   
	   // 복호화
		// 복호화
		public static boolean decrypt(String password, String encrytedPassword) {
			return BCrypt.checkpw(password,encrytedPassword);
		}
}
