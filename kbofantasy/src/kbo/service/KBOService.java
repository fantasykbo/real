package kbo.service;

import kbo.login.dto.*;

public interface KBOService {
	KBOLoginDTO login(String email,String password);
	KBOLoginDTO register(KBOLoginDTO register);
	boolean emailcheck(String email);
}
