package member.service;

import member.dto.*;

public interface KBOService {
	KBOLoginDTO login(String email,String password);
	KBOLoginDTO register(KBOLoginDTO register);
	boolean emailcheck(String email);
	boolean passcheck(String password);
	int memberleave(String email,String password);
	boolean logincheck(String email, String password);
	int changepass(String email,String password);
	int register1(KBOLoginDTO register);
}
