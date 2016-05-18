package kbo.service;

import kbo.login.dto.*;

public interface KBOService {
	KBOLoginDTO login(String email,String password);
	KBOLoginDTO register(KBOLoginDTO register);
	boolean emailcheck(String email);
	boolean passcheck(String password);
	int memberleave(String email,String password);
}
