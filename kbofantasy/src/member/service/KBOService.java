package member.service;

import member.dto.*;

public interface KBOService {
	KBOLoginDTO login(String email,String password);
	KBOLoginDTO register(KBOLoginDTO register);
	boolean emailcheck(String email);
	boolean passcheck(String password);
}
